package com.advance.emotionscanapp.uil.di

import android.content.Context
import com.advance.emotionscanapp.data.sql.EmotionScanDatabase
import com.advance.emotionscanapp.data.sql.UserDao

object DatabaseModule {

    private fun provideDatabase(context: Context): EmotionScanDatabase {
        return EmotionScanDatabase.getDatabase(context)
    }

    private fun provideUserDao(database: EmotionScanDatabase): UserDao {
        return database.userDao()
    }

    fun setup(context: Context) {
        DIContainer.singleton(EmotionScanDatabase::class.java, provideDatabase(context))
        DIContainer.singleton(UserDao::class.java, provideUserDao(DIContainer.get<EmotionScanDatabase>()))
    }

}