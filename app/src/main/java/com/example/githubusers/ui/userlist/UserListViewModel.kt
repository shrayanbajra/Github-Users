package com.example.githubusers.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusers.core.ObjectBox
import com.example.githubusers.data.UserCacheEntity
import com.example.githubusers.network.GithubRetrofitClient
import com.example.githubusers.util.ResultWrapper
import com.example.githubusers.util.SingleEventLiveData
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private val scope = viewModelScope

    private val repository by lazy {
        val githubApi = GithubRetrofitClient.getInstance()
        UserListRepository(githubApi)
    }

    fun getUsers(): LiveData<ResultWrapper<List<UserCacheEntity>>> {

        val users = SingleEventLiveData<ResultWrapper<List<UserCacheEntity>>>()

        scope.launch { users.value = repository.getUsers() }

        return users

    }

    fun deleteAllUsers() {

        val userBox: Box<UserCacheEntity> = ObjectBox.boxStore.boxFor()
        repository.deleteAllUsersInDb(userBox)

    }

}