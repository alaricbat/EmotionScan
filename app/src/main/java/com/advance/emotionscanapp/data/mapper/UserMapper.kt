package com.advance.emotionscanapp.data.mapper

import com.advance.emotionscanapp.data.local.UserEntity
import com.advance.emotionscanapp.data.remote.UserDto
import com.advance.emotionscanapp.domain.model.User
import javax.inject.Inject
import kotlin.collections.emptyList

class UserMapper @Inject constructor() {

    fun mapToDomain(dto: UserDto): User = when(dto.role) {
        "admin" -> User.AdminUser(
            id = dto.id,
            name = dto.name,
            email = dto.email,
            permission = dto.permissions ?: emptyList(),
        )
        "guest" -> User.GuestUser(
            id = dto.id,
            name = dto.name,
            email = dto.email,
        )
        else -> User.RegularUser(
            id = dto.id,
            name = dto.name,
            email = dto.email
        )
    }

    fun mapToDomain(entity: UserEntity): User = when(entity.role) {
        "admin" -> User.AdminUser(
            id = entity.id,
            name = entity.name,
            email = entity.email,
            permission = entity.permissions ?: emptyList(),
        )
        "guest" -> User.GuestUser(
            id = entity.id,
            name = entity.name,
            email = entity.email,
        )
        else -> User.RegularUser(
            id = entity.id,
            name = entity.name,
            email = entity.email
        )
    }

    fun mapToEntity(dto: UserDto): UserEntity = UserEntity(
        id = dto.id,
        name = dto.name,
        email = dto.email,
        avatar = dto.avatar ?: "",
        role = dto.role ?: "",
        department = dto.department ?: "",
        permissions = dto.permissions ?: emptyList(),
        expiryDate = dto.expiryDate,
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt,
    )

    fun mapToEntity(dto: User): UserEntity = UserEntity(
        id = dto.id,
        name = dto.name,
        email = dto.email,
        avatar = "",
        role = "",
        department = "",
        permissions = emptyList(),
        expiryDate = 0L,
        createdAt = "",
        updatedAt = "",
    )

    fun mapToDto(user: User): UserDto = when(user) {
        is User.AdminUser -> UserDto(
            id = user.id,
            name = user.name,
            email = user.email,
            avatar = "",
            role = "admin",
            department = "Admin Department",
            permissions = listOf("admin", "guest", "regular")
        )
        is User.RegularUser -> UserDto(
            id = user.id,
            name = user.name,
            email = user.email,
            avatar = "",
            role = "regular",
            department = "Normal Department",
            permissions = listOf("regular")
        )
        is User.GuestUser -> UserDto(
            id = user.id,
            name = user.name,
            email = user.email,
            avatar = "",
            role = "guest",
            department = "No Department",
            permissions = listOf("regular")
        )
    }

}