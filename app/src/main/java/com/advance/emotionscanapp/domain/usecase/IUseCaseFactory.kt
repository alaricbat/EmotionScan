package com.advance.emotionscanapp.domain.usecase

interface IUseCaseFactory {

    fun createInsertUserUseCase(): InsertUserUseCase

    fun createUpdateUserUseCase(): UpdateUserUseCase

    fun createDeleteUserUseCase(): DeleteUserUseCase

    fun createGetUserByIdUseCase(): GetUserByIdUseCase

    fun createGetUsersUseCase(): GetUsersUseCase

}