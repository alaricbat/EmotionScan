package com.advance.emotionscanapp.data.repository

import com.advance.emotionscanapp.data.datasource.local.UserLocalDataSource
import com.advance.emotionscanapp.data.datasource.remote.UserRemoteDataSource
import com.advance.emotionscanapp.data.mapper.UserMapper
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource,
    private val userMapper: UserMapper
): UserRepository {

    override fun getUsers(): Single<List<User>> {
        return localDataSource.getUsers()
            .flatMap { userEntities ->
                if (userEntities.isNotEmpty()) {
                    Single.just(userEntities.map { userMapper.mapToDomain(it) })
                } else {
                    fetchAndCacheUsers()
                }
            .onErrorResumeNext { throwable ->
                fetchAndCacheUsers()
            }
        }
    }

    override fun getUserById(id: Int): Single<User> {
        return localDataSource.getUserById(id)
            .map { userMapper.mapToDomain(it) }
            .onErrorResumeNext {
                remoteDataSource.getUserById(id).map { userMapper.mapToDomain(it) }
            }
    }

    override fun refreshUsers(): Completable {
        return remoteDataSource.getUsers().flatMapCompletable { userDtos ->
            val userEntities = userDtos.map { userMapper.mapToEntity(it) }
            localDataSource.saveUsers(userEntities)
        }
    }

    private fun fetchAndCacheUsers(): Single<List<User>> {
        return remoteDataSource.getUsers().flatMap { userDtos ->
            val userEntities = userDtos.map { userMapper.mapToEntity(it) }
            localDataSource.saveUsers(userEntities).andThen(Single.just(userDtos.map { userMapper.mapToDomain(it) }))
        }
    }
}