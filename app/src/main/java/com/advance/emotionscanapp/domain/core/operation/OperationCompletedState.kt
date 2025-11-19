package com.advance.emotionscanapp.domain.core.operation

class OperationCompletedState<T>: OperationState<T>() {

    override fun onCompleted() {
        try {
            listener ?: throw NullPointerException("listener is null.")
            listener!!.onCompleted()
            state = OperationIdleState()
        } catch (e: Exception) {
            state = OperationErrorState()
            state.onError(e)
        }
    }

}