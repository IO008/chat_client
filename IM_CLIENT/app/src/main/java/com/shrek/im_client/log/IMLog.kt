package com.shrek.im_client.log

import android.util.Log

class IMLog {

    companion object {
        private const val TAG = "IM_CLIENT"

        fun d(msg: String) {
            Log.d(TAG, msg)
        }

        fun e(e: Exception) {
            Log.e(TAG, e.message.toString(), e)
        }
    }
}