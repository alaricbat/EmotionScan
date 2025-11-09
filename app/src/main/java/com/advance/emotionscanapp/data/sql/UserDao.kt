package com.advance.emotionscanapp.data.sql

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.advance.emotionscanapp.data.local.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userEntity: UserEntity)

    @Update
    suspend fun update(userEntity: UserEntity)

    @Delete
    suspend fun delete(userEntity: UserEntity)

    @Query(value = "SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Int): Flow<UserEntity>

    @Query(value = "SELECT * FROM users")
    suspend fun getAll(): Flow<List<UserEntity>>

}