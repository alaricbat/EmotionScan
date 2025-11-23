package com.advance.emotionscanapp.data.repository

import com.advance.emotionscanapp.data.datasource.local.AboutDataSource
import com.advance.emotionscanapp.data.mapper.AboutMapper
import com.advance.emotionscanapp.domain.model.AboutMe
import com.advance.emotionscanapp.domain.repository.IAboutRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class AboutRepositoryImpl(
    private val dataSource: AboutDataSource,
    private val aboutMapper: AboutMapper
) : IAboutRepository {

    //    override fun getAboutInfo(): Single<AboutMe> {
//        return dataSource.getAboutInfo().map {
//            aboutMapper.mapToDomain(it)
//        }
//    }
//
//    override fun updateAboutInfo(aboutMe: AboutMe): Completable {
//        return dataSource.saveAboutInfo(aboutMapper.mapToDto(aboutMe))
//    }
    override fun insert(t: AboutMe): Completable {
        TODO("Not yet implemented")
    }

    override fun update(t: AboutMe): Completable {
        TODO("Not yet implemented")
    }

    override fun delete(t: AboutMe): Completable {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Int): Single<AboutMe> {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): Single<List<AboutMe>> {
        TODO("Not yet implemented")
    }

}