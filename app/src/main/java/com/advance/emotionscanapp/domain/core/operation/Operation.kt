package com.advance.emotionscanapp.domain.core.operation

sealed class OperateResult<out T> {

    object Idle: OperateResult<Nothing>()

    object Loading: OperateResult<Nothing>()

    data class Success<T>(val data: T): OperateResult<T>()

    data class Error(val error: Throwable): OperateResult<Nothing>()

}

