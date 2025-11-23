package com.advance.emotionscanapp.data.repository

import com.advance.emotionscanapp.data.datasource.local.IUserLocalDataSource
import com.advance.emotionscanapp.data.datasource.remote.UserRemoteDataSource
import com.advance.emotionscanapp.data.mapper.UserMapper
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.IUserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.first

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: IUserLocalDataSource,
): IUserRepository {

    private val userMapper: UserMapper = UserMapper()

    override fun insert(t: User): Completable {
        return Completable.create { emitter ->
            try {
                val userEntity = userMapper.mapToEntity(t)
                val resultDb = localDataSource.insert(userEntity)
                if (!resultDb.getResultInfo().isSuccess()) {
                    throw Exception(resultDb.getResultInfo().exMsg)
                }
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    override fun update(t: User): Completable {
        return Completable.create { emitter ->
            try {
                val userEntity = userMapper.mapToEntity(t)
                val resultDb = localDataSource.update(userEntity)
                if (!resultDb.getResultInfo().isSuccess()) {
                    throw Exception(resultDb.getResultInfo().exMsg)
                }
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    override fun delete(t: User): Completable {
        return Completable.create { emitter ->
            try {
                val userEntity = userMapper.mapToEntity(t)
                val resultDb = localDataSource.delete(userEntity)
                if (!resultDb.getResultInfo().isSuccess()) {
                    throw Exception(resultDb.getResultInfo().exMsg)
                }
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    override suspend fun getById(id: Int): Single<User> {
        val resultDb = localDataSource.getUserById(id)
        if (resultDb.getResultInfo().isSuccess()) {
            val user = resultDb.getData()!!.first().let {entity ->
                userMapper.mapToDomain(entity)
            }
            return Single.just(user)
        } else {
            return Single.error { Throwable(resultDb.getResultInfo().exMsg) }
        }
    }

    override suspend fun getAll(): Single<List<User>> {
        val resultDb = localDataSource.getAll()
        if (resultDb.getResultInfo().isSuccess()) {
            val users = resultDb.getData()!!.first().map { entity ->
                userMapper.mapToDomain(entity)
            }
            return Single.just(users)
        } else {
            return Single.error { Throwable(resultDb.getResultInfo().exMsg) }
        }
    }
}