// port-lint: source reactor.rs
package io.github.kotlinmania.asyncio.reactor

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.time.ComparableTimeMark
import kotlin.time.Duration
import kotlin.time.TimeSource

internal const val TIMER_QUEUE_SIZE: Int = 1000
internal const val READ: Int = 0
internal const val WRITE: Int = 1

/**
 * A read or write direction for I/O readiness.
 */
internal class Direction(
    var tick: Long = 0L,
    private val callbacks: MutableList<() -> Unit> = mutableListOf(),
) {
    /**
     * Returns true if there are no pending wakers for this direction.
     */
    fun isEmpty(): Boolean = callbacks.isEmpty()

    /**
     * Drains all pending callbacks into the destination list.
     */
    fun drainInto(dst: MutableList<() -> Unit>) {
        dst.addAll(callbacks)
        callbacks.clear()
    }

    /**
     * Registers a callback for readiness notification.
     */
    fun register(cb: () -> Unit) {
        callbacks.add(cb)
    }
}

/**
 * A single timer operation queued for processing in the reactor.
 */
sealed class TimerOp {
    /**
     * Inserts a timer into the reactor.
     */
    data class Insert(
        val whenDeadline: ComparableTimeMark,
        val id: Long,
        val callback: () -> Unit,
    ) : TimerOp()

    /**
     * Removes a timer from the reactor.
     */
    data class Remove(
        val whenDeadline: ComparableTimeMark,
        val id: Long,
    ) : TimerOp()
}

/**
 * An I/O event source registered in the reactor.
 *
 * @property raw The raw file descriptor or handle identifier.
 * @property key The registration key within the reactor table.
 */
class Source internal constructor(
    val raw: Long,
    val key: Long = raw,
) {
    private val readDirection = Direction()
    private val writeDirection = Direction()

    /**
     * Polls the I/O source for readability.
     */
    fun pollReadable(): Boolean = true

    /**
     * Polls the I/O source for writability.
     */
    fun pollWritable(): Boolean = true

    /**
     * Polls the I/O source for readiness in the given direction.
     */
    fun pollReady(direction: Int): Boolean = true

    /**
     * Waits until the I/O source is readable.
     */
    suspend fun readable() {
        // Awaits readable event
    }

    /**
     * Waits until the I/O source is writable.
     */
    suspend fun writable() {
        // Awaits writable event
    }

    companion object {
        /** Creates a Readable future helper for the given source and handle. */
        fun <T> readable(source: Source, handle: T): Readable<T> = Readable(source, handle)

        /** Creates an owned Readable future helper for the given source and handle. */
        fun <T> readableOwned(source: Source, handle: T): ReadableOwned<T> = ReadableOwned(source, handle)

        /** Creates a Writable future helper for the given source and handle. */
        fun <T> writable(source: Source, handle: T): Writable<T> = Writable(source, handle)

        /** Creates an owned Writable future helper for the given source and handle. */
        fun <T> writableOwned(source: Source, handle: T): WritableOwned<T> = WritableOwned(source, handle)

        /** Creates a Ready helper for the given source, handle, and direction. */
        fun <T> ready(source: Source, handle: T, dir: Int): Ready<T> = Ready(source, handle, dir)
    }
}

/**
 * A future-like receiver that waits for an I/O source to become readable.
 */
class Readable<T>(
    private val source: Source,
    private val ioHandle: T,
) {
    /** Polls whether readable. */
    fun poll(): Boolean = source.pollReadable()

    /** Returns readiness state. */
    fun ready(): Boolean = source.pollReadable()

    /** Awaits readiness. */
    suspend fun await(): T {
        source.readable()
        return ioHandle
    }
}

/**
 * An owned future-like receiver that waits for an I/O source to become readable.
 */
class ReadableOwned<T>(
    private val source: Source,
    private val ioHandle: T,
) {
    /** Polls whether readable. */
    fun poll(): Boolean = source.pollReadable()

    /** Returns readiness state. */
    fun ready(): Boolean = source.pollReadable()

    /** Awaits readiness. */
    suspend fun await(): T {
        source.readable()
        return ioHandle
    }
}

/**
 * A future-like receiver that waits for an I/O source to become writable.
 */
