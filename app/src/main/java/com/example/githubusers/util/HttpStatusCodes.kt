package com.example.githubusers.util

enum class HttpStatusCodes(val code: Int, val title: String) {

    BAD_REQUEST(code = 400, title = "Bad Request"),
    UNAUTHORIZED(code = 401, title = "Unauthorized"),
    FORBIDDEN(code = 403, title = "Forbidden"),
    NOT_FOUND(code = 404, title = "Not Found"),
    CONFLICT(code = 409, title = "Conflict"),
    INTERNAL_SERVER_ERROR(code = 500, title = "Internal Server Error");

    companion object {

        fun getErrorMessage(code: Int): String {

            var errorMessage = "Server Error"

            val httpResponseCodes = enumValues<HttpStatusCodes>()
            for (responseCode in httpResponseCodes) {

                if (code == responseCode.code) {
                    errorMessage = responseCode.title
                    break
                }

            }

            return errorMessage

        }

    }

}