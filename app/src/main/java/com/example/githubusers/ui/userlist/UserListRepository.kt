package com.example.githubusers.ui.userlist

import com.example.githubusers.ObjectBox
import com.example.githubusers.data.UserCacheEntity
import com.example.githubusers.data.UserCacheEntity_
import com.example.githubusers.data.UserResponse
import com.example.githubusers.network.GithubApi
import com.example.githubusers.util.BaseRepository
import com.example.githubusers.util.ResultWrapper
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import timber.log.Timber

class UserListRepository(private val githubApi: GithubApi) : BaseRepository() {

    suspend fun getUsers(): ResultWrapper<List<UserCacheEntity>> {

        val networkResponse = makeSafeApiCall { githubApi.getUsers() }

        return when (networkResponse) {

            is ResultWrapper.Error -> proceedToShowingCachedData(networkResponse)
            is ResultWrapper.Success -> proceedToShowingUpdatedDataFromDb(networkResponse)

        }

    }

    private fun proceedToShowingCachedData(networkResponse: ResultWrapper.Error): ResultWrapper<MutableList<UserCacheEntity>> {

        Timber.d("Proceeding to Show Cached Data")

        val userBox: Box<UserCacheEntity> = ObjectBox.boxStore.boxFor()
        val usersInDb = userBox.query()
            .order(UserCacheEntity_.name)
            .build()
            .find()

        return if (usersInDb.isNullOrEmpty()) {
            Timber.d("No data found in database, return network response as it is")
            networkResponse
        } else {
            Timber.d("Returned cached data from database")
            ResultWrapper.Success(data = usersInDb)
        }

    }

    private fun proceedToShowingUpdatedDataFromDb(networkResponse: ResultWrapper.Success<List<UserResponse>>): ResultWrapper.Success<MutableList<UserCacheEntity>> {

        Timber.d("Proceeding to show updated data from database")

        val userBox: Box<UserCacheEntity> = ObjectBox.boxStore.boxFor()
        val cacheEntity = UserModelMapper.mapAllResponseToCacheEntities(networkResponse.data)
        userBox.put(cacheEntity)

        Timber.d("Returned updated cached data")
        val updatedUsersInDb = userBox.all
        return ResultWrapper.Success(data = updatedUsersInDb)

    }

}