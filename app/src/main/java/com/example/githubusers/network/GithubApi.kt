package com.example.githubusers.network

import com.example.githubusers.data.UserResponse
import retrofit2.http.GET

interface GithubApi {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>

}