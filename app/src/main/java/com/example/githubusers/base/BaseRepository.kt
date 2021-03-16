package com.example.githubusers.base

import com.example.githubusers.util.ErrorResponse
import com.example.githubusers.util.NetworkResponseHandler
import com.example.githubusers.util.ResultWrapper
import com.haroldadmin.cnradapter.NetworkResponse

abstract class BaseRepository {

    suspend fun <T : Any> makeSafeApiCall(apiCall: suspend () -> NetworkResponse<T, ErrorResponse>): ResultWrapper<T> {

        val networkResponse = apiCall()
        return NetworkResponseHandler.handle(networkResponse = networkResponse)

    }

}