package com.advance.emotionscanapp.domain.usecase

import com.advance.emotionscanapp.domain.repository.IAboutRepository
import com.advance.emotionscanapp.domain.repository.UserRepository
import com.advance.emotionscanapp.domain.usecase.strategy.DefaultUserStrategy

//import javax.inject.Inject

//class UseCaseFactory @Inject constructor(
class UseCaseFactory constructor(
    private val _userRepository: UserRepository,
    private val _I_aboutRepository: IAboutRepository
) {

    val userRepository: UserRepository
        get() = _userRepository

    val IAboutRepository: IAboutRepository
        get() = _I_aboutRepository

    fun createGetUsersUseCase(
        strategy: UserStrategy = DefaultUserStrategy(_userRepository)): GetUsersUseCase {
        return GetUsersUseCase(strategy)
    }

    fun createGetAboutInfoUseCase() = GetAboutInfoUseCase(_I_aboutRepository)

}