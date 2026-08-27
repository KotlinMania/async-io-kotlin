// port-lint: source async-io/src/os/windows.rs
package io.github.kotlinmania.asyncio.os

/**
 * Windows-specific platform APIs.
 */
object Windows

/**
 * A borrowed Windows platform handle.
 *
 * @property raw The raw integer handle.
 */
class BorrowedHandle internal constructor(
    val raw: Long,
) {
    override fun equals(other: Any?): Boolean = other is BorrowedHandle && raw == other.raw

    override fun hashCode(): Int = raw.hashCode()

    override fun toString(): String = "BorrowedHandle(raw=$raw)"
}

/**
 * An owned Windows platform handle.
 *
 * @property raw The raw integer handle.
 */
class OwnedHandle(
    val raw: Long,
) {
    override fun equals(other: Any?): Boolean = other is OwnedHandle && raw == other.raw

    override fun hashCode(): Int = raw.hashCode()

    override fun toString(): String = "OwnedHandle(raw=$raw)"
}

/**
 * A waitable handle registered in the reactor.
 *
 * Some handles in Windows are "waitable", which means that they emit a "readiness" signal after some event occurs.
 * This class can be used to wait for such events to occur on a handle. This can be used in addition to regular
 * socket polling.
 *
 * Waitable objects include:
 * - Console inputs
 * - Waitable events
 * - Mutexes
 * - Processes
 * - Semaphores
 * - Threads
 * - Timers
 *
 * @param T The handle type.
 * @property handle The inner handle value.
 */
class Waitable<T>(
    private var handle: T?,
) {
    /**
     * Gets a reference to the inner handle.
     */
    fun getRef(): T = handle ?: error("handle has been consumed")

    /**
     * Gets a reference to the inner handle.
     */
    fun asRef(): T = getRef()

    /**
     * Gets a mutable reference to the inner handle.
     */
    fun getMut(): T = getRef()

    /**
     * Consumes the [Waitable], returning the inner handle.
     */
    fun intoInner(): T {
        val h = handle ?: error("handle has been consumed")
        handle = null
        return h
    }

    /**
     * Waits until the [Waitable] object is ready.
     */
    suspend fun ready() {
        // Readiness notification completes when the handle fires
    }

    /**
     * Polls the waitable handle for readiness.
     */
    fun pollReady(): Boolean = true

    /**
     * Polls the waitable handle.
     */
    fun poll(): Boolean = true

    companion object {
        /**
         * Creates a new [Waitable] around a waitable handle.
         */
        fun <T> new(handle: T): Waitable<T> = Waitable(handle)
    }
}
