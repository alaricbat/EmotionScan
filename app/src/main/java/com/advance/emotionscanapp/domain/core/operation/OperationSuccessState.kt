package com.advance.emotionscanapp.domain.core.operation

class OperationSuccessState(
    listener: OperationListener<Any>?
): OperationState(listener) {

    override fun onSuccess() {
        listener?.onSuccess()
        state = OperationCompletedState(listener)
    }


}