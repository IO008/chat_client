package com.shrek.im_client.net

import com.shrek.im_client.log.IMLog
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

class Connection : IConnection {

    private var socket: Socket? = null
    private var inputStream: InputStream? = null
    private var outputStream: OutputStream? = null

    override suspend fun connected(address: String, port: Int) {

        try {
            if (socket == null) {
                socket = Socket(address, port)
            }
            socket?.let {
                inputStream = it.getInputStream()
                outputStream = it.getOutputStream()
            }

        } catch (e: Exception) {
            IMLog.d("Error connecting to server: $e")
        }
    }

    override suspend fun close() {
        try {
            socket?.close()
        } catch (e: Exception) {
            IMLog.d("Error closing connection: $e")
        }
    }

    override suspend fun read(buffer: ByteArray): Int {
        var result = 0
        runCatching {
            result = inputStream?.read(buffer) ?: 0
        }
        return result
    }

    override suspend fun write(data: ByteArray) {
        runCatching {
            outputStream?.write(data)
        }
    }
}