package com.advance.emotionscanapp.data.repository

import com.advance.emotionscanapp.data.datasource.local.IUserLocalDataSource
import com.advance.emotionscanapp.data.datasource.remote.UserRemoteDataSource
import com.advance.emotionscanapp.data.local.UserEntity
import com.advance.emotionscanapp.data.mapper.UserMapper
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: IUserLocalDataSource,
    private val userMapper: UserMapper,
): UserRepository {

    override fun insert(user: User) {

    }

    override fun update(user: User) {
        TODO("Not yet implemented")
    }

    override fun delete(user: User) {
        TODO("Not yet implemented")
    }

    override fun getUserByIdFlow(id: Int): Flow<UserEntity> {
        TODO("Not yet implemented")
    }

    override fun getUserByIdRxSingle(id: Int): Single<User> {
        TODO("Not yet implemented")
    }

    override fun getAllFlow(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override fun getAllRxSingle(): Single<List<User>> {
        TODO("Not yet implemented")
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