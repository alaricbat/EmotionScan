package com.advance.emotionscanapp.domain.usecase.strategy

import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.IUserRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DefaultUserStrategy @Inject constructor (
    private val repository: IUserRepository
): Strategy<User>() {

    private val compositeDisposable = CompositeDisposable()

    override fun insert(t: User) {
        compositeDisposable.add(
            repository.insert(t)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe({
                    //TODO implement onComplete
                }, {
                    //TODO implement error
                })
        )
    }

    override fun update(t: User) {
        compositeDisposable.add(
            repository.update(t)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe({
                    //TODO implement onComplete
                }, {
                    //TODO implement error
                })
        )
    }

    override fun delete(t: User) {
        compositeDisposable.add(
            repository.delete(t)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe({
                    //TODO implement onComplete
                }, {
                    //TODO implement error
                })
        )
    }

    override suspend fun getById(id: Int) {
        compositeDisposable.add(
            repository.getById(id).subscribe()
        )
    }

    override suspend fun getAll() {
        compositeDisposable.add(
            repository.getAll().subscribe()
        )
    }

    override fun release() {
        compositeDisposable.clear()
    }

}