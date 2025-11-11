package com.advance.emotionscanapp.domain.usecase.strategy

import com.advance.emotionscanapp.domain.core.OperationListener
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.IUserRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DefaultUserStrategy @Inject constructor (
    private val repository: IUserRepository
): Strategy<User>() {

    private val compositeDisposable = CompositeDisposable()

    private var _operationListener: OperationListener<User>? = null

    var operationListener: OperationListener<User>? = null
        set(value) {
            field = value
            _operationListener = value
        }

    override fun insert(t: User) {
        compositeDisposable.add(
            repository.insert(t)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe {
                    _operationListener?.onLoading()
                }
                .subscribe({
                    _operationListener?.onSuccess()
                }, { throwable ->
                    _operationListener?.onError(throwable)
                })
        )
    }

    override fun update(t: User) {
        compositeDisposable.add(
            repository.update(t)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe {
                    _operationListener?.onLoading()
                }
                .subscribe({
                    _operationListener?.onSuccess()
                }, { throwable ->
                    _operationListener?.onError(throwable)
                })
        )
    }

    override fun delete(t: User) {
        compositeDisposable.add(
            repository.delete(t)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe {
                    _operationListener?.onLoading()
                }
                .subscribe({
                    _operationListener?.onSuccess()
                }, { throwable ->
                    _operationListener?.onError(throwable)
                })
        )
    }

    override suspend fun getById(id: Int) {
        compositeDisposable.add(
            repository.getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe {
                    _operationListener?.onLoading()
                }
                .subscribe({ user ->
                    _operationListener?.onSuccessWithSingleData(user)
                }, { throwable ->
                    _operationListener?.onError(throwable)
                })
        )
    }

    override suspend fun getAll() {
        compositeDisposable.add(
            repository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe {
                    _operationListener?.onLoading()
                }
                .subscribe({ users ->
                    _operationListener?.onSuccessWithListData(users)
                }, { throwable ->
                    _operationListener?.onError(throwable)
                })
        )
    }

    override fun release() {
        compositeDisposable.clear()
        _operationListener = null
        operationListener = null
    }

}