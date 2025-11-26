package com.advance.emotionscanapp.domain.core.operation

open class OperationState {

    private lateinit var _listener: OperationListener<*>
    var listener: OperationListener<*>? = null
        set(value) {
            field = value
            _listener = value!!
        }

    private lateinit var _state: OperationState
    var state: OperationState? = null
        set(value) {
            field = value
            _state = value!!
        }

    open suspend fun onStart(operator: suspend() -> Unit) {
        throw IllegalStateException("not correct state")
    }

    open suspend fun onSuccess() {
        throw IllegalStateException("not correct state")
    }

    open suspend fun onProcessing() {
        throw IllegalStateException("not correct state")
    }

    open suspend fun onError(e: Throwable) {
        throw IllegalStateException("not correct state")
    }

    open suspend fun onCompleted() {
        throw IllegalStateException("not correct state")
    }

}