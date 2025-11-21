package com.advance.emotionscanapp.domain.usecase

import com.advance.emotionscanapp.domain.core.operation.OperationIdleState
import com.advance.emotionscanapp.domain.core.operation.OperationListener
import com.advance.emotionscanapp.domain.model.BaseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


abstract class UseCase<in P> {

    private var operation = OperationIdleState()

    protected open suspend fun execute(listener: OperationListener<BaseModel>) {
        throw IllegalStateException("called with uncorrected method.")
    }

    protected open suspend fun execute(params: P, listener: OperationListener<BaseModel>) {
        throw IllegalStateException("called with uncorrected method.")
    }

    open val dispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend operator fun invoke(params: P, listener: OperationListener<BaseModel>) {
        withContext(dispatcher) {
                operation.onStart({ execute(params, listener) })
        }
    }

    suspend operator fun invoke(listener: OperationListener<BaseModel>) {
        withContext(dispatcher) {
                operation.onStart({ execute(listener) })
        }
    }

}