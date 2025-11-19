package com.advance.emotionscanapp.domain.core.operation

class OperationErrorState: OperationState() {

    override fun onError(e: Throwable) {
        state = OperationIdleState()
    }

}