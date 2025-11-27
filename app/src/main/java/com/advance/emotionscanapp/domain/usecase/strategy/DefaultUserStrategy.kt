package com.advance.emotionscanapp.domain.usecase.strategy

import com.advance.emotionscanapp.data.repository.UserRepositoryImpl
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.IUserRepository

class DefaultUserStrategy (
    repository: IUserRepository = UserRepositoryImpl(remoteDataSource = null)
): Strategy<User, IUserRepository>(repository)