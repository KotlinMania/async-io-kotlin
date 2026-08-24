// port-lint: tests reactor.rs
package io.github.kotlinmania.asyncio.reactor

import io.github.kotlinmania.asyncio.runSync
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.TimeSource

class ReactorTest {
    @Test
    fun testReactorSingleton() {
        val reactor1 = Reactor.get()
        val reactor2 = Reactor.get()
        assertEquals(reactor1, reactor2)
    }

    @Test
    fun testReactorInsertAndRemoveIo() {
        val reactor = Reactor.get()
        val source = reactor.insertIo(42L)
        assertEquals(42L, source.raw)
        assertEquals(42L, source.key)
        assertTrue(source.pollReadable())
        assertTrue(source.pollWritable())
        assertTrue(source.pollReady(0))
        assertTrue(source.pollReady(1))
        reactor.removeIo(source)
    }

    @Test
    fun testReactorTimers() {
        runSync {
            val reactor = Reactor.get()
            val deadline = TimeSource.Monotonic.markNow() + 50.milliseconds
            val id = reactor.insertTimer(deadline)
            assertTrue(id > 0L)
            val dur = reactor.processTimers()
            assertNotNull(dur)
            reactor.removeTimer(id)
        }
    }

    @Test
    fun testReactorLockAndReact() {
        val reactor = Reactor.get()
        val lock = reactor.lock()
        lock.react(10.milliseconds)

        val tryLock = reactor.tryLock()
        assertNotNull(tryLock)
        tryLock.react()
    }

    @Test
    fun testDirection() {
        val dir = Direction(tick = 1L)
        assertTrue(dir.isEmpty())
        var called = false
        dir.register { called = true }
        assertTrue(!dir.isEmpty())
        val list = mutableListOf<() -> Unit>()
        dir.drainInto(list)
        assertTrue(dir.isEmpty())
        assertEquals(1, list.size)
        list[0].invoke()
        assertTrue(called)
    }

    @Test
    fun testReadyHelper() {
        runSync {
            val source = Source(10L)
            val readyRead = Ready(source, "data-read", 0)
            assertTrue(readyRead.poll())
            assertTrue(readyRead.ready())
            assertEquals("data-read", readyRead.await())

            val readyWrite = Ready(source, "data-write", 1)
            assertTrue(readyWrite.poll())
            assertTrue(readyWrite.ready())
            assertEquals("data-write", readyWrite.await())
        }
    }

    @Test
    fun testReadableAndWritableHelpers() {
        runSync {
            val source = Source(20L)

            val r = Source.readable(source, "handle-r")
            assertTrue(r.poll())
            assertTrue(r.ready())
            assertEquals("handle-r", r.await())

            val ro = Source.readableOwned(source, "handle-ro")
            assertTrue(ro.poll())
            assertTrue(ro.ready())
            assertEquals("handle-ro", ro.await())

            val w = Source.writable(source, "handle-w")
            assertTrue(w.poll())
            assertTrue(w.ready())
            assertEquals("handle-w", w.await())

            val wo = Source.writableOwned(source, "handle-wo")
            assertTrue(wo.poll())
            assertTrue(wo.ready())
            assertEquals("handle-wo", wo.await())

            val ready = Source.ready(source, "handle-ready", 0)
            assertEquals("handle-ready", ready.await())
        }
    }
}
