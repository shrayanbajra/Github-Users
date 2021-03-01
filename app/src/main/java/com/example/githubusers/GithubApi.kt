package com.example.githubusers

import retrofit2.http.GET

interface GithubApi {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>

}