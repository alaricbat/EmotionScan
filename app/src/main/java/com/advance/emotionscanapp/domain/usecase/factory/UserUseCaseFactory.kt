package com.advance.emotionscanapp.domain.usecase.factory

import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.usecase.DeleteUserUseCase
import com.advance.emotionscanapp.domain.usecase.GetUserByIdUseCase
import com.advance.emotionscanapp.domain.usecase.GetUsersUseCase
import com.advance.emotionscanapp.domain.usecase.InsertUserUseCase
import com.advance.emotionscanapp.domain.usecase.UpdateUserUseCase
import com.advance.emotionscanapp.domain.usecase.UseCase

class UserUseCaseFactory: IUseCaseFactory<User> {

    override fun createInsertUseCase(): UseCase<User> {
        return InsertUserUseCase()
    }

    override fun createUpdateUseCase(): UseCase<User> {
        return UpdateUserUseCase()
    }

    override fun createDeleteUseCase(): UseCase<User> {
        return DeleteUserUseCase()
    }

    override fun createGetByIdUseCase(): UseCase<Int> {
        return GetUserByIdUseCase()
    }

    override fun createGetAllUseCase(): UseCase<Unit> {
        return GetUsersUseCase()
    }

    override val useCaseName: String
        get() = FactoryName.FACTORY_NAME_USER

}