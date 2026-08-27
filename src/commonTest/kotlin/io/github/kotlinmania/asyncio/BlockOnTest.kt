// port-lint: tests tests/block_on.rs
package io.github.kotlinmania.asyncio

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BlockOnTest {
    @Test
    fun testBlockOnBasic() {
        val result =
            blockOn {
                42
            }
        assertEquals(42, result)
    }

    @Test
    fun testBlockOnNonSuspending() {
        val result =
            blockOn {
                val timer = Timer.never()
                timer.willFire()
            }
        assertFalse(result)
    }

    @Test
    fun doesntPollAfterReady() {
        var polled = false
        val result =
            blockOn {
                polled = true
                42
            }
        assertEquals(42, result)
        assertTrue(polled)
    }

    @Test
    fun recursiveWakersAreDifferent() {
        val result =
            blockOn {
                blockOn {
                    99
                }
            }
        assertEquals(99, result)
    }

    @Test
    fun innerCannotWakeOuter() {
        var innerRan = false
        val result =
            blockOn {
                blockOn {
                    innerRan = true
                }
                "outer"
            }
        assertEquals("outer", result)
        assertTrue(innerRan)
    }

    @Test
    fun outerCannotWakeInner() {
        var outerRan = false
        val result =
            blockOn {
                outerRan = true
                blockOn {
                    "inner"
                }
            }
        assertEquals("inner", result)
        assertTrue(outerRan)
    }

    @Test
    fun firstCannotWakeSecond() {
        val res1 = blockOn { 1 }
        val res2 = blockOn { 2 }
        assertEquals(1, res1)
        assertEquals(2, res2)
    }
}
