package com.advance.emotionscanapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "about_info")
data class AboutEntity (

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 1,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "bio")
    val bio: String,

    @ColumnInfo(name = "location")
    val location: String?,

    @ColumnInfo(name = "phone")
    val phone: String?,

    @ColumnInfo(name = "role")
    val role: String,

    @ColumnInfo(name = "experience")
    val experience: String,

    @ColumnInfo(name = "company")
    val company: String?,

    @ColumnInfo(name = "skills_json")
    val skillsJson: String,

    @ColumnInfo(name = "social_links_json")
    val socialLinksJson: String,

    @ColumnInfo(name = "last_updated")
    val lastUpdated: Long = System.currentTimeMillis()

)