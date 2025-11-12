package com.advance.emotionscanapp.domain.usecase.strategy

import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.IUserRepository
import javax.inject.Inject

class DefaultUserStrategy @Inject constructor (
    repository: IUserRepository
): Strategy<User, IUserRepository>(repository)