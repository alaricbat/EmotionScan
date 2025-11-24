package com.advance.emotionscanapp.di

import com.advance.emotionscanapp.domain.usecase.factory.UseCaseFactoryProvider

interface IServiceLocateProvider {

    fun provideUserRepository(): UseCaseFactoryProvider

}