package com.advance.emotionscanapp.domain.repository

import com.advance.emotionscanapp.data.local.UserEntity
import com.advance.emotionscanapp.domain.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun insert(user: User)

    fun update(user: User)

    fun delete(user: User)

    fun getUserByIdFlow(id: Int): Flow<UserEntity>

    fun getUserByIdRxSingle(id: Int): Single<User>

    fun getAllFlow(): Flow<List<User>>

    fun getAllRxSingle(): Single<List<User>>

    fun refreshUsers(): Completable

}