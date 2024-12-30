package com.shrek.im_client.net

import android.util.Log
import java.net.Socket

class Connection {

    suspend fun connected(address: String, port: Int) {
        try {
            val socket = Socket(address, port)

            val inputStream = socket.getInputStream()
            val outputStream = socket.getOutputStream()

            while (true) {
                outputStream.write("Hello, server!".toByteArray())

                val buffer = ByteArray(1024)
                val bytesRead = inputStream.read(buffer)
                val message = String(buffer, 0, bytesRead)

                Log.d("shrek", "Received message from server: $message")
                Thread.sleep(3000)
            }

            //socket.close()
        } catch (e: Exception) {
            Log.d("shrek","Error connecting to server: ${e}")
        }
    }
}