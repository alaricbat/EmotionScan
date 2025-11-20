package com.advance.emotionscanapp.domain.usecase.factory

import com.advance.emotionscanapp.domain.model.AboutMe
import com.advance.emotionscanapp.domain.usecase.DeleteAboutUseCase
import com.advance.emotionscanapp.domain.usecase.GetAboutByIdUseCase
import com.advance.emotionscanapp.domain.usecase.GetAboutsUseCase
import com.advance.emotionscanapp.domain.usecase.InsertAboutUseCase
import com.advance.emotionscanapp.domain.usecase.UpdateAboutUseCase
import com.advance.emotionscanapp.domain.usecase.UseCase

class AboutUseCaseFactory: IUseCaseFactory<AboutMe> {

    override fun createInsertUseCase(): UseCase<AboutMe> {
        return InsertAboutUseCase()
    }

    override fun createUpdateUseCase(): UseCase<AboutMe> {
        return UpdateAboutUseCase()
    }

    override fun createDeleteUseCase(): UseCase<AboutMe> {
        return DeleteAboutUseCase()
    }

    override fun createGetByIdUseCase(): UseCase<Int> {
        return GetAboutByIdUseCase()
    }

    override fun createGetAllUseCase(): UseCase<Unit> {
        return GetAboutsUseCase()
    }

    override val useCaseName: String
        get() = FactoryName.FACTORY_NAME_ABOUT

}