package com.advance.emotionscanapp.domain.core.operation

class OperationSuccessState<T>: OperationState<T>() {

    override fun onSuccess() {
        try {
            listener ?: throw NullPointerException("listener is null.")
            listener?.onSuccess()
            state = OperationCompletedState()
            state.onCompleted()
        } catch (e: Exception) {
            state = OperationErrorState()
            state.onError(e)
        }
    }


}