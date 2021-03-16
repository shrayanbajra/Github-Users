package com.example.githubusers.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusers.data.ProfileResponse
import com.example.githubusers.network.GithubRetrofitClient
import com.example.githubusers.util.ResultWrapper
import com.example.githubusers.util.SingleEventLiveData
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val scope = viewModelScope

    private val repository by lazy {
        val githubClient = GithubRetrofitClient.getInstance()
        ProfileRepository(githubClient)
    }

    fun getUserProfileDetails(username: String): LiveData<ResultWrapper<ProfileResponse>> {

        val userProfileDetails = SingleEventLiveData<ResultWrapper<ProfileResponse>>()

        scope.launch {
            val networkResponse = repository.getUserProfileDetails(username = username)
            userProfileDetails.value = networkResponse
        }

        return userProfileDetails

    }

}