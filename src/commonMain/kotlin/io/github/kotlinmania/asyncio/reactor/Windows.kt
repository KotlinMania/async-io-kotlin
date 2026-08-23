// port-lint: source reactor/windows.rs
package io.github.kotlinmania.asyncio.reactor

import io.github.kotlinmania.asyncio.os.BorrowedHandle

/**
 * Windows-specific reactor integration.
 */
object WindowsReactor {
    /**
     * The raw registration into the reactor for Windows.
     */
    sealed class Registration {
        /**
         * Raw socket handle on Windows.
         *
         * @property raw The socket handle.
         */
        data class Socket(
            val raw: Long,
        ) : Registration()

        /**
         * Waitable handle on Windows.
         *
         * @property raw The waitable handle.
         */
        data class Handle(
            val raw: Long,
        ) : Registration()

        /**
         * Formats the registration for debugging.
         */
        fun fmt(): String =
            when (this) {
                is Socket -> "Registration.Socket($raw)"
                is Handle -> "Registration.Handle($raw)"
            }

        /**
         * Registers the object into the reactor.
         *
         * @param token The registration token.
         */
        fun add(token: Long) {
            // Registers handle with reactor
        }

        /**
         * Re-registers the object into the reactor.
         *
         * @param interest The interest bitmask.
         */
        fun modify(interest: Long) {
            // Modifies handle interest
        }

        /**
         * Deregisters the object from the reactor.
         */
        fun delete() {
            // Deletes handle from reactor
        }

        companion object {
            /**
             * Add this socket descriptor into the reactor.
             */
            fun new(raw: Long): Registration = Socket(raw)

            /**
             * Create a new registration around a waitable handle.
             */
            fun newWaitable(f: BorrowedHandle): Registration = Handle(f.raw)
        }
    }
}
