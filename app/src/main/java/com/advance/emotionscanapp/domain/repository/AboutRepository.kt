package com.advance.emotionscanapp.domain.repository

import com.advance.emotionscanapp.domain.model.AboutMe
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AboutRepository {
    fun getAboutInfo(): Single<AboutMe>
    fun updateAboutInfo(aboutMe: AboutMe): Completable
}