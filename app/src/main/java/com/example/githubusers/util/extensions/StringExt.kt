package com.example.githubusers.util.extensions

import com.example.githubusers.util.Constants

fun String?.getNotAvailableIfNullOrBlank(): String {

    return if (this.isNullOrBlank())
        Constants.NOT_AVAILABLE
    else
        this

}