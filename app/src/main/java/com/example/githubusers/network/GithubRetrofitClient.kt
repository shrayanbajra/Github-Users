package com.example.githubusers.network

import com.example.githubusers.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubRetrofitClient {

    fun getInstance(): GithubApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
        return retrofit.create(GithubApi::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .build()

    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor

    }

}