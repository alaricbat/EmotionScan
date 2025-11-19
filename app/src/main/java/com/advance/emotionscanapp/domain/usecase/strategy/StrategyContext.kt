package com.advance.emotionscanapp.domain.usecase.strategy

import com.advance.emotionscanapp.domain.core.operation.OperationListener
import com.advance.emotionscanapp.domain.model.BaseModel
import com.advance.emotionscanapp.domain.repository.IRepository

class StrategyContext {

    private val strategyMap = hashMapOf<StrategyKey, Strategy<BaseModel, IRepository<BaseModel>>>()

    companion object {

        @Volatile
        private var instance: StrategyContext? = null

        fun getInstance(): StrategyContext {
            return instance ?: synchronized(this) {
                instance ?: StrategyContext().also { instance = it }
            }
        }

    }

    fun init(
        vararg strategies: Pair<StrategyKey, Strategy<BaseModel, IRepository<BaseModel>>>
    ) {
        strategies.forEach { (key, strategy) ->
            strategyMap.put(key, strategy)
        }
    }

    fun sendRequest(key: StrategyKey, msg: StrategyMsg, model: BaseModel, listener: OperationListener<BaseModel>?) {
        listener ?: throw NullPointerException("listener is null.")
        val strategy = strategyMap.get(key) ?: throw IllegalArgumentException("key was not correct")
        strategy.operationListener = listener
        when (msg) {
            StrategyMsg.STRATEGY_MSG_INSERT -> strategy.insert(model)
            StrategyMsg.STRATEGY_MSG_UPDATE -> strategy.update(model)
            StrategyMsg.STRATEGY_MSG_DELETE -> strategy.delete(model)
            else -> throw IllegalArgumentException("msg was not correct.")
        }
    }

    suspend fun sendRequest(key: StrategyKey, msg: StrategyMsg, id: Int, listener: OperationListener<BaseModel>?) {
        listener ?: throw NullPointerException("listener is null.")
        val strategy = strategyMap.get(key) ?: throw IllegalArgumentException("key was not correct")
        strategy.operationListener = listener
        when (msg) {
            StrategyMsg.STRATEGY_MSG_GET_BY_ID -> strategy.getById(id)
            else -> throw IllegalArgumentException("msg was not correct.")
        }
    }

    suspend fun sendRequest(key: StrategyKey, msg: StrategyMsg, listener: OperationListener<BaseModel>?) {
        listener ?: throw NullPointerException("listener is null.")
        val strategy = strategyMap.get(key) ?: throw IllegalArgumentException("key was not correct")
        strategy.operationListener = listener
        when (msg) {
            StrategyMsg.STRATEGY_MSG_GET_ALL -> strategy.getAll()
            else -> throw IllegalArgumentException("msg was not correct.")
        }
    }

    enum class StrategyKey {
        STRATEGY_USER,
        STRATEGY_ABOUT
    }

    enum class StrategyMsg {
        STRATEGY_MSG_INSERT,
        STRATEGY_MSG_UPDATE,
        STRATEGY_MSG_DELETE,
        STRATEGY_MSG_GET_BY_ID,
        STRATEGY_MSG_GET_ALL
    }

}