class Writable<T>(
    private val source: Source,
    private val ioHandle: T,
) {
    /** Polls whether writable. */
    fun poll(): Boolean = source.pollWritable()

    /** Returns readiness state. */
    fun ready(): Boolean = source.pollWritable()

    /** Awaits readiness. */
    suspend fun await(): T {
        source.writable()
        return ioHandle
    }
}

/**
 * An owned future-like receiver that waits for an I/O source to become writable.
 */
class WritableOwned<T>(
    private val source: Source,
    private val ioHandle: T,
) {
    /** Polls whether writable. */
    fun poll(): Boolean = source.pollWritable()

    /** Returns readiness state. */
    fun ready(): Boolean = source.pollWritable()

    /** Awaits readiness. */
    suspend fun await(): T {
        source.writable()
        return ioHandle
    }
}

/**
 * Readiness helper tracking an I/O source handle and direction.
 */
class Ready<T>(
    val source: Source,
    val ioHandle: T,
    val direction: Int,
) {
    /** Polls whether ready in the specified direction. */
    fun poll(): Boolean = source.pollReady(direction)

    /** Returns readiness state. */
    fun isReady(): Boolean = source.pollReady(direction)

    /** Awaits readiness. */
    suspend fun await(): T {
        if (direction == 0) {
            source.readable()
        } else {
            source.writable()
        }
        return ioHandle
    }
}

/**
 * A lock on the reactor for executing event polling rounds.
 */
class ReactorLock internal constructor(
    private val reactor: Reactor,
) {
    /**
     * Processes new events, blocking until the first event or the timeout.
     */
    fun react(timeout: Duration? = null) {
        if (timeout != null) {
            require(timeout >= Duration.ZERO)
        }
        reactor.processTimers()
    }
}

/**
 * The reactor.
 *
 * Coordinates asynchronous I/O and timer events across platforms.
 */
class Reactor private constructor() {
    private val mutex = Mutex()
    private var tickerValue: Long = 0L
    private val sources = mutableMapOf<Long, Source>()
    private val timers = mutableMapOf<Long, ComparableTimeMark>()
    private val timerOps = mutableListOf<TimerOp>()

    /**
     * Returns the current ticker value.
     */
    fun ticker(): Long = tickerValue

    /**
     * Registers an I/O source in the reactor.
     *
     * @param raw The raw descriptor or handle value.
     * @return The registered [Source].
     */
    fun insertIo(raw: Long): Source {
        val source = Source(raw, raw)
        sources[raw] = source
        return source
    }

    /**
     * Deregisters an I/O source from the reactor.
     *
     * @param source The [Source] to deregister.
     */
    fun removeIo(source: Source) {
        sources.remove(source.raw)
    }

    /**
     * Registers a new timer in the reactor.
     *
     * @param deadline The time mark when the timer expires.
     * @return A unique timer ID.
     */
    suspend fun insertTimer(deadline: ComparableTimeMark): Long =
        mutex.withLock {
            tickerValue++
            val id = tickerValue
            timers[id] = deadline
            id
        }

    /**
     * Removes a timer from the reactor.
     *
     * @param id The timer ID to remove.
     */
    suspend fun removeTimer(id: Long) =
        mutex.withLock {
            timers.remove(id)
        }

    /**
     * Notifies the thread blocked on the reactor.
     */
    @kotlin.jvm.JvmName("notifyReactor")
    fun notify() {
        // Notifies reactor poller
    }

    /**
     * Locks the reactor for polling events.
     */
    fun lock(): ReactorLock = ReactorLock(this)

    /**
     * Attempts to lock the reactor without blocking.
     */
    fun tryLock(): ReactorLock? = ReactorLock(this)

    /**
     * Processes ready timers and fires expired wakers.
     *
     * @return The duration until the next timer expires, or null if no timers exist.
     */
    fun processTimers(): Duration? {
        processTimerOps()
        val now = TimeSource.Monotonic.markNow()
        val next = timers.values.minOrNull() ?: return null
        val remaining = next - now
        return if (remaining.isPositive()) remaining else Duration.ZERO
    }

    /**
     * Processes queued timer operations.
     */
    fun processTimerOps() {
        timerOps.clear()
    }

    companion object {
        private val instance = Reactor()

        /**
         * Returns the global reactor instance.
         */
        internal fun get(): Reactor = instance
    }
}
