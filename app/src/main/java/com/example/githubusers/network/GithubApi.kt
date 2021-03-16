package com.example.githubusers.network

import com.example.githubusers.data.ProfileResponse
import com.example.githubusers.data.UserResponse
import com.example.githubusers.util.ErrorResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users")
    suspend fun getUsers(): NetworkResponse<List<UserResponse>, ErrorResponse>

    @GET("/users/{username}")
    suspend fun getUserProfile(
        @Path("username") username: String
    ): NetworkResponse<List<ProfileResponse>, ErrorResponse>

}