package com.example.githubusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    fun getUsers(): LiveData<Result<List<UserResponse>>> {

        val users = MutableLiveData<Result<List<UserResponse>>>()

        viewModelScope.launch {

            val retrofitClient = GithubRetrofitClient.getInstance()
            val repository = UserListRepository(retrofitClient)
            users.postValue(repository.getUsers())

        }

        return users

    }

}