package com.advance.emotionscanapp.data.sql

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.advance.emotionscanapp.data.local.AboutEntity
import com.advance.emotionscanapp.data.local.UserEntity

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [UserEntity::class, AboutEntity::class],
    version = 1,
    exportSchema = false)
abstract class EmotionScanDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun aboutDao(): AboutDao

    companion object {

        private const val DATABASE_NAME = "emotion_scan_db";

        @Volatile
        private var Instant: EmotionScanDatabase? = null

        fun getDatabase(context: Context): EmotionScanDatabase {
            return Instant ?: synchronized(this) {
                Room.databaseBuilder(context, EmotionScanDatabase::class.java, DATABASE_NAME)
                    .build()
                    .also {
                        Instant = it
                    }
            }
        }

    }

}