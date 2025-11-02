package com.advance.emotionscanapp.domain.usecase

import com.advance.emotionscanapp.domain.repository.AboutRepository
import com.advance.emotionscanapp.domain.repository.UserRepository
import com.advance.emotionscanapp.domain.usecase.strategy.DefaultUserStrategy
import com.advance.emotionscanapp.domain.usecase.strategy.UserStrategy
import javax.inject.Inject

class UseCaseFactory @Inject constructor(
    private val _userRepository: UserRepository,
    private val _aboutRepository: AboutRepository
) {

    val userRepository: UserRepository
        get() = _userRepository

    val aboutRepository: AboutRepository
        get() = _aboutRepository

    fun createGetUsersUseCase(
        strategy: UserStrategy = DefaultUserStrategy(_userRepository)): GetUsersUseCase {
        return GetUsersUseCase(strategy)
    }

    fun createGetAboutInfoUseCase() = GetAboutInfoUseCase(_aboutRepository)

}