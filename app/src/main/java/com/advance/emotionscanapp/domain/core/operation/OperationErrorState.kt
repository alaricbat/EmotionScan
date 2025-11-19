package com.advance.emotionscanapp.domain.core.operation

class OperationErrorState<T>: OperationState<T>() {

    override fun onError(e: Throwable) {
        listener?.onError(e)
        state = OperationIdleState()
    }

}