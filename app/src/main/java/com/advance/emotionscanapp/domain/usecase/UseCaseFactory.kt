package com.advance.emotionscanapp.domain.usecase

import com.advance.emotionscanapp.domain.repository.AboutRepository
import com.advance.emotionscanapp.domain.repository.UserRepository
import com.advance.emotionscanapp.domain.usecase.strategy.DefaultUserStrategy
import com.advance.emotionscanapp.domain.usecase.strategy.UserStrategy
import javax.inject.Inject

class UseCaseFactory @Inject constructor(
    private val userRepository: UserRepository,
    private val aboutRepository: AboutRepository
) {

    fun createGetUsersUseCase(
        strategy: UserStrategy = DefaultUserStrategy(userRepository)): GetUsersUseCase {
        return GetUsersUseCase(strategy)
    }

    fun createGetAboutInfoUseCase() = GetAboutInfoUseCase(aboutRepository)

}