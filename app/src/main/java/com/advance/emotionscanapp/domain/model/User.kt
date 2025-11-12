package com.advance.emotionscanapp.domain.model

import java.security.Permission

sealed class User : BaseModel(){
    abstract val id: Int
    abstract val name: String
    abstract val email: String

    data class RegularUser(
        override val id: Int,
        override val name: String,
        override val email: String
    ): User()

    data class AdminUser(
        override val id: Int,
        override val name: String,
        override val email: String,
        val permission: List<String>
    ): User()

    data class GuestUser(
        override val id: Int,
        override val name: String,
        override val email: String
    ): User()
}
