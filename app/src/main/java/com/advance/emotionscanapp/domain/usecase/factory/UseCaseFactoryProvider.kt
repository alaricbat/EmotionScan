package com.advance.emotionscanapp.domain.usecase.factory

import com.advance.emotionscanapp.domain.model.BaseModel

sealed class UseCaseType {

    object UserFactory: UseCaseType()

    object AboutFactory: UseCaseType()

}

@Suppress("UNCHECKED_CAST")
class UseCaseFactoryProvider {

    private val cache = mutableMapOf<UseCaseType, IUseCaseFactory<*>>()

    fun <T: BaseModel> getFactory(useCaseType: UseCaseType): IUseCaseFactory<T> {
        return cache.getOrPut(useCaseType) {
            when(useCaseType) {
                UseCaseType.UserFactory -> UserUseCaseFactory()
                UseCaseType.AboutFactory -> AboutUseCaseFactory()
            }
        } as IUseCaseFactory<T>
    }

}