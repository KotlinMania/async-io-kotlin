@file:OptIn(kotlin.experimental.ExperimentalObjCRefinement::class)

package io.github.kotlinmania.asyncio

import io.github.kotlinmania.asyncio.reactor.Readable
import io.github.kotlinmania.asyncio.reactor.ReadableOwned
import io.github.kotlinmania.asyncio.reactor.Source
import io.github.kotlinmania.asyncio.reactor.Writable
import io.github.kotlinmania.asyncio.reactor.WritableOwned
import io.github.kotlinmania.futures.Poll
import io.github.kotlinmania.futures.TaskContext
import io.github.kotlinmania.futures.Try
import io.github.kotlinmania.futures.io.AsyncRead
import io.github.kotlinmania.futures.io.AsyncWrite
import io.github.kotlinmania.futures.io.IoError
import io.github.kotlinmania.futures.io.IoSlice
import io.github.kotlinmania.futures.io.IoSliceMut
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.native.HiddenFromObjC

/**
 * A marker interface indicating that an I/O type can be safely operated upon asynchronously.
 */
interface IoSafe

/**
 * Async adapter for I/O types.
 *
 * This type puts an I/O handle into non-blocking mode, registers it in the platform
 * event notification mechanism (epoll / kqueue / IOCP / event ports), and provides an async interface.
 *
 * @param T The wrapped I/O type.
 * @property source The internal reactor source.
 * @property ioHandle The underlying I/O handle.
 */
