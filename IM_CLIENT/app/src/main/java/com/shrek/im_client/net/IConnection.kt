package com.shrek.im_client.net

interface IConnection {
    suspend fun connected(address: String, port: Int)

    suspend fun close()
}