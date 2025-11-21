package com.advance.emotionscanapp.domain.core.operation

import com.advance.emotionscanapp.domain.model.BaseModel
import java.lang.IllegalStateException

interface OperationListener<T: BaseModel> {

    fun onStart()

    fun onLoading()

    fun onSuccess() {
        throw IllegalStateException("called wrong method.")
    }

    fun onSuccessWithSingleData(t: T) {
        throw IllegalStateException("called wrong method.")
    }

    fun onSuccessWithListData(t: List<T>) {
        throw IllegalStateException("called wrong method.")
    }

    fun onError(throwable: Throwable)

    fun onCompleted()

}