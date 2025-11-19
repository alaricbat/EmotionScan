package com.advance.emotionscanapp.domain.usecase

class UseCaseFactory: IUseCaseFactory {

    override fun createInsertUserUseCase(): InsertUserUseCase {
        return InsertUserUseCase()
    }

    override fun createUpdateUserUseCase(): UpdateUserUseCase {
        return UpdateUserUseCase()
    }

    override fun createDeleteUserUseCase(): DeleteUserUseCase {
        return DeleteUserUseCase()
    }

    override fun createGetUserByIdUseCase(): GetUserByIdUseCase {
        return GetUserByIdUseCase()
    }

    override fun createGetUsersUseCase(): GetUsersUseCase {
        return GetUsersUseCase()
    }


}