package com.advance.emotionscanapp.data.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


fun <T> Flow<T>.firstBlocking(): T =
    runBlocking { this@firstBlocking.first() }