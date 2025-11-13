package com.advance.emotionscanapp.domain.core.operation

open class OperationState(
    val listener: OperationListener<Any>?
) {

    private var _state: OperationState = OperationIdleState(null)
    var state: OperationState = OperationIdleState(null)
        set(value) {
            field = value
            _state = value
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