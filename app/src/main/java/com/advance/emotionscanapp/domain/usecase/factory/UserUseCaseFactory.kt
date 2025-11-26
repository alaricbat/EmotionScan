package com.advance.emotionscanapp.domain.usecase.factory

import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.usecase.DeleteUserUseCase
import com.advance.emotionscanapp.domain.usecase.GetUserByIdUseCase
import com.advance.emotionscanapp.domain.usecase.GetUsersUseCase
import com.advance.emotionscanapp.domain.usecase.InsertUserUseCase
import com.advance.emotionscanapp.domain.usecase.UpdateUserUseCase
import com.advance.emotionscanapp.domain.usecase.UseCase
import com.advance.emotionscanapp.log.Log

class UserUseCaseFactory: IUseCaseFactory<User> {

    companion object {
        private val TAG = UserUseCaseFactory::javaClass.name
    }

    override fun createInsertUseCase(): UseCase<User> {
        Log.funIn(TAG, "createInsertUseCase")
        Log.funOut(TAG, "createInsertUseCase")
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