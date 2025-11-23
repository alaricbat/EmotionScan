package com.advance.emotionscanapp.domain.usecase.strategy

import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.IUserRepository

class DefaultUserStrategy (
    repository: IUserRepository
): Strategy<User, IUserRepository>(repository)