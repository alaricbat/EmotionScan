package com.advance.emotionscanapp.di

import com.advance.emotionscanapp.domain.usecase.factory.UseCaseFactoryProvider

object ServiceLocator : IServiceLocateProvider {

    private val factoryProvider : UseCaseFactoryProvider by lazy { UseCaseFactoryProvider() }

    override fun provideUserRepository() = factoryProvider

}