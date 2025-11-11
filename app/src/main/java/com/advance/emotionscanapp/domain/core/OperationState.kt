package com.advance.emotionscanapp.domain.core

sealed class OperationState {

    object Idle: OperationState()

    object Loading: OperationState()

    object Success: OperationState()

    data class Error(val msg: String): OperationState()

}

interface OperationListener<T> {

    fun onLoading()

    fun onSuccess()

    fun onSuccessWithSingleData(t: T)

    fun onSuccessWithListData(t: List<T>)

    fun onError(throwable: Throwable)

}