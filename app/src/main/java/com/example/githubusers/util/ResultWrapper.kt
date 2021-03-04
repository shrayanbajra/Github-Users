package com.example.githubusers.util

sealed class ResultWrapper<out T : Any?> {

    data class Success<out T : Any?>(val data: T) : ResultWrapper<T>()
    data class Error(val msg: String) : ResultWrapper<Nothing>()

}