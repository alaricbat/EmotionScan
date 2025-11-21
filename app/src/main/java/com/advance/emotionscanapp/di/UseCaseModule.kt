package com.advance.emotionscanapp.di

//import com.advance.emotionscanapp.domain.repository.IAboutRepository
//import com.advance.emotionscanapp.domain.repository.UserRepository
//import com.advance.emotionscanapp.domain.usecase.UseCaseFactory
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object UseCaseModule {
//
//    @Provides
//    @Singleton
//    fun provideUseCaseFactory(
//        userRepository: UserRepository,
//        IAboutRepository: IAboutRepository
//    ): UseCaseFactory {
//        return UseCaseFactory(userRepository, IAboutRepository)
//    }
//
//}