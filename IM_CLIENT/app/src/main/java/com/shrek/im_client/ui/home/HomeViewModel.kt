package com.shrek.im_client.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrek.im_client.net.Connection
import com.shrek.im_client.net.ConnectionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    fun connect() {
        viewModelScope.launch(Dispatchers.IO) {
            ConnectionManager.startMessageConnection()
        }
    }

    fun close() {
        viewModelScope.launch(Dispatchers.IO) {
            ConnectionManager.closeMessageConnection()
        }
    }
}