package com.advance.emotionscanapp.uil.di

import com.advance.emotionscanapp.domain.usecase.factory.UseCaseFactoryProvider

interface IServiceLocateProvider {

    fun provideUserRepository(): UseCaseFactoryProvider

}