package com.advance.emotionscanapp.domain.core.operation

import com.advance.emotionscanapp.uil.log.Log

class OperationCompletedState: OperationState() {

    companion object {
        private val TAG = OperationIdleState::class.java.name
    }

    override suspend fun onCompleted() {
        Log.funIn(TAG, "onCompleted")
        try {
            state = OperationIdleState()
        } catch (e: Exception) {
            state = OperationErrorState(listener!!)
            state?.onError(e)
        }
        Log.funOut(TAG, "onCompleted")
    }

}