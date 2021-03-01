package com.example.githubusers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubRetrofitClient {

    fun getInstance(): GithubApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(GithubApi::class.java)
    }

}