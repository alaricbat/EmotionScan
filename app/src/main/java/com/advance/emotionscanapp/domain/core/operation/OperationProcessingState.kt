package com.advance.emotionscanapp.domain.core.operation

import com.advance.emotionscanapp.log.Log

class OperationProcessingState: OperationState() {

    companion object {
        private val TAG = OperationProcessingState::class.java.name
    }

    override suspend fun onProcessing() {
        Log.funIn(TAG, "onProcessing")
        try {
            state = OperationSuccessState()
            state!!.onSuccess()
        } catch (e: Exception) {
            state = OperationErrorState(listener!!)
            state?.onError(e)
        }
        Log.funOut(TAG, "onProcessing")
    }

}