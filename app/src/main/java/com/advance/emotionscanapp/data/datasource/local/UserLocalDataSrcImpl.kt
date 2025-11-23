package com.advance.emotionscanapp.data.datasource.local

import com.advance.emotionscanapp.data.local.UserEntity
import com.advance.emotionscanapp.data.sql.UserDao
import com.advance.emotionscanapp.data.result.DbResult
import com.advance.emotionscanapp.data.result.ErrorCode
import com.advance.emotionscanapp.data.result.ResultInfoError
import com.advance.emotionscanapp.data.result.ResultInfoSuccess
import com.advance.emotionscanapp.data.result.SuccessCode
import kotlinx.coroutines.flow.Flow

class UserLocalDataSrcImpl(
    private val userDao: UserDao
): IUserLocalDataSource {

    override fun insert(user: UserEntity): DbResult<Nothing?> {
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

    override fun update(user: UserEntity): DbResult<Nothing?> {
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

    override fun delete(user: UserEntity): DbResult<Nothing?> {
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

    override fun getUserById(id: Int): DbResult<Flow<UserEntity>?> {
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

    override fun getAll(): DbResult<Flow<List<UserEntity>>?> {
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