package com.advance.emotionscanapp.domain.core.operation

open class OperationState {

    private var _state: OperationState = OperationIdleState()
    var state: OperationState = OperationIdleState()
        set(value) {
            field = value
            _state = value
        }

    open suspend fun onStart(operator: suspend() -> Unit) {
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