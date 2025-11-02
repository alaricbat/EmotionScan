package com.advance.emotionscanapp.data.repository

import com.advance.emotionscanapp.data.datasource.local.AboutDataSource
import com.advance.emotionscanapp.data.mapper.AboutMapper
import com.advance.emotionscanapp.domain.model.AboutMe
import com.advance.emotionscanapp.domain.repository.AboutRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AboutRepositoryImpl @Inject constructor(
    private val dataSource: AboutDataSource,
    private val aboutMapper: AboutMapper
) : AboutRepository {

    override fun getAboutInfo(): Single<AboutMe> {
        return dataSource.getAboutInfo().map {
            aboutMapper.mapToDomain(it)
        }
    }

    override fun updateAboutInfo(aboutMe: AboutMe): Completable {
        return dataSource.saveAboutInfo(aboutMapper.mapToDto(aboutMe))
    }

}