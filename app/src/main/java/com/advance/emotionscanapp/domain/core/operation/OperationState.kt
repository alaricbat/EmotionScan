package com.advance.emotionscanapp.domain.core.operation

open class OperationState<T> {

    private var _state: OperationState<T> = OperationIdleState<T>()
    var state: OperationState<T> = OperationIdleState<T>()
        set(value) {
            field = value
            _state = value
        }

    private var _listener: OperationListener<T>? = null

    var listener: OperationListener<T>? = null
        set(value) {
            field = value
            listener = value
        }

    open fun onStart() {
        throw IllegalStateException("not correct state")
    }

    open fun onSuccess() {
        throw IllegalStateException("not correct state")
    }

    open fun onProcessing() {
        throw IllegalStateException("not correct state")
    }

    open fun onError(e: Throwable) {
        throw IllegalStateException("not correct state")
    }

    open fun onCompleted() {
        throw IllegalStateException("not correct state")
    }

}