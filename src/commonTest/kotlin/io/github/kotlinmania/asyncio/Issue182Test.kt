// port-lint: tests ../tests/issue_182.rs
package io.github.kotlinmania.asyncio

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Issue182Test {
    @Test
    fun networkingInitialized() {
        val result =
            blockOn {
                val asyncHandle = Async.new("simulated-socket")
                val isReadable = asyncHandle.pollReadable()
                assertTrue(isReadable)
                asyncHandle.intoInner()
            }
        assertEquals("simulated-socket", result)
    }
}
