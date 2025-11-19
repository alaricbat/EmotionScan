package com.advance.emotionscanapp.domain.usecase

import com.advance.emotionscanapp.domain.core.operation.OperationListener
import com.advance.emotionscanapp.domain.model.BaseModel
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.usecase.strategy.StrategyContext

class InsertAboutUseCase(): UseCase<User>() {

    override suspend fun execute(
        params: User,
        listener: OperationListener<BaseModel>
    ) {
        StrategyContext.getInstance().sendRequest(
            StrategyContext.StrategyKey.STRATEGY_ABOUT,
            StrategyContext.StrategyMsg.STRATEGY_MSG_INSERT,
            params,
            listener
        )
    }

}

class UpdateAboutUseCase(): UseCase<User>() {

    override suspend fun execute(
        params: User,
        listener: OperationListener<BaseModel>
    ) {
        StrategyContext.getInstance().sendRequest(
            StrategyContext.StrategyKey.STRATEGY_ABOUT,
            StrategyContext.StrategyMsg.STRATEGY_MSG_UPDATE,
            params,
            listener
        )
    }

}

class DeleteAboutUseCase(): UseCase<User>() {

    override suspend fun execute(
        params: User,
        listener: OperationListener<BaseModel>
    ) {
        StrategyContext.getInstance().sendRequest(
            StrategyContext.StrategyKey.STRATEGY_ABOUT,
            StrategyContext.StrategyMsg.STRATEGY_MSG_DELETE,
            params,
            listener
        )
    }

}

class GetAboutByIdUseCase(): UseCase<Int>() {

    override suspend fun execute(
        params: Int,
        listener: OperationListener<BaseModel>
    ) {
        StrategyContext.getInstance().sendRequest(
            StrategyContext.StrategyKey.STRATEGY_ABOUT,
            StrategyContext.StrategyMsg.STRATEGY_MSG_GET_BY_ID,
            params,
            listener
        )
    }

}

class GetAboutsUseCase(): UseCase<Unit>() {

    override suspend fun execute(listener: OperationListener<BaseModel>) {
        StrategyContext.getInstance().sendRequest(
            StrategyContext.StrategyKey.STRATEGY_ABOUT,
            StrategyContext.StrategyMsg.STRATEGY_MSG_GET_ALL,
            listener
        )
    }

}