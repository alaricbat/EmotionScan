package com.advance.emotionscanapp.domain.core.operation

sealed class OperationResult<out T> {

    object Idle: OperationResult<Nothing>()

    object Loading: OperationResult<Nothing>()

    data class Success<T>(val data: T): OperationResult<T>()

    data class Error(val error: Throwable): OperationResult<Nothing>()

}

