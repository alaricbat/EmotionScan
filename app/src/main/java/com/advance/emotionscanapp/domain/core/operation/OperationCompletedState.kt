package com.advance.emotionscanapp.domain.core.operation

class OperationCompletedState: OperationState() {

    override suspend fun onCompleted() {
        try {
            state = OperationIdleState()
        } catch (e: Exception) {
            state = OperationErrorState()
            state.onError(e)
        }
    }

}