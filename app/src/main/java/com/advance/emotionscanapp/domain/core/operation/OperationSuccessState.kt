package com.advance.emotionscanapp.domain.core.operation

class OperationSuccessState: OperationState() {

    override suspend fun onSuccess() {
        try {
            state = OperationCompletedState()
            state!!.onCompleted()
        } catch (e: Exception) {
            state = OperationErrorState()
            state?.onError(e)
        }
    }


}