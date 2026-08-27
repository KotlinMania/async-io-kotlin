// port-lint: source async-io/src/os.rs
package io.github.kotlinmania.asyncio.os

/**
 * Platform-specific functionality.
 *
 * Exposes platform-specific I/O types and registration primitives for Unix, Kqueue, and Windows.
 */
object Os {
    /**
     * Unix-specific extensions and file descriptor registrations.
     */
    val unix: Unix = Unix

    /**
     * Kqueue-specific registrations (macOS, iOS, BSD).
     */
    val kqueue: Kqueue = Kqueue

    /**
     * Windows-specific registrations and handle wrappers.
     */
    val windows: Windows = Windows
}
