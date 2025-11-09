package com.advance.emotionscanapp.di

import com.advance.emotionscanapp.data.datasource.local.AboutDataSource
import com.advance.emotionscanapp.data.datasource.local.IUserLocalDataSource
import com.advance.emotionscanapp.data.datasource.remote.UserRemoteDataSource
import com.advance.emotionscanapp.data.mapper.AboutMapper
import com.advance.emotionscanapp.data.mapper.UserMapper
import com.advance.emotionscanapp.data.repository.AboutRepositoryImpl
import com.advance.emotionscanapp.data.repository.UserRepositoryImpl
import com.advance.emotionscanapp.domain.repository.AboutRepository
import com.advance.emotionscanapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        remoteDataSource: UserRemoteDataSource,
        localDataSource: IUserLocalDataSource,
        userMapper: UserMapper
    ): UserRepository {
        return UserRepositoryImpl(remoteDataSource, localDataSource, userMapper)
    }

    @Provides
    @Singleton
    fun provideAboutRepository(
        dataSource: AboutDataSource,
        aboutMapper: AboutMapper
    ): AboutRepository {
        return AboutRepositoryImpl(dataSource, aboutMapper)
    }

}