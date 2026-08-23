package io.github.kotlinmania.asyncio

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class BlockOnTest {

    @Test
    fun testBlockOnBasic() {
        val result = blockOn {
            42
        }
        assertEquals(42, result)
    }

    @Test
    fun testBlockOnNonSuspending() {
        val result = blockOn {
            val timer = Timer.never()
            timer.willFire()
        }
        assertFalse(result)
    }
}
