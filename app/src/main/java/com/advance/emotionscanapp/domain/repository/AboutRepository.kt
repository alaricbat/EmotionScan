package com.advance.emotionscanapp.domain.repository

import com.advance.emotionscanapp.domain.model.User

interface AboutRepository {
    suspend fun getAboutInfo(): Result<User>
}