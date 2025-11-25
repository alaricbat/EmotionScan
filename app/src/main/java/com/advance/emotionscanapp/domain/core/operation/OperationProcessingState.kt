package com.advance.emotionscanapp.domain.core.operation

class OperationProcessingState: OperationState() {

    override suspend fun onProcessing() {
        try {
            state = OperationSuccessState()
            state!!.onSuccess()
        } catch (e: Exception) {
            state = OperationErrorState()
            state?.onError(e)
        }
    }

}