package com.advance.emotionscanapp.domain.usecase

import com.advance.emotionscanapp.domain.core.CompletableUseCase
import com.advance.emotionscanapp.domain.core.UseCase
import com.advance.emotionscanapp.domain.model.AboutMe
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.IAboutRepository
import com.advance.emotionscanapp.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class GetUsersUseCase(
    private val strategy: UserStrategy
): UseCase<Unit, List<User>>() {
    override fun execute(params: Unit): Single<List<User>> = strategy.getUsers()
}

class GetUserByIdUseCase(
    private val repository: UserRepository
): UseCase<Int, User>() {
    override fun execute(params: Int): Single<User> = repository.getUserById(params)
}

class GetAboutInfoUseCase(
    private val repository: IAboutRepository
): UseCase<Unit, AboutMe>() {
    override fun execute(params: Unit): Single<AboutMe> = repository.getAboutInfo()
}

class UpdateAboutInfoUseCase(
    private val repository: IAboutRepository
): CompletableUseCase<AboutMe>() {
    override fun execute(params: AboutMe): Completable = repository.updateAboutInfo(params)
}