package com.example.githubusers.util


import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @SerializedName("message")
    val message: String?

)