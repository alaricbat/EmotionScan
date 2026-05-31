package com.advance.emotionscanapp.util.di

import com.advance.emotionscanapp.domain.usecase.factory.UseCaseFactoryProvider

interface IServiceLocateProvider {

    fun provideUserRepository(): UseCaseFactoryProvider

}