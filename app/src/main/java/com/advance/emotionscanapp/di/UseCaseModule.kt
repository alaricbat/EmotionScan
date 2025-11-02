package com.advance.emotionscanapp.di

import com.advance.emotionscanapp.domain.repository.AboutRepository
import com.advance.emotionscanapp.domain.repository.UserRepository
import com.advance.emotionscanapp.domain.usecase.UseCaseFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCaseFactory(
        userRepository: UserRepository,
        aboutRepository: AboutRepository
    ): UseCaseFactory {
        return UseCaseFactory(userRepository, aboutRepository)
    }

}