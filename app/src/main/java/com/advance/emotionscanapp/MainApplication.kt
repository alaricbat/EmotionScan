package com.advance.emotionscanapp

import android.app.Application
import com.advance.emotionscanapp.domain.usecase.strategy.DefaultUserStrategy
import com.advance.emotionscanapp.domain.usecase.strategy.StrategyContext

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val userStrategy = DefaultUserStrategy()

        StrategyContext.getInstance().init()
    }
}