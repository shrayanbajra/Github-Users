package com.example.githubusers.ui.userlist

import com.example.githubusers.network.GithubApi
import com.example.githubusers.util.BaseRepository

class UserListRepository(private val githubApi: GithubApi) : BaseRepository() {

    suspend fun getUsers() = makeSafeApiCall { githubApi.getUsers() }

}