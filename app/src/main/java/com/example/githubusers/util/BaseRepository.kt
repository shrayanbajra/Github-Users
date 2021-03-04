package com.example.githubusers.util

import com.haroldadmin.cnradapter.NetworkResponse

abstract class BaseRepository {

    suspend fun <T : Any> makeSafeApiCall(apiCall: suspend () -> NetworkResponse<T, ErrorResponse>): ResultWrapper<T> {

        val networkResponse = apiCall()
        return NetworkResponseHandler.handle(networkResponse = networkResponse)

    }

}