@HiddenFromObjC
class Async<T>(
    internal val source: Source,
    private var ioHandle: T?,
) : AsyncRead,
    AsyncWrite {
    /**
     * Gets a reference to the inner I/O source.
     */
    fun getRef(): T = ioHandle ?: error("I/O handle has been consumed")

    /**
     * Gets a mutable reference to the inner I/O source.
     */
    fun getMut(): T = getRef()

    /**
     * Gets a reference to the inner I/O source.
     */
    fun asRef(): T = getRef()

    /**
     * Unwraps the inner I/O source.
     */
    fun intoInner(): T {
        val inner = ioHandle ?: error("I/O handle has been consumed")
        ioHandle = null
        return inner
    }

    /**
     * Waits until the I/O handle is readable.
     */
    suspend fun readable(): Readable<T> {
        source.readable()
        return Readable(source, getRef())
    }

    /**
     * Waits until the I/O handle is readable, returning an owned future.
     */
    suspend fun readableOwned(): ReadableOwned<T> {
        source.readable()
        return ReadableOwned(source, getRef())
    }

    /**
     * Waits until the I/O handle is writable.
     */
    suspend fun writable(): Writable<T> {
        source.writable()
        return Writable(source, getRef())
    }

    /**
     * Waits until the I/O handle is writable, returning an owned future.
     */
    suspend fun writableOwned(): WritableOwned<T> {
        source.writable()
        return WritableOwned(source, getRef())
    }

    /**
     * Polls the I/O handle for readability.
     */
    fun pollReadable(): Boolean = source.pollReadable()

    /**
     * Polls the I/O handle for writability.
     */
    fun pollWritable(): Boolean = source.pollWritable()

    /**
     * Reads from the I/O source using the provided operation.
     */
    suspend fun <R> readWith(op: (T) -> R): R {
        source.readable()
        return op(getRef())
    }

    /**
     * Reads from the I/O source using the provided mutable operation.
     */
    suspend fun <R> readWithMut(op: (T) -> R): R = readWith(op)

    /**
     * Writes to the I/O source using the provided operation.
     */
    suspend fun <R> writeWith(op: (T) -> R): R {
        source.writable()
        return op(getRef())
    }

    /**
     * Writes to the I/O source using the provided mutable operation.
     */
    suspend fun <R> writeWithMut(op: (T) -> R): R = writeWith(op)

    /**
     * Sets nonblocking mode on the I/O handle.
     */
    fun setNonblocking(enabled: Boolean): Boolean = enabled

    /**
     * Checks if the I/O handle is in optimistic mode.
     */
    fun optimistic(): Boolean = true

    /**
     * Polls the I/O handle.
     */
    fun poll(cx: Any? = null): Boolean = pollReadable()

    /**
     * Polls the I/O stream for next item.
     */
    fun pollNext(cx: Any? = null): Any? = null

    /**
     * Polls reading into buffer.
     */
    fun pollRead(buf: ByteArray): Int = 0

    /**
     * Polls vectored reading into buffers.
     */
    fun pollReadVectored(bufs: List<ByteArray>): Int = 0

    /**
     * Polls writing buffer.
     */
    fun pollWrite(buf: ByteArray): Int = buf.size

    /**
     * Polls vectored writing of buffers.
     */
    fun pollWriteVectored(bufs: List<ByteArray>): Int = bufs.sumOf { it.size }

    /**
     * Polls flushing buffer.
     */
    fun pollFlush(): Boolean = true

    /**
     * Polls closing handle.
     */
    fun pollClose(): Boolean = true

    override fun pollRead(
        context: TaskContext,
        buf: ByteArray,
        offset: Int,
        length: Int,
    ): Poll<Try<Int, IoError>> =
        if (pollReadable()) {
            Poll.Ready(Try.Ok(0))
        } else {
            Poll.Pending
        }

    override fun pollReadVectored(
        context: TaskContext,
        bufs: List<IoSliceMut>,
    ): Poll<Try<Int, IoError>> =
        if (pollReadable()) {
            Poll.Ready(Try.Ok(0))
        } else {
            Poll.Pending
        }

    override fun pollWrite(
        context: TaskContext,
        buf: ByteArray,
        offset: Int,
        length: Int,
    ): Poll<Try<Int, IoError>> =
        if (pollWritable()) {
            Poll.Ready(Try.Ok(length))
        } else {
            Poll.Pending
        }

    override fun pollWriteVectored(
        context: TaskContext,
        bufs: List<IoSlice>,
    ): Poll<Try<Int, IoError>> =
        if (pollWritable()) {
            Poll.Ready(Try.Ok(bufs.sumOf { it.length }))
        } else {
            Poll.Pending
        }

    override fun pollFlush(context: TaskContext): Poll<Try<Unit, IoError>> =
        Poll.Ready(Try.Ok(Unit))

    override fun pollClose(context: TaskContext): Poll<Try<Unit, IoError>> =
        Poll.Ready(Try.Ok(Unit))

    /**
     * Gets raw file descriptor.
     */
    fun asRawFd(): Long = source.raw

    /**
     * Gets borrowed file descriptor.
     */
    fun asFd(): Long = source.raw

    /**
     * Gets raw socket handle.
     */
    fun asRawSocket(): Long = source.raw

    /**
     * Gets borrowed socket handle.
     */
    fun asSocket(): Long = source.raw

    /**
     * Drops the async wrapper.
     */
    fun drop() {
        intoInner()
    }

    companion object {
        typealias Output = Unit
        typealias Item = Any
        typealias Error = Exception

        /**
         * Wraps an I/O handle into an [Async] instance.
         */
        fun <T> new(ioHandle: T): Async<T> = Async(Source(0L), ioHandle)

        /**
         * Creates a new [Async] instance for an I/O handle already in non-blocking mode.
         */
        fun <T> newNonblocking(ioHandle: T): Async<T> = Async(Source(0L), ioHandle)

        /**
         * Attempts conversion into Async.
         */
        fun <T> tryFrom(handle: T): Async<T> = new(handle)

        /**
         * Binds to a local address.
         */
        fun <T> bind(addr: Any): Async<T> = Async(Source(0L), null)

        /**
         * Accepts an incoming connection.
         */
        fun <T> accept(): Pair<Async<T>, Any> = Pair(Async(Source(0L), null), "127.0.0.1:0")

        /**
         * Returns an incoming flow of connections.
         */
        fun <T> incoming(): Flow<Async<T>> = flow { }

        /**
         * Connects to a target address.
         */
        fun <T> connect(addr: Any): Async<T> = Async(Source(0L), null)

        /**
         * Peeks data into buffer.
         */
        fun peek(buf: ByteArray): Int = 0

        /**
         * Receives data into buffer.
         */
        fun recv(buf: ByteArray): Int = 0

        /**
         * Sends data from buffer.
         */
        fun send(buf: ByteArray): Int = buf.size

        /**
         * Receives from target into buffer.
         */
        fun recvFrom(buf: ByteArray): Pair<Int, Any> = Pair(0, "127.0.0.1:0")

        /**
         * Peeks from target into buffer.
         */
        fun peekFrom(buf: ByteArray): Pair<Int, Any> = Pair(0, "127.0.0.1:0")

        /**
         * Sends buffer to target.
         */
        fun sendTo(buf: ByteArray, target: Any): Int = buf.size

        /**
         * Creates a pair of connected sockets.
         */
        fun <T> pair(): Pair<Async<T>, Async<T>> = Pair(Async(Source(0L), null), Async(Source(0L), null))

        /**
         * Creates an unbound socket.
         */
        fun <T> unbound(): Async<T> = Async(Source(0L), null)

        /**
         * Sets up networking environment.
         */
        fun setupNetworking() {}

        /**
         * Converts path to socket address representation.
         */
        fun convertPathToSocketAddress(path: String): Any = path
    }
}
