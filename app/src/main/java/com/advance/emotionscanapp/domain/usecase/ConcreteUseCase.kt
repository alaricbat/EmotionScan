package com.advance.emotionscanapp.domain.usecase

import com.advance.emotionscanapp.domain.core.ResultCode
import com.advance.emotionscanapp.domain.core.UseCase
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.usecase.strategy.StrategyContext

class GetUserByIdUseCase(): UseCase<Int, ResultCode>() {
    override suspend fun execute(params: Int): ResultCode {
        strategyContext.sendRequest(
            StrategyContext.StrategyKey.STRATEGY_USER,
            StrategyContext.StrategyMsg.STRATEGY_MSG_GET_BY_ID,
            null
        )
    }
}

class GetUsersUseCase(): UseCase<Unit, List<User>>() {
    override suspend fun execute(params: Unit): List<User> {
    }

}