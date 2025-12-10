package com.advance.emotionscanapp.domain.repository

import com.advance.emotionscanapp.data.extension.RepositoryException

interface IRepository<T : Any> {

    @Throws(RepositoryException::class)
    fun insert(t: T)

    @Throws(RepositoryException::class)
    fun update(t: T)

    @Throws(RepositoryException::class)
    fun delete(t: T)

    @Throws(RepositoryException::class)
    fun getById(id: Int): T

    @Throws(RepositoryException::class)
    fun getAll(): List<T>

}