package com.example.githubusers.util

import androidx.annotation.StringRes
import com.example.githubusers.R

sealed class ResultWrapper<out T : Any?> {

    data class Success<out T : Any?>(val data: T) : ResultWrapper<T>()
    data class Error(val msg: String) : ResultWrapper<Nothing>()
    data class NoInternetConnection(
        @StringRes val msg: Int = R.string.no_internet_connection
    ) : ResultWrapper<Nothing>()

}