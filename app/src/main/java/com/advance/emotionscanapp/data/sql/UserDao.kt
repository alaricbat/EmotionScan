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
    fun insert(userEntity: UserEntity)

    @Update
    fun update(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

    @Query(value = "SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): Flow<UserEntity>

    @Query(value = "SELECT * FROM users")
    fun getAll(): Flow<List<UserEntity>>

}