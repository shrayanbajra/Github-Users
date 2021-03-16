package com.example.githubusers.ui.profile

import com.example.githubusers.base.BaseRepository
import com.example.githubusers.data.ProfileResponse
import com.example.githubusers.network.GithubApi
import com.example.githubusers.util.ResultWrapper

class ProfileRepository(private val githubApi: GithubApi) : BaseRepository() {

    suspend fun getUserProfileDetails(username: String): ResultWrapper<ProfileResponse> {

        return makeSafeApiCall { githubApi.getUserProfile(username = username) }

    }

}