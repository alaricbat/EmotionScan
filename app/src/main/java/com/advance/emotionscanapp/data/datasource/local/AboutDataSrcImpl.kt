package com.advance.emotionscanapp.data.datasource.local

import com.advance.emotionscanapp.data.remote.AboutDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class AboutDataSrcImpl: AboutDataSource {
    override fun getAboutInfo(): Single<AboutDto> {
        TODO("Not yet implemented")
    }

    override fun saveAboutInfo(aboutDto: AboutDto): Completable {
        TODO("Not yet implemented")
    }
}