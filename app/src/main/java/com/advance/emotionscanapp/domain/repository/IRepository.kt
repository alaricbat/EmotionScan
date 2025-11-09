package com.advance.emotionscanapp.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRepository<T : Any> {

    fun insert(t: T): Completable

    fun update(t: T): Completable

    fun delete(t: T): Completable

    suspend fun getById(id: Int): Single<T>

    suspend fun getAll(): Single<List<T>>

}