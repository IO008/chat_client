package com.shrek.im_client.net

interface IConnection {
    suspend fun connected(address: String, port: Int)

    suspend fun close()

    suspend fun read(buffer: ByteArray): Int

    suspend fun write(data: ByteArray)
}