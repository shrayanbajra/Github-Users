package com.example.githubusers.ui.userlist

import com.example.githubusers.data.Result
import com.example.githubusers.data.UserResponse
import com.example.githubusers.network.GithubApi
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