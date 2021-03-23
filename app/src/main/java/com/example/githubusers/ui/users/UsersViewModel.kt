package com.example.githubusers.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusers.data.UserResponse
import com.example.githubusers.network.GithubRetrofitClient
import com.example.githubusers.util.ResultWrapper
import com.example.githubusers.util.SingleEventLiveData
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {

    private val scope = viewModelScope

    private val repository by lazy {
        val githubApi = GithubRetrofitClient.getInstance()
        UsersRepository(githubApi)
    }

    fun getUsers(): LiveData<ResultWrapper<List<UserResponse>>> {

        val users = SingleEventLiveData<ResultWrapper<List<UserResponse>>>()

        scope.launch { users.value = repository.getUsers() }

        return users

    }

}