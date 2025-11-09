package com.advance.emotionscanapp.data.datasource.local

import com.advance.emotionscanapp.data.local.UserEntity
import com.advance.emotionscanapp.data.sql.UserDao
import com.advance.emotionscanapp.data.utils.DbResult
import com.advance.emotionscanapp.data.utils.ErrorCode
import com.advance.emotionscanapp.data.utils.ResultInfoError
import com.advance.emotionscanapp.data.utils.ResultInfoSuccess
import com.advance.emotionscanapp.data.utils.SuccessCode
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDataSrcImpl @Inject constructor(
    private val userDao: UserDao
): IUserLocalDataSource {

    override suspend fun insert(user: UserEntity): DbResult<Nothing?> {
        return runCatching {
            userDao.insert(user)
            DbResult(
                null,
                ResultInfoSuccess(
                    SuccessCode.RESULT_ID_DB_C001
                )
            )
        }.getOrElse { exception ->
            val result = ResultInfoError(
                ErrorCode.RESULT_ID_DB_ER001
            )
            exception.message?.let {
                result.exMsg = it
            }
            DbResult(
                null,
                result
            )
        }
    }

    override suspend fun update(user: UserEntity): DbResult<Nothing?> {
        return runCatching {
            userDao.update(user)
            DbResult(
                null,
                ResultInfoSuccess(
                    SuccessCode.RESULT_ID_DB_C002
                )
            )
        }.getOrElse { exception ->
            val result = ResultInfoError(
                ErrorCode.RESULT_ID_DB_ER002
            )
            exception.message?.let {
                result.exMsg = it
            }
            DbResult(
                null,
                result
            )
        }
    }

    override suspend fun delete(user: UserEntity): DbResult<Nothing?> {
        return runCatching {
            userDao.delete(user)
            DbResult(
                null,
                ResultInfoSuccess(
                    SuccessCode.RESULT_ID_DB_C003
                )
            )
        }.getOrElse { exception ->
            val result = ResultInfoError(
                ErrorCode.RESULT_ID_DB_ER003
            )
            exception.message?.let {
                result.exMsg = it
            }
            DbResult(
                null,
                result
            )
        }
    }

    override suspend fun getUserById(id: Int): DbResult<Flow<UserEntity>?> {
        return runCatching {
            val user = userDao.getUserById(id)
            DbResult<Flow<UserEntity>?>(
                user,
                ResultInfoSuccess(
                    SuccessCode.RESULT_ID_DB_C004
                )
            )
        }.getOrElse { exception ->
            val result = ResultInfoError(
                ErrorCode.RESULT_ID_DB_ER004
            )
            exception.message?.let {
                result.exMsg = it
            }
            DbResult<Flow<UserEntity>?>(
                null,
                result
            )
        }
    }

    override suspend fun getAll(): DbResult<Flow<List<UserEntity>>?> {
        return runCatching {
            val users = userDao.getAll()
            DbResult<Flow<List<UserEntity>>?>(
                users,
                ResultInfoSuccess(
                    SuccessCode.RESULT_ID_DB_C005
                )
            )
        }.getOrElse { exception ->
            val result = ResultInfoError(
                ErrorCode.RESULT_ID_DB_ER005
            )
            exception.message?.let {
                result.exMsg = it
            }
            DbResult<Flow<List<UserEntity>>?>(
                null,
                result
            )
        }
    }

}