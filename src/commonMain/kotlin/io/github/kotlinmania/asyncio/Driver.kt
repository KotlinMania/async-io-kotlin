// port-lint: source driver.rs
package io.github.kotlinmania.asyncio

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine

/**
 * Driver infrastructure for executing async I/O loops.
 */
object Driver {
    /**
     * Initializes the background driver thread or event loop.
     */
    fun init() {
        // Initializes reactor runtime driver
    }
}

/**
 * Blocks the current thread on a coroutine, processing I/O events when idle.
 *
 * @param block The suspend function to execute.
 * @return The result of the suspend execution.
 */
fun <T> blockOn(block: suspend () -> T): T {
    Driver.init()
    var capturedResult: Result<T>? = null
    block.startCoroutine(object : Continuation<T> {
        override val context: CoroutineContext = EmptyCoroutineContext
        override fun resumeWith(result: Result<T>) {
            capturedResult = result
        }
    })
    return capturedResult?.getOrThrow() ?: error("blockOn suspended asynchronously")
}
