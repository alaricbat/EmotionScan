package com.advance.emotionscanapp

import android.app.Application
import com.advance.emotionscanapp.di.DatabaseModule
import com.advance.emotionscanapp.domain.model.BaseModel
import com.advance.emotionscanapp.domain.repository.IRepository
import com.advance.emotionscanapp.domain.usecase.strategy.DefaultUserStrategy
import com.advance.emotionscanapp.domain.usecase.strategy.Strategy
import com.advance.emotionscanapp.domain.usecase.strategy.StrategyContext
import com.advance.emotionscanapp.log.Log

class MainApplication: Application() {

    companion object {
        private val TAG = MainApplication::javaClass.name
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreate() {
        Log.funIn(TAG, "onCreate")
        super.onCreate()
        DatabaseModule.setup(this)
        StrategyContext.getInstance().init(
            Pair(StrategyContext.StrategyKey.STRATEGY_USER, DefaultUserStrategy() as Strategy<BaseModel, IRepository<BaseModel>>)
        )
        Log.funOut(TAG, "onCreate")
    }

}