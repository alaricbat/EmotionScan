package com.advance.emotionscanapp.data.sql

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.advance.emotionscanapp.data.local.AboutEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AboutDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(aboutEntity: AboutEntity)

    @Update
    fun update(aboutEntity: AboutEntity)

    @Delete
    fun delete(aboutEntity: AboutEntity)

    @Query(value = "SELECT * FROM about_info WHERE id = :id")
    fun getItemById(id: Int): Flow<AboutEntity>

    @Query(value = "SELECT * FROM about_info")
    fun getAll(): Flow<List<AboutEntity>>

}