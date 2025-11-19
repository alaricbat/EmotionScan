package com.advance.emotionscanapp.domain.core.operation

class OperationIdleState<T>: OperationState<T>() {

    override fun onStart() {
        try {
            super.onStart()
            listener ?: throw NullPointerException("listener is null.")
            listener!!.onStart()
            state = OperationProcessingState()
            state.onProcessing()
        } catch (e: Exception) {
            state = OperationErrorState()
            state.onError(e)
        }
    }

}