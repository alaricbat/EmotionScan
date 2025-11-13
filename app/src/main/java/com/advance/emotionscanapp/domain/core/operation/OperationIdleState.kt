package com.advance.emotionscanapp.domain.core.operation

class OperationIdleState(
    listener: OperationListener<Any>?
): OperationState(listener) {

    override fun onStart() {
        super.onStart()
        listener?.onStart()
        state = OperationProcessingState(listener)
    }

}