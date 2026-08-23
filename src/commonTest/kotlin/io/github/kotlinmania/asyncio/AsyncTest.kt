package io.github.kotlinmania.asyncio

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class AsyncTest {
    @Test
    fun testAsyncNewAndGetRef() {
        val async = Async.new("hello-socket")
        assertEquals("hello-socket", async.getRef())
        assertEquals("hello-socket", async.getMut())
        assertEquals("hello-socket", async.asRef())
    }

    @Test
    fun testAsyncNewNonblocking() {
        val async = Async.newNonblocking(12345)
        assertEquals(12345, async.getRef())
        assertTrue(async.pollReadable())
        assertTrue(async.pollWritable())
        assertTrue(async.setNonblocking(true))
        assertTrue(async.optimistic())
    }

    @Test
    fun testAsyncIntoInner() {
        val async = Async.new("consume-me")
        val inner = async.intoInner()
        assertEquals("consume-me", inner)
        assertFailsWith<IllegalStateException> {
            async.getRef()
        }
    }

    @Test
    fun testAsyncReadWithAndWriteWith() {
        runSync {
            val async = Async.new(StringBuilder("init"))
            val len =
                async.readWith { sb ->
                    sb.length
                }
            assertEquals(4, len)

            val lenMut =
                async.readWithMut { sb ->
                    sb.length
                }
            assertEquals(4, lenMut)

            val appended =
                async.writeWith { sb ->
                    sb.append("-updated")
                    sb.toString()
                }
            assertEquals("init-updated", appended)

            val appendedMut =
                async.writeWithMut { sb ->
                    sb.append("-again")
                    sb.toString()
                }
            assertEquals("init-updated-again", appendedMut)
        }
    }

    @Test
    fun testAsyncReadableAndWritableAwaits() {
        runSync {
            val async = Async.new("stream-handle")
            val readable = async.readable()
            assertEquals("stream-handle", readable.await())

            val readableOwned = async.readableOwned()
            assertEquals("stream-handle", readableOwned.await())

            val writable = async.writable()
            assertEquals("stream-handle", writable.await())

            val writableOwned = async.writableOwned()
            assertEquals("stream-handle", writableOwned.await())
        }
    }
}
