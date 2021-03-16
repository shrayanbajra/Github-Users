package com.example.githubusers

import android.content.Context
import com.example.githubusers.data.MyObjectBox
import io.objectbox.BoxStore

object ObjectBox {

    lateinit var boxStore: BoxStore
        private set

    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
            .androidContext(context)
            .build()
    }

}