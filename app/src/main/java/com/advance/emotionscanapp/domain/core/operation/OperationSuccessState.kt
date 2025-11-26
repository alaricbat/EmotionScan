package com.advance.emotionscanapp.domain.core.operation

import com.advance.emotionscanapp.log.Log

class OperationSuccessState: OperationState() {

    companion object {
        private val TAG = OperationSuccessState::class.java.name
    }

    override suspend fun onSuccess() {
        Log.funIn(TAG, "onSuccess")
        try {
            state = OperationCompletedState()
            state!!.onCompleted()
        } catch (e: Exception) {
            state = OperationErrorState(listener!!)
            state?.onError(e)
        }
        Log.funOut(TAG, "onSuccess")
    }


}