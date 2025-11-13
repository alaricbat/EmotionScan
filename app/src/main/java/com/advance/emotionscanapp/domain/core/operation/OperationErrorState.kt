package com.advance.emotionscanapp.domain.core.operation

class OperationErrorState (
    listener: OperationListener<Any>?
): OperationState(listener) {

    override fun onError(e: Throwable) {
        listener?.onError(e)
        state = OperationIdleState(listener)
    }

}