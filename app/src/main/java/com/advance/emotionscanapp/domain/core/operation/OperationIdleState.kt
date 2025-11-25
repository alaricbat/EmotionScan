package com.advance.emotionscanapp.domain.core.operation

class OperationIdleState: OperationState() {

    override suspend fun onStart(operator: suspend() -> Unit) {
        try {
            operator()
            state = OperationProcessingState()
            state!!.onProcessing()
        } catch (e: Exception) {
            state = OperationErrorState()
            state?.onError(e)
        }
    }

}