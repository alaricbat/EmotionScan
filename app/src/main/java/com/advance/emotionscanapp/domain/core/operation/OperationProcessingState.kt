package com.advance.emotionscanapp.domain.core.operation

class OperationProcessingState (
    listener: OperationListener<Any>?
): OperationState(listener) {

    override fun onProcessing() {
        try {
            listener?.onLoading()
        } catch (e: Exception) {
            state = OperationErrorState(listener)
        }
        state = OperationSuccessState(listener)
    }

}