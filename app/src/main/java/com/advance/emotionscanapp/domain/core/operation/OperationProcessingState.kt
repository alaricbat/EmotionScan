package com.advance.emotionscanapp.domain.core.operation

class OperationProcessingState<T>: OperationState<T>() {

    override fun onProcessing() {
        try {
            listener ?: throw NullPointerException("listener is null.")
            listener!!.onLoading()
            state = OperationSuccessState()
            state.onSuccess()
        } catch (e: Exception) {
            state = OperationErrorState()
            state.onError(e)
        }
    }

}