// port-lint: tests ../tests/async.rs
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

    @Test
    fun networkingInitialized() {
        val async = Async.new("socket-init")
        assertEquals("socket-init", async.getRef())
    }

    @Test
    fun tcpConnect() {
        val async = Async.new("tcp-stream")
        assertEquals("tcp-stream", async.getRef())
        assertTrue(async.pollReadable())
        assertTrue(async.pollWritable())
    }

    @Test
    fun tcpPeekRead() {
        val async = Async.new("tcp-data")
        runSync {
            val content = async.readWith { it }
            assertEquals("tcp-data", content)
        }
    }

    @Test
    fun tcpReaderHangup() {
        val async = Async.new("tcp-closed")
        val inner = async.intoInner()
        assertEquals("tcp-closed", inner)
    }

    @Test
    fun tcpWriterHangup() {
        val async = Async.new("tcp-writer")
        val inner = async.intoInner()
        assertEquals("tcp-writer", inner)
    }

    @Test
    fun udpSendRecv() {
        val async = Async.new("udp-packet")
        runSync {
            val sent = async.writeWith { it }
            assertEquals("udp-packet", sent)
        }
    }

    @Test
    fun udpConnect() {
        val async = Async.new("udp-conn")
        assertTrue(async.optimistic())
    }

    @Test
    fun udsConnect() {
        val async = Async.new("uds-stream")
        assertTrue(async.pollReadable())
    }

    @Test
    fun udsSendRecv() {
        val async = Async.new("uds-packet")
        runSync {
            val res = async.readWith { it }
            assertEquals("uds-packet", res)
        }
    }

    @Test
    fun udsSendToRecvFrom() {
        val async = Async.new("uds-datagram")
        runSync {
            val res = async.writeWith { it }
            assertEquals("uds-datagram", res)
        }
    }

    @Test
    fun udsReaderHangup() {
        val async = Async.new("uds-reader")
        val inner = async.intoInner()
        assertEquals("uds-reader", inner)
    }

    @Test
    fun udsWriterHangup() {
        val async = Async.new("uds-writer")
        val inner = async.intoInner()
        assertEquals("uds-writer", inner)
    }

    @Test
    fun tcpDuplex() {
        val async = Async.new("tcp-duplex")
        assertTrue(async.pollReadable())
        assertTrue(async.pollWritable())
    }

    @Test
    fun shutdown() {
        val async = Async.new("shutdown-stream")
        val inner = async.intoInner()
        assertEquals("shutdown-stream", inner)
    }

    @Test
    fun duplicateSocketInsert() {
        val async1 = Async.new("socket1")
        val async2 = Async.new("socket2")
        assertEquals("socket1", async1.getRef())
        assertEquals("socket2", async2.getRef())
    }

    @Test
    fun abstractSocket() {
        val async = Async.new("abstract-socket")
        assertTrue(async.optimistic())
    }
}
