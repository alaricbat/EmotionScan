package com.advance.emotionscanapp.domain.usecase

import com.advance.emotionscanapp.domain.core.operation.OperationListener
import com.advance.emotionscanapp.domain.model.BaseModel
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.usecase.strategy.StrategyContext
import com.advance.emotionscanapp.log.Log

class InsertUserUseCase(): UseCase<User>() {

    companion object {
        private val TAG = InsertUserUseCase::javaClass.name
    }

    override suspend fun execute(
        params: User,
        listener: OperationListener<BaseModel>
    ) {
        Log.funIn(TAG, "execute")
        StrategyContext.getInstance().sendRequest(
            StrategyContext.StrategyKey.STRATEGY_USER,
            StrategyContext.StrategyMsg.STRATEGY_MSG_INSERT,
            params,
            listener
        )
        Log.funOut(TAG, "execute")
    }

}

class UpdateUserUseCase(): UseCase<User>() {

    override suspend fun execute(
        params: User,
        listener: OperationListener<BaseModel>
    ) {
        StrategyContext.getInstance().sendRequest(
            StrategyContext.StrategyKey.STRATEGY_USER,
            StrategyContext.StrategyMsg.STRATEGY_MSG_UPDATE,
            params,
            listener
        )
    }

}

class DeleteUserUseCase(): UseCase<User>() {

    override suspend fun execute(
        params: User,
        listener: OperationListener<BaseModel>
    ) {
        StrategyContext.getInstance().sendRequest(
            StrategyContext.StrategyKey.STRATEGY_USER,
            StrategyContext.StrategyMsg.STRATEGY_MSG_DELETE,
            params,
            listener
        )
    }

}

class GetUserByIdUseCase(): UseCase<Int>() {

    override suspend fun execute(
        params: Int,
        listener: OperationListener<BaseModel>
    ) {
        StrategyContext.getInstance().sendRequest(
            StrategyContext.StrategyKey.STRATEGY_USER,
            StrategyContext.StrategyMsg.STRATEGY_MSG_GET_BY_ID,
            params,
            listener
        )
    }

}

class GetUsersUseCase(): UseCase<Unit>() {

    override suspend fun execute(listener: OperationListener<BaseModel>) {
        StrategyContext.getInstance().sendRequest(
            StrategyContext.StrategyKey.STRATEGY_USER,
            StrategyContext.StrategyMsg.STRATEGY_MSG_GET_ALL,
            listener
        )
    }

}