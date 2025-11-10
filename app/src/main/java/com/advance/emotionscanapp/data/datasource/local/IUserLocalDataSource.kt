package com.advance.emotionscanapp.data.datasource.local

import com.advance.emotionscanapp.data.local.UserEntity
import com.advance.emotionscanapp.data.result.DbResult
import kotlinx.coroutines.flow.Flow

interface IUserLocalDataSource {

    fun insert(user: UserEntity): DbResult<Nothing?>

    fun update(user: UserEntity): DbResult<Nothing?>

    fun delete(user: UserEntity): DbResult<Nothing?>

    fun getUserById(id: Int): DbResult<Flow<UserEntity>?>

    fun getAll(): DbResult<Flow<List<UserEntity>>?>

}