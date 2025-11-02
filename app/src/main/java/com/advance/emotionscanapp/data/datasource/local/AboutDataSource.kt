package com.advance.emotionscanapp.data.datasource.local

import com.advance.emotionscanapp.data.remote.AboutDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AboutDataSource {
    fun getAboutInfo(): Single<AboutDto>
    fun saveAboutInfo(aboutDto: AboutDto): Completable
}