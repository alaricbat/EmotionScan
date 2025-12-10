package com.advance.emotionscanapp.data.repository

import com.advance.emotionscanapp.data.datasource.local.AboutDataSource
import com.advance.emotionscanapp.data.mapper.AboutMapper
import com.advance.emotionscanapp.domain.model.AboutMe
import com.advance.emotionscanapp.domain.repository.IAboutRepository

class AboutRepositoryImpl(
    private val dataSource: AboutDataSource,
    private val aboutMapper: AboutMapper
) : IAboutRepository {

    override fun insert(t: AboutMe) {
        TODO("Not yet implemented")
    }

    override fun update(t: AboutMe) {
        TODO("Not yet implemented")
    }

    override fun delete(t: AboutMe) {
        TODO("Not yet implemented")
    }

    override fun getById(id: Int): AboutMe {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<AboutMe> {
        TODO("Not yet implemented")
    }

}