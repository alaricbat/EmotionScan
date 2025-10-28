package com.advance.emotionscanapp.domain.repository

import com.advance.emotionscanapp.domain.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UserRepository {
    fun getUsers(): Single<List<User>>
    fun getUserById(id: Int): Single<User>
    fun refreshUsers(): Completable
}