package com.shrek.im_client.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrek.im_client.log.IMLog
import com.shrek.im_client.net.ConnectionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private var connectionJob: Job? = null
    fun connect() {
        connectionJob = viewModelScope.launch(Dispatchers.IO) {
            ConnectionManager.startMessageConnection()
        }
    }

    fun close() {
        connectionJob?.cancel()
        viewModelScope.launch(Dispatchers.IO) {
            ConnectionManager.closeMessageConnection()
        }
    }

    fun loopRead() {
        viewModelScope.launch(Dispatchers.IO) {
            connectionJob?.join()
            var conn = ConnectionManager.getMessageConnection()
            val buffer = ByteArray(1024)
            while (conn != null) {
                conn.write("test message".toByteArray())
                val count = conn.read(buffer)
                val message = String(buffer, 0, count)
                IMLog.d("Received message from server: $message")
                conn = ConnectionManager.getMessageConnection()
                //delay(3000)
            }
        }
    }
}