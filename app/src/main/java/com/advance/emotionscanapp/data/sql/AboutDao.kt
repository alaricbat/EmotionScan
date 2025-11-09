package com.advance.emotionscanapp.data.sql

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.advance.emotionscanapp.data.local.AboutEntity
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

@Dao
interface AboutDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(aboutEntity: AboutEntity)

    @Update
    suspend fun update(aboutEntity: AboutEntity)

    @Delete
    suspend fun delete(aboutEntity: AboutEntity)

    @Query(value = "SELECT * FROM about_info WHERE id = :id")
    suspend fun getItemByIdFlow(id: Int): Flow<AboutEntity>

    @Query(value = "SELECT * FROM about_info WHERE id = :id")
    suspend fun getItemByIdRxSingle(id: Int): Single<AboutEntity>

    @Query(value = "SELECT * FROM about_info")
    suspend fun getAllFlow(): Flow<List<AboutEntity>>

    @Query(value = "SELECT * FROM about_info")
    suspend fun getAllRxSingle(): Single<List<AboutEntity>>

}