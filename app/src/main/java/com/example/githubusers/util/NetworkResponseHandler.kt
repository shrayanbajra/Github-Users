package com.example.githubusers.util

import com.haroldadmin.cnradapter.NetworkResponse
import timber.log.Timber

object NetworkResponseHandler {

    fun <T : Any> handle(networkResponse: NetworkResponse<T, ErrorResponse>): ResultWrapper<T> {

        return when (networkResponse) {

            is NetworkResponse.Success -> handleSuccessfulNetworkResponse(networkResponse)

            is NetworkResponse.ServerError -> handleServerError(networkResponse)

            is NetworkResponse.NetworkError -> handleNetworkError(networkResponse)

            is NetworkResponse.UnknownError -> handleUnknownError(networkResponse)

        }

    }

    private fun <T : Any> handleSuccessfulNetworkResponse(networkResponse: NetworkResponse.Success<T>): ResultWrapper.Success<T> {
        Timber.d("Successful Response")

        return ResultWrapper.Success(data = networkResponse.body)
    }

    private fun handleServerError(networkResponse: NetworkResponse.ServerError<ErrorResponse>): ResultWrapper.Error {
        Timber.d("Server Error")

        val networkResponseMessage = networkResponse.body?.message

        val errorMessage =
            if (networkResponseMessage.isNullOrBlank())
                HttpStatusCodes.getErrorMessage(code = networkResponse.code)
            else
                networkResponseMessage

        return ResultWrapper.Error(msg = errorMessage)
    }

    private fun handleNetworkError(networkResponse: NetworkResponse.NetworkError): ResultWrapper.Error {
        Timber.d("Network Error")

        val message = networkResponse.error.message ?: "Network Error"

        val hasConnectionTimedOut = message.equals("timeout", ignoreCase = true)
        val errorMessage =
            if (hasConnectionTimedOut)
                "Connection Timed Out"
            else
                message

        return ResultWrapper.Error(msg = errorMessage)
    }

    private fun handleUnknownError(networkResponse: NetworkResponse.UnknownError): ResultWrapper.Error {
        Timber.d("Unknown Error")

        val errorMessage = networkResponse.error.message ?: "Something went wrong"
        return ResultWrapper.Error(msg = errorMessage)
    }

}