package com.advance.emotionscanapp.domain.core.operation

interface OperationListener<T> {

    fun onStart()

    fun onLoading()

    fun onSuccess()

    fun onSuccessWithSingleData(t: T)

    fun onSuccessWithListData(t: List<T>)

    fun onError(throwable: Throwable)

    fun onCompleted()

}