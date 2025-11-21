package com.advance.emotionscanapp.domain.core

sealed class DomainResult<out T> {

    object Success: DomainResult<Nothing>()

    data class SuccessWithData<out T>(@JvmField val data: T) : DomainResult<T>()

    data class Error(val exception: Throwable) : DomainResult<Nothing>()

    object Loading : DomainResult<Nothing>()

    fun getData(): T? = if (this is SuccessWithData) data else null

    fun isSuccess(): Boolean = this is SuccessWithData || this is Success

    fun isLoading(): Boolean = this is Loading

    fun isError(): Boolean = this is Error

}