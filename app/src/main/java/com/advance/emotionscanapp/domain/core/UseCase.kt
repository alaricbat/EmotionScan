package com.advance.emotionscanapp.domain.core

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class UseCase<in P, R : Any> {

    private val _state = MutableLiveData<>

    protected abstract fun execute(params: P): Single<R>

    operator fun invoke(params: P): Single<R> = execute(params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}

abstract class CompletableUseCase<in P> {

    protected abstract fun execute(params: P): Completable

    operator fun invoke(params: P): Completable = execute(params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}