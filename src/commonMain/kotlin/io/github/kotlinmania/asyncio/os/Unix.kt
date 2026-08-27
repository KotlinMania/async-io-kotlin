// port-lint: source async-io/src/os/unix.rs
package io.github.kotlinmania.asyncio.os

/**
 * Unix-specific platform APIs.
 */
object Unix

/**
 * A borrowed platform file descriptor.
 *
 * This describes a valid file descriptor that has not been closed.
 *
 * @property raw The raw integer file descriptor.
 */
class BorrowedFd internal constructor(
    val raw: Long,
) {
    override fun equals(other: Any?): Boolean = other is BorrowedFd && raw == other.raw

    override fun hashCode(): Int = raw.hashCode()

    override fun toString(): String = "BorrowedFd(raw=$raw)"
}

/**
 * Gets a file descriptor that can be used to wait for readiness in an external runtime.
 *
 * This file descriptor is equivalent to the one used by the underlying epoll, kqueue, or
 * event-ports instance for polling. The intention is that this file descriptor can be registered
 * into an external runtime, such as calloop or GLib, so that async-io can be seamlessly polled
 * alongside the other runtime.
 *
 * Not every backend used on Unix has an associated file descriptor. While epoll, kqueue, and
 * event ports have a file descriptor as a backend, on some Unix systems async-io uses the poll()
 * system call instead. Since there are no file descriptors intrinsically associated with poll(),
 * this function returns null in that case.
 *
 * There is presently no way to stop the "async-io" thread from being launched, so the reactor
 * will still be continuously polled on that thread. This fact should be kept in mind by anyone
 * looking to integrate async-io into another runtime using this function.
 *
 * It is possible to use this function to call raw system calls on the underlying event source.
 * This is generally not recommended, since registered event sources may conflict with async-io's
 * existing scheme for managing sources. The behavior resulting from this is not specified, but
 * will not result in undefined behavior. This could include exceptions, incorrect results, aborts,
 * memory leaks, and non-termination.
 *
 * Example:
 *
 * ```kotlin
 * val fd = reactorFd() ?: return
 * myRuntime.register(fd)
 * ```
 */
fun reactorFd(): BorrowedFd? {
    val fd: BorrowedFd? = null
    return fd
}
