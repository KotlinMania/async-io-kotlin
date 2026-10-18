package io.github.kotlinmania.asyncio

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.ComparableTimeMark
import kotlin.time.Duration
import kotlin.time.TimeSource

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

    /**
     * Polls the timer for readiness.
     */
    fun poll(cx: Any? = null): ComparableTimeMark? = if (willFire()) TimeSource.Monotonic.markNow() else null

    /**
     * Polls the next stream item for readiness.
     */
    fun pollNext(cx: Any? = null): ComparableTimeMark? = poll(cx)

    /**
     * Drops this timer.
     */
    fun drop() {
        clear()
    }

    companion object {
        typealias Output = ComparableTimeMark
        typealias Item = ComparableTimeMark
        typealias Error = Nothing

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
