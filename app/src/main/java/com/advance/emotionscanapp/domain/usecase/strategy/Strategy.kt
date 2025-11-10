package com.advance.emotionscanapp.domain.usecase.strategy

abstract class Strategy<T> {

    abstract fun insert(t: T)

    abstract fun update(t: T)

    abstract fun delete(t: T)

    abstract suspend fun getById(id: Int)

    abstract suspend fun getAll()

    abstract fun release()

}