package com.advance.emotionscanapp.data.repository

import com.advance.emotionscanapp.data.datasource.local.IUserLocalDataSource
import com.advance.emotionscanapp.data.datasource.remote.UserRemoteDataSource
import com.advance.emotionscanapp.data.local.UserEntity
import com.advance.emotionscanapp.data.mapper.UserMapper
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.IUserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: IUserLocalDataSource,
): IUserRepository {

    private val userMapper: UserMapper = UserMapper()

    override fun insert(t: User): Completable {
        return Completable.fromAction {
            val userEntity = userMapper.mapToEntity(t)
            localDataSource.insert(userEntity)
        }
    }

    override fun update(t: User): Completable {
        return Completable.fromAction {
            val userEntity = userMapper.mapToEntity(t)
            localDataSource.update(userEntity)
        }
    }

    override fun delete(t: User): Completable {
        return Completable.fromAction {
            val userEntity = userMapper.mapToEntity(t)
            localDataSource.delete(userEntity)
        }
    }

    override suspend fun getById(id: Int): Single<User> {
        val result = localDataSource.getUserById(id)
        var user: UserEntity? = null
        result.getData()?.collect { u ->
            user = u
        }
        val userObj = user?.let { userMapper.mapToDomain(it) }
        return Single.just(userObj!!)
    }

    override suspend fun getAll(): Single<List<User>> {
        val result = localDataSource.getAll()
        var userEntityList: List<UserEntity> = emptyList()
        val userList: MutableList<User> = ArrayList()
        result.getData()?.collect { uList ->
            userEntityList = uList
        }
        userEntityList.forEach { entity ->
            userList.add(userMapper.mapToDomain(entity))
        }
        return Single.just(userList)
    }
}