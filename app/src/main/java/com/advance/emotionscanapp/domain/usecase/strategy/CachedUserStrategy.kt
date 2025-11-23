package com.advance.emotionscanapp.domain.usecase.strategy

import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.IUserRepository

class CachedUserStrategy(
    repository: IUserRepository
): Strategy<User, IUserRepository>(repository) {

    private companion object {
        const val CACHE_DURATION = 5 * 60 * 1000
    }

    private var lastRefreshTime = 0L

//    override fun getUsers(): Single<List<User>> {
//        return if (shouldRefresh()) {
//            repository.refreshUsers().
//            andThen(repository.getUsers()).
//            doOnSuccess { lastRefreshTime = SystemClock.elapsedRealtime() }
//        } else {
//            repository.getUsers()
//        }
//    }

//    override fun shouldRefresh(): Boolean {
//        return SystemClock.elapsedRealtime() - lastRefreshTime > CACHE_DURATION
//    }

}