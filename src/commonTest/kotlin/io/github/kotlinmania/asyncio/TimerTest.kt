package io.github.kotlinmania.asyncio

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource

class TimerTest {

    @Test
    fun testNeverTimer() {
        val timer = Timer.never()
        assertFalse(timer.willFire())
    }

    @Test
    fun testAfterTimerWillFire() {
        val timer = Timer.after(1.seconds)
        assertTrue(timer.willFire())
    }

    @Test
    fun testSetAfter() {
        val timer = Timer.after(10.seconds)
        assertTrue(timer.willFire())
        timer.setAfter(20.milliseconds)
        assertTrue(timer.willFire())
    }

    @Test
    fun testInterval() {
        val timer = Timer.interval(50.milliseconds)
        assertTrue(timer.willFire())
    }

    @Test
    fun testIntervalAt() {
        val start = TimeSource.Monotonic.markNow() + 100.milliseconds
        val timer = Timer.intervalAt(start, 50.milliseconds)
        assertTrue(timer.willFire())
    }

    @Test
    fun testClear() {
        val timer = Timer.after(1.seconds)
        assertTrue(timer.willFire())
        timer.clear()
        assertFalse(timer.willFire())
    }
}
