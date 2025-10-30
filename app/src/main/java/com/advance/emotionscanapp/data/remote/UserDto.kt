package com.advance.emotionscanapp.data.remote

import com.google.gson.annotations.SerializedName


data class UserDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: Int,

    @SerializedName("email")
    val email: Int,

    @SerializedName("avatar")
    val avatar: String? = null,

    @SerializedName("role")
    val role: String? = "user",

    @SerializedName("department")
    val department: String? = null,

    @SerializedName("permissions")
    val permissions: List<String>? = emptyList(),

    @SerializedName("expiry_date")
    val expiryDate: Long? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null

)
