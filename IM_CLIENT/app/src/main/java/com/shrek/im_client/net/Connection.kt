package com.shrek.im_client.net

import android.util.Log
import com.shrek.im_client.log.IMLog
import java.net.Socket

class Connection : IConnection {

    private var socket: Socket? = null

    override suspend fun connected(address: String, port: Int) {

        try {
            if (socket == null) {
                socket = Socket(address, port)
            }
            socket?.let {
                val inputStream = it.getInputStream()
                val outputStream = it.getOutputStream()

                while (true) {
                    outputStream.write("Hello, server!".toByteArray())

                    val buffer = ByteArray(1024)
                    val bytesRead = inputStream.read(buffer)
                    val message = String(buffer, 0, bytesRead)

                    IMLog.d("Received message from server: $message")
                    Thread.sleep(3000)
                }
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
}