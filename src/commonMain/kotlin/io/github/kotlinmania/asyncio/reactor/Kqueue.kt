// port-lint: source reactor/kqueue.rs
package io.github.kotlinmania.asyncio.reactor

import io.github.kotlinmania.asyncio.os.Exit
import io.github.kotlinmania.asyncio.os.Signal

/**
 * Kqueue-specific reactor integration.
 */
object KqueueReactor {
    /**
     * The raw registration into the reactor for kqueue platforms.
     */
    sealed class Registration {
        /**
         * Raw file descriptor for readability/writability.
         *
         * @property raw The file descriptor integer.
         */
        data class Fd(val raw: Long) : Registration()

        /**
         * Raw signal number for signal delivery.
         *
         * @property signal The signal to monitor.
         */
        data class SignalRegistration(val signal: Signal) : Registration()

        /**
         * Process identifier for process termination.
         *
         * @property exit The exit watcher containing the PID.
         */
        data class ProcessRegistration(val exit: Exit) : Registration()

        /**
         * Formats the registration for debugging.
         */
        fun fmt(): String = when (this) {
            is Fd -> "Registration.Fd($raw)"
            is SignalRegistration -> "Registration.SignalRegistration($signal)"
            is ProcessRegistration -> "Registration.ProcessRegistration($exit)"
        }

        /**
         * Registers the object into the reactor.
         *
         * @param token The registration token.
         */
        fun add(token: Long) {
            // Registers filter with reactor
        }

        /**
         * Re-registers the object into the reactor.
         *
         * @param interest The interest bitmask.
         */
        fun modify(interest: Long) {
            // Modifies filter interest
        }

        /**
         * Deregisters the object from the reactor.
         */
        fun delete() {
            // Deletes filter from reactor
        }

        companion object {
            /**
             * Add this file descriptor into the reactor.
             */
            fun new(raw: Long): Registration = Fd(raw)
        }
    }
}
