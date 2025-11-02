package com.advance.emotionscanapp.data.datasource.remote

import com.advance.emotionscanapp.data.remote.UserDto
import io.reactivex.rxjava3.core.Single

interface UserRemoteDataSource {
    fun getUsers(): Single<List<UserDto>>
    fun getUserById(id: Int): Single<UserDto>
}