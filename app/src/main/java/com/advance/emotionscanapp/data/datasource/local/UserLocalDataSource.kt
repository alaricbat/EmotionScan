package com.advance.emotionscanapp.data.datasource.local

import com.advance.emotionscanapp.data.local.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UserLocalDataSource {
    fun getUsers(): Single<List<UserEntity>>
    fun saveUsers(users: List<UserEntity>): Completable
    fun getUserById(id: Int): Single<UserEntity>
}