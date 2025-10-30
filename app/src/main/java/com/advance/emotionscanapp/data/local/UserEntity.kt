package com.advance.emotionscanapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "role")
    val role: String,

    @ColumnInfo(name = "department")
    val department: String,

    @ColumnInfo(name = "permissions")
    val permissions: List<String>?,

    @ColumnInfo(name = "expiry_date")
    val expiryDate: Long?,

    @ColumnInfo(name = "created_at")
    val createdAt: String?,

    @ColumnInfo(name = "updated_at")
    val updatedAt: String?,

    @ColumnInfo(name = "last_sync_time")
    val lastSyncTime: Long = System.currentTimeMillis()

)
