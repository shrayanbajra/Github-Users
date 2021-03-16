package com.example.githubusers.data

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class UserCacheEntity(

    @Id
    var id: Long = 0,
    var name: String? = null,
    var avatarUrl: String? = null

)
