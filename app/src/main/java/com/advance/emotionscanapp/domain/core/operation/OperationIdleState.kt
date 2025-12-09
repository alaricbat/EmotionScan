package com.advance.emotionscanapp.domain.core.operation

import com.advance.emotionscanapp.uil.log.Log

class OperationIdleState: OperationState() {

    companion object {
        private val TAG = OperationIdleState::class.java.name
    }

    override suspend fun onStart(operator: suspend() -> Unit) {
        Log.funIn(TAG, "onStart")
        try {
            operator()
            state = OperationProcessingState()
            state!!.onProcessing()
        } catch (e: Exception) {
            state = OperationErrorState(listener!!)
            state?.onError(e)
        }
        Log.funOut(TAG, "onStart")
    }

}