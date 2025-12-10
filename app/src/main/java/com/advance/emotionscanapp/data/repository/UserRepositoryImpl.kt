package com.advance.emotionscanapp.data.repository

import com.advance.emotionscanapp.data.datasource.local.IUserLocalDataSource
import com.advance.emotionscanapp.data.datasource.local.UserLocalDataSrcImpl
import com.advance.emotionscanapp.data.datasource.remote.IUserRemoteDataSource
import com.advance.emotionscanapp.data.extension.RepositoryException
import com.advance.emotionscanapp.data.extension.firstBlocking
import com.advance.emotionscanapp.data.mapper.UserMapper
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.IUserRepository

class UserRepositoryImpl(
    private val remoteDataSource: IUserRemoteDataSource?,
    private val localDataSource: IUserLocalDataSource = UserLocalDataSrcImpl(),
): IUserRepository {

    private val userMapper: UserMapper = UserMapper()

    override fun insert(t: User) {
        val userEntity = userMapper.mapToEntity(t)
        val resultDb = localDataSource.insert(userEntity)
        if (!resultDb.getResultInfo().isSuccess()) {
            throw RepositoryException(resultDb.getResultInfo().exMsg)
        }
    }

    override fun update(t: User) {
        val userEntity = userMapper.mapToEntity(t)
        val resultDb = localDataSource.update(userEntity)
        if (!resultDb.getResultInfo().isSuccess()) {
            throw RepositoryException(resultDb.getResultInfo().exMsg)
        }
    }

    override fun delete(t: User) {
        val userEntity = userMapper.mapToEntity(t)
        val resultDb = localDataSource.delete(userEntity)
        if (!resultDb.getResultInfo().isSuccess()) {
            throw RepositoryException(resultDb.getResultInfo().exMsg)
        }
    }

    override fun getById(id: Int): User {
        val resultDb = localDataSource.getUserById(id)
        if (resultDb.getResultInfo().isSuccess()) {
            val user = resultDb.getData()!!.firstBlocking().let { entity ->
                userMapper.mapToDomain(entity)
            }
            return user
        } else {
            throw RepositoryException(resultDb.getResultInfo().exMsg)
        }
    }

    override fun getAll(): List<User> {
        val resultDb = localDataSource.getAll()
        if (resultDb.getResultInfo().isSuccess()) {
            val users = resultDb.getData()!!.firstBlocking().map { entity ->
                userMapper.mapToDomain(entity)
            }
            return users
        } else {
            throw RepositoryException(resultDb.getResultInfo().exMsg)
        }
    }

}