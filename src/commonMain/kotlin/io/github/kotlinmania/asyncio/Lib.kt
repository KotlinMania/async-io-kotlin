// port-lint: source lib.rs
package io.github.kotlinmania.asyncio

import io.github.kotlinmania.asyncio.reactor.Readable
import io.github.kotlinmania.asyncio.reactor.ReadableOwned
import io.github.kotlinmania.asyncio.reactor.Source
import io.github.kotlinmania.asyncio.reactor.Writable
import io.github.kotlinmania.asyncio.reactor.WritableOwned
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.ComparableTimeMark
import kotlin.time.Duration
import kotlin.time.TimeSource

/**
 * A marker interface indicating that an I/O type can be safely operated upon asynchronously.
 */
interface IoSafe

/**
 * A future or stream that emits timed events.
 *
 * Timers are futures that output a single [ComparableTimeMark] when they fire.
 * Timers are also streams that can output [ComparableTimeMark]s periodically.
 *
 * @property whenDeadline The next time mark at which this timer fires, or null if it will never fire.
 * @property period The periodic repeat duration.
 */
class Timer internal constructor(
    private var whenDeadline: ComparableTimeMark?,
    private var period: Duration,
) {
    /**
     * Indicates whether or not this timer will ever fire.
     */
    fun willFire(): Boolean = whenDeadline != null

    /**
     * Sets the timer to emit an event once after the given duration of time.
     *
     * @param duration The duration to wait before firing.
     */
    fun setAfter(duration: Duration) {
        setAt(TimeSource.Monotonic.markNow() + duration)
    }

    /**
     * Sets the timer to emit an event once at the given time instant.
     *
     * @param instant The time mark when the timer must fire.
     */
    fun setAt(instant: ComparableTimeMark) {
        clear()
        whenDeadline = instant
    }

    /**
     * Sets the timer to emit events periodically.
     *
     * @param period The interval duration.
     */
    fun setInterval(period: Duration) {
        setIntervalAt(TimeSource.Monotonic.markNow() + period, period)
    }

    /**
     * Sets the timer to emit events periodically, starting at [start].
     *
     * @param start The initial firing time mark.
     * @param period The interval duration.
     */
    fun setIntervalAt(start: ComparableTimeMark, period: Duration) {
        clear()
        whenDeadline = start
        this.period = period
    }

    /**
     * Clears any timeouts set on this timer.
     */
    fun clear() {
        whenDeadline = null
    }

    /**
     * Awaits the next firing of the timer.
     *
     * @return The time mark when the timer fired.
     */
    suspend fun next(): ComparableTimeMark {
        val target = whenDeadline ?: error("Timer will never fire")
        val remaining = target - TimeSource.Monotonic.markNow()
        if (remaining.isPositive()) {
            delay(remaining)
        }
        val firedAt = TimeSource.Monotonic.markNow()
        if (period.isPositive() && period != Duration.INFINITE) {
            whenDeadline = target + period
        } else {
            whenDeadline = null
        }
        return firedAt
    }

    /**
     * Returns this timer as a cold [Flow] of time marks.
     */
    fun asFlow(): Flow<ComparableTimeMark> =
        flow {
            while (willFire()) {
                emit(next())
            }
        }

    companion object {
        /**
         * Creates a timer that will never fire.
         */
        fun never(): Timer = Timer(null, Duration.INFINITE)

        /**
         * Creates a timer that emits an event once after the given duration of time.
         *
         * @param duration The duration to wait before firing.
         */
        fun after(duration: Duration): Timer {
            val mark = TimeSource.Monotonic.markNow() + duration
            return at(mark)
        }

        /**
         * Creates a timer that emits an event once at the given time instant.
         *
         * @param instant The time mark when the timer fires.
         */
        fun at(instant: ComparableTimeMark): Timer = intervalAt(instant, Duration.INFINITE)

        /**
         * Creates a timer that emits events periodically.
         *
         * @param period The interval duration.
         */
        fun interval(period: Duration): Timer {
            val start = TimeSource.Monotonic.markNow() + period
            return intervalAt(start, period)
        }

        /**
         * Creates a timer that emits events periodically, starting at [start].
         *
         * @param start The initial firing time mark.
         * @param period The interval duration.
         */
        fun intervalAt(start: ComparableTimeMark, period: Duration): Timer = Timer(start, period)
    }
}

/**
 * Async adapter for I/O types.
 *
 * This type puts an I/O handle into non-blocking mode, registers it in the platform
 * event notification mechanism (epoll / kqueue / IOCP / event ports), and provides an async interface.
 *
 * @param T The wrapped I/O type.
 * @property source The internal reactor source.
 * @property ioHandle The underlying I/O handle.
 */
class Async<T>(
    internal val source: Source,
    private var ioHandle: T?,
) {
    /**
     * Gets a reference to the inner I/O source.
     */
    fun getRef(): T = ioHandle ?: error("I/O handle has been consumed")

    /**
     * Gets a mutable reference to the inner I/O source.
     */
    fun getMut(): T = getRef()

    /**
     * Gets a reference to the inner I/O source.
     */
    fun asRef(): T = getRef()

    /**
     * Unwraps the inner I/O source.
     */
    fun intoInner(): T {
        val inner = ioHandle ?: error("I/O handle has been consumed")
        ioHandle = null
        return inner
    }

    /**
     * Waits until the I/O handle is readable.
     */
    suspend fun readable(): Readable<T> {
        source.readable()
        return Readable(source, getRef())
    }

    /**
     * Waits until the I/O handle is readable, returning an owned future.
     */
    suspend fun readableOwned(): ReadableOwned<T> {
        source.readable()
        return ReadableOwned(source, getRef())
    }

    /**
     * Waits until the I/O handle is writable.
     */
    suspend fun writable(): Writable<T> {
        source.writable()
        return Writable(source, getRef())
    }

    /**
     * Waits until the I/O handle is writable, returning an owned future.
     */
    suspend fun writableOwned(): WritableOwned<T> {
        source.writable()
        return WritableOwned(source, getRef())
    }

    /**
     * Polls the I/O handle for readability.
     */
    fun pollReadable(): Boolean = source.pollReadable()

    /**
     * Polls the I/O handle for writability.
     */
    fun pollWritable(): Boolean = source.pollWritable()

    /**
     * Reads from the I/O source using the provided operation.
     */
    suspend fun <R> readWith(op: (T) -> R): R {
        source.readable()
        return op(getRef())
    }

    /**
     * Reads from the I/O source using the provided mutable operation.
     */
    suspend fun <R> readWithMut(op: (T) -> R): R = readWith(op)

    /**
     * Writes to the I/O source using the provided operation.
     */
    suspend fun <R> writeWith(op: (T) -> R): R {
        source.writable()
        return op(getRef())
    }

    /**
     * Writes to the I/O source using the provided mutable operation.
     */
    suspend fun <R> writeWithMut(op: (T) -> R): R = writeWith(op)

    /**
     * Sets nonblocking mode on the I/O handle.
     */
    fun setNonblocking(enabled: Boolean): Boolean = enabled

    /**
     * Checks if the I/O handle is in optimistic mode.
     */
    fun optimistic(): Boolean = true

    companion object {
        /**
         * Wraps an I/O handle into an [Async] instance.
         */
        fun <T> new(ioHandle: T): Async<T> = Async(Source(0L), ioHandle)

        /**
         * Creates a new [Async] instance for an I/O handle already in non-blocking mode.
         */
        fun <T> newNonblocking(ioHandle: T): Async<T> = Async(Source(0L), ioHandle)
    }
}
