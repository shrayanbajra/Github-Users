package com.example.githubusers.util

import com.example.githubusers.R
import com.haroldadmin.cnradapter.NetworkResponse
import timber.log.Timber

abstract class BaseRepository {

    suspend fun <T : Any> makeSafeApiCall(apiCall: suspend () -> NetworkResponse<T, ErrorResponse>): ResultWrapper<T> {

        return if (NetworkUtils.hasNoInternetConnection()) {

            Timber.d("No internet connection found")
            ResultWrapper.NoInternetConnection()

        } else {

            Timber.d("Internet connection found: proceeding to call api")
            val networkResponse = apiCall()
            NetworkResponseHandler.handle(networkResponse = networkResponse)

        }


    }

}