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
            assertEquals("data-read", readyRead.await())

            val readyWrite = Ready(source, "data-write", 1)
            assertEquals("data-write", readyWrite.await())
        }
    }
}
