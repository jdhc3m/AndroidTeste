package com.example.prigui.androidtest.remote.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by prigui on 13/08/2018.
 */
class NetworkConnection {
    companion object {
        @SuppressLint("MissingPermission")
        fun isNetworkConnected(context: Context): Boolean {

            var connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectionManager.activeNetworkInfo

            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }
    }
}