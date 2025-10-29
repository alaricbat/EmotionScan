package com.advance.emotionscanapp.domain.usecase.strategy

import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single

class DefaultUserStrategy(
    private val repository: UserRepository
): UserStrategy {

    override fun getUsers(): Single<List<User>> {
        return repository.getUsers()
    }

    override fun shouldRefresh(): Boolean = true
}