package com.example.githubusers.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.githubusers.App

object NetworkUtils {

    private lateinit var applicationContext: Context

    fun init(applicationContext: Context){

        this.applicationContext = applicationContext

    }

    fun hasNoInternetConnection(): Boolean {

        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo

        return activeNetwork?.isConnectedOrConnecting != true

    }
}