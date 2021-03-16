package com.example.githubusers.ui.userlist

import com.example.githubusers.data.UserCacheEntity
import com.example.githubusers.data.UserResponse

object UserModelMapper {

    fun mapAllResponseToCacheEntities(userResponses: List<UserResponse>): List<UserCacheEntity> {

        return userResponses.map {
            mapResponseToCacheEntity(it)
        }

    }

    private fun mapResponseToCacheEntity(response: UserResponse): UserCacheEntity {

        return UserCacheEntity(name = response.login)

    }

}