package io.github.kotlinmania.asyncio.os

import io.github.kotlinmania.asyncio.runSync
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue

class OsTest {
    @Test
    fun testKqueueSignalAndExit() {
        runSync {
            val sig = Signal(9)
            assertEquals(9, sig.signalNumber)

            val exit = Exit.fromPid(1234)
            assertEquals(1234, exit.pid)

            assertFailsWith<IllegalArgumentException> {
                Exit(0)
            }

            val filter = Filter.new(sig)
            assertEquals(sig, filter.getRef())
            assertEquals(sig, filter.getMut())
            assertEquals(sig, filter.asRef())
            assertEquals(sig, filter.asMut())
            assertTrue(filter.pollReady())
            assertTrue(filter.poll())
            filter.ready()

            val inner = filter.intoInner()
            assertEquals(sig, inner)
            assertFailsWith<IllegalStateException> {
                filter.getRef()
            }
        }
    }

    @Test
    fun testWindowsWaitable() {
        runSync {
            val handle = OwnedHandle(100L)
            assertEquals(100L, handle.raw)
            assertEquals("OwnedHandle(raw=100)", handle.toString())

            val borrowed = BorrowedHandle(200L)
            assertEquals(200L, borrowed.raw)
            assertEquals("BorrowedHandle(raw=200)", borrowed.toString())

            val waitable = Waitable.new(handle)
            assertEquals(handle, waitable.getRef())
            assertEquals(handle, waitable.asRef())
            assertEquals(handle, waitable.getMut())
            assertTrue(waitable.pollReady())
            assertTrue(waitable.poll())
            waitable.ready()

            val inner = waitable.intoInner()
            assertEquals(handle, inner)
            assertFailsWith<IllegalStateException> {
                waitable.getRef()
            }
        }
    }

    @Test
    fun testUnixBorrowedFd() {
        val fd = BorrowedFd(5L)
        assertEquals(5L, fd.raw)
        assertEquals("BorrowedFd(raw=5)", fd.toString())
        assertNull(reactorFd())
    }
}
