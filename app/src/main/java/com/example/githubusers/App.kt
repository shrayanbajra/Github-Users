package com.example.githubusers

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.example.githubusers.util.NetworkUtils
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {

    companion object {
        private const val TAG = "App"
    }

    @SuppressLint("LogNotTimber")
    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "Instantiating App")

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        NetworkUtils.init(this)

    }

}