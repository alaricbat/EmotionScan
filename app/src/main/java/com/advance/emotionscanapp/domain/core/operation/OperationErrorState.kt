package com.advance.emotionscanapp.domain.core.operation

class OperationErrorState: OperationState() {

    override suspend fun onError(e: Throwable) {
        state = OperationIdleState()
    }

}