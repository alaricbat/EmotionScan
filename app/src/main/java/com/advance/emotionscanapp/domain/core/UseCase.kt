package com.advance.emotionscanapp.domain.core

import com.advance.emotionscanapp.domain.core.operation.OperationIdleState
import com.advance.emotionscanapp.domain.core.operation.OperationState
import com.advance.emotionscanapp.domain.usecase.strategy.StrategyContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext


abstract class UseCase<in P, R: Any> {

    private val _state = MutableStateFlow<OperationState>(OperationIdleState(null))
    val state: StateFlow<OperationState> = _state.asStateFlow()

    lateinit var strategyContext: StrategyContext

    protected abstract suspend fun execute(params: P): R

    open val dispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend operator fun invoke(params: P) {
//        _state.value = ResultState.Loading
        try {
            val result = withContext(dispatcher) {
                execute(params)
            }
//            _state.value = ResultState.Success(result)
        } catch (e: Exception) {
//            _state.value = ResultState.Error(e)
        }
    }

    fun reset() {
//        _state.value = ResultState.Idle
    }

}

abstract class CompletableUseCase<in P> {

    protected abstract fun execute(params: P): Completable

    operator fun invoke(params: P): Completable = execute(params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}