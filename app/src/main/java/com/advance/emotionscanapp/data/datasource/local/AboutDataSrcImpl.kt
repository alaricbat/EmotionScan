package com.advance.emotionscanapp.data.datasource.local

import com.advance.emotionscanapp.data.remote.AboutDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AboutDataSrcImpl @Inject constructor(): AboutDataSource {
    override fun getAboutInfo(): Single<AboutDto> {
        return Single<AboutDto>
    }

    override fun saveAboutInfo(aboutDto: AboutDto): Completable {
        TODO("Not yet implemented")
    }
}