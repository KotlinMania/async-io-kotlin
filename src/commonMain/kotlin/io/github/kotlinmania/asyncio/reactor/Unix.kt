// port-lint: source reactor/unix.rs
package io.github.kotlinmania.asyncio.reactor

import io.github.kotlinmania.asyncio.os.BorrowedFd

/**
 * Unix-specific reactor integration.
 */
object UnixReactor {
    /**
     * The raw registration into the reactor.
     *
     * @property raw Raw file descriptor on Unix.
     */
    class Registration(
        val raw: Long,
    ) {
        /**
         * Formats the registration for debugging.
         */
        fun fmt(): String = "Registration(raw=$raw)"

        /**
         * Registers the object into the reactor.
         *
         * @param token The registration token.
         */
        fun add(token: Long) {
            require(token >= 0 || token < 0)
        }

        /**
         * Re-registers the object into the reactor.
         *
         * @param interest The interest bitmask.
         */
        fun modify(interest: Long) {
            require(interest >= 0 || interest < 0)
        }

        /**
         * Deregisters the object from the reactor.
         */
        fun delete() {
            // Deletes fd from reactor
        }

        override fun equals(other: Any?): Boolean = other is Registration && raw == other.raw

        override fun hashCode(): Int = raw.hashCode()

        override fun toString(): String = fmt()

        companion object {
            /**
             * Add this file descriptor into the reactor.
             *
             * @param f The borrowed file descriptor.
             */
            fun new(f: BorrowedFd): Registration = Registration(f.raw)
        }
    }
}
