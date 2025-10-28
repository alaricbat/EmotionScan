package com.advance.emotionscanapp.domain.usecase

import com.advance.emotionscanapp.domain.model.User
import io.reactivex.rxjava3.core.Single

interface UserStrategy {
    fun getUsers(): Single<List<User>>
    fun shouldRefresh(): Boolean
}