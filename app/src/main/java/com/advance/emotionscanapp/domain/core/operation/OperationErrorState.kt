package com.advance.emotionscanapp.domain.core.operation

import com.advance.emotionscanapp.log.Log

class OperationErrorState(listener: OperationListener<*>): OperationState() {

    companion object {
        private val TAG = OperationErrorState::class.java.name
    }

    init {
        this.listener = listener
    }

    override suspend fun onError(e: Throwable) {
        Log.funIn(TAG, "onError")
        listener!!.onError(e)
        state = OperationIdleState()
        Log.funOut(TAG, "onError")
    }

}