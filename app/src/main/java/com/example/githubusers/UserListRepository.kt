package com.example.githubusers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserListRepository(private val githubApi: GithubApi) {

    suspend fun getUsers(): Result<List<UserResponse>> {

        return withContext(Dispatchers.IO) {

            try {

                val users = githubApi.getUsers()
                Result.Success(data = users)

            } catch (cause: Throwable) {

                val exception = Exception(cause.message, cause)
                Result.Error(exception = exception)

            }

        }

    }

}