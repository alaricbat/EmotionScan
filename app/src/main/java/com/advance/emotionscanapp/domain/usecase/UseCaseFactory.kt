package com.advance.emotionscanapp.domain.usecase

import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.usecase.strategy.DefaultUserStrategy
import com.advance.emotionscanapp.domain.usecase.strategy.Strategy
import javax.inject.Inject

class UseCaseFactory @Inject constructor(
    private val _userStrategy: Strategy<User>
) {

    val userStrategy: Strategy<User>
        get() = _userStrategy

    fun createGetUsersUseCase(): GetUsersUseCase {
        return GetUsersUseCase(_userStrategy)
    }

    fun createGetAboutInfoUseCase() = null

}