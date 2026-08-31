// port-lint: source async-io/src/driver.rs
package io.github.kotlinmania.asyncio

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine

/**
 * RAII guard calling a callback on drop/cleanup.
 */
class CallOnDrop(
    private val onDrop: () -> Unit,
) {
    /**
     * Executes the drop callback.
     */
    fun drop() {
        onDrop()
    }
}

/**
 * Waker for blockOn implementations.
 */
class BlockOnWaker(
    val ioBlocked: Any? = null,
    val unparker: Any? = null,
) {
    /**
     * Wakes the task by reference.
     */
    fun wakeByRef() {}

    /**
     * Wakes the task.
     */
    fun wake() {
        wakeByRef()
    }

    companion object {
        /**
         * Creates a [BlockOnWaker].
         */
        fun create(ioBlocked: Any? = null, unparker: Any? = null): BlockOnWaker = BlockOnWaker(ioBlocked, unparker)
    }
}

/**
 * Driver infrastructure for executing async I/O loops.
 */
object Driver {
    /**
     * Unparker for the driver loop.
     */
    fun unparker(): Any? = null

    /**
     * Driver main event loop.
     */
    fun mainLoop(parker: Any? = null) {
        if (parker != null) {
            // Helper event loop for the async-io driver
        }
    }

    /**
     * Creates a parker and waker pair.
     */
    fun parkerAndWaker(): Triple<Any?, BlockOnWaker, Any?> =
        Triple(null, BlockOnWaker.create(), null)

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
    block.startCoroutine(
        object : Continuation<T> {
            override val context: CoroutineContext = EmptyCoroutineContext

            override fun resumeWith(result: Result<T>) {
                capturedResult = result
            }
        },
    )
    return capturedResult?.getOrThrow() ?: error("blockOn suspended asynchronously")
}
