package com.example.githubusers.network

import com.example.githubusers.data.UserResponse
import com.example.githubusers.util.ErrorResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET

interface GithubApi {

    @GET("users")
    suspend fun getUsers(): NetworkResponse<List<UserResponse>, ErrorResponse>

}