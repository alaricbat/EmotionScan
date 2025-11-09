package com.advance.emotionscanapp.data.datasource.local

import com.advance.emotionscanapp.data.local.UserEntity
import com.advance.emotionscanapp.data.utils.DbResult
import kotlinx.coroutines.flow.Flow

interface IUserLocalDataSource {

    suspend fun insert(user: UserEntity): DbResult<Nothing>

    suspend fun update(user: UserEntity): DbResult<Nothing>

    suspend fun delete(user: UserEntity): DbResult<Nothing>

    suspend fun getUserById(id: Int): DbResult<Flow<UserEntity>>

    suspend fun getAll(): DbResult<Flow<List<UserEntity>>>

}