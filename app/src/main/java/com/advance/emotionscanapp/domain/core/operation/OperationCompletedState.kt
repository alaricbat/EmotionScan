package com.advance.emotionscanapp.domain.core.operation

class OperationCompletedState (
    listener: OperationListener<Any>?
): OperationState(listener) {

    override fun onCompleted() {
        listener?.onCompleted()
        state = OperationIdleState(listener)
    }

}