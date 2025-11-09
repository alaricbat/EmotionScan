package com.advance.emotionscanapp.data.datasource.local

import com.advance.emotionscanapp.data.local.UserEntity
import com.advance.emotionscanapp.data.sql.UserDao
import com.advance.emotionscanapp.data.utils.DbResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDataSrcImpl @Inject constructor(
    private val userDao: UserDao
): IUserLocalDataSource {

    override suspend fun insert(user: UserEntity): DbResult<Nothing> {
        val result = runCatching {

        }.exceptionOrNull()
    }

    override suspend fun update(user: UserEntity): DbResult<Nothing> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(user: UserEntity): DbResult<Nothing> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById(id: Int): DbResult<Flow<UserEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): DbResult<Flow<List<UserEntity>>> {
        TODO("Not yet implemented")
    }

}