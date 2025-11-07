package com.advance.emotionscanapp.data.datasource.local

import com.advance.emotionscanapp.data.local.UserEntity
import com.advance.emotionscanapp.data.remote.UserDto
import com.advance.emotionscanapp.domain.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserLocalDataSrcImpl @Inject constructor() : UserLocalDataSource {
    override fun getUsers(): Single<List<UserEntity>> {
        val userSingle = UserEntity(
            id = 1,
            name = "name",
            email = "email",
            avatar = "avatar",
            role = "role",
            department = "department",
            permissions = emptyList(),
            expiryDate = null,
            createdAt = null,
            updatedAt = null,
            lastSyncTime = 0L
        )
        val userEntityListSingle: MutableList<UserEntity> = mutableListOf()
        userEntityListSingle.add(userSingle)
        return Single<List<UserEntity>>.just(userEntityListSingle)
    }

    override fun saveUsers(users: List<UserEntity>): Completable {
        TODO("Not yet implemented")
    }

    override fun getUserById(id: Int): Single<UserEntity> {
        TODO("Not yet implemented")
    }
}