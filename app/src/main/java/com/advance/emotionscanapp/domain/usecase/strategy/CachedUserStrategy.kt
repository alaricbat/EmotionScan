package com.advance.emotionscanapp.domain.usecase.strategy

import android.os.SystemClock
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.IRepository
//import com.advance.emotionscanapp.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CachedUserStrategy @Inject constructor(
    private val repository: IRepository<User>
): Strategy<User>() {

    private companion object {
        const val CACHE_DURATION = 5 * 60 * 1000
    }

    private var lastRefreshTime = 0L
    override fun insert(t: User) {
        TODO("Not yet implemented")
    }

    override fun update(t: User) {
        TODO("Not yet implemented")
    }

    override fun delete(t: User) {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll() {
        TODO("Not yet implemented")
    }

    override fun release() {
        TODO("Not yet implemented")
    }

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