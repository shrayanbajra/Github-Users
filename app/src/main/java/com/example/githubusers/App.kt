package com.example.githubusers

import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        ObjectBox.init(this)
        Stetho.initializeWithDefaults(this)

    }

}