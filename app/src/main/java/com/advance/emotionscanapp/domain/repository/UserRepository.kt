package com.advance.emotionscanapp.domain.repository

import com.advance.emotionscanapp.domain.model.User

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUserProfile(): Result<User>
}