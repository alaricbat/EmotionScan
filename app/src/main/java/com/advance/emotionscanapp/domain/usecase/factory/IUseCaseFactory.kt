package com.advance.emotionscanapp.domain.usecase.factory

import com.advance.emotionscanapp.domain.model.BaseModel
import com.advance.emotionscanapp.domain.usecase.UseCase

interface IUseCaseFactory<T: BaseModel> {

    fun createInsertUseCase(): UseCase<T>

    fun createUpdateUseCase(): UseCase<T>

    fun createDeleteUseCase(): UseCase<T>

    fun createGetByIdUseCase(): UseCase<Int>

    fun createGetAllUseCase(): UseCase<Unit>

    val useCaseName: String

}