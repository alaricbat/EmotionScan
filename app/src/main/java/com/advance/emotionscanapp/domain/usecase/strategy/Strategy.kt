package com.advance.emotionscanapp.domain.usecase.strategy

import com.advance.emotionscanapp.domain.core.operation.OperationListener
import com.advance.emotionscanapp.domain.model.BaseModel
import com.advance.emotionscanapp.domain.repository.IRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

abstract class Strategy<T : BaseModel, in R: IRepository<T>> @Inject constructor(
    private val repository: R
) {

    internal val compositeDisposable = CompositeDisposable()

    private var _operationListener: OperationListener<T>? = null

    var operationListener: OperationListener<T>? = null
        set(value) {
            field = value
            _operationListener = value
        }

    fun insert(t: T) {
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

    fun update(t: T) {
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

    fun delete(t: T) {
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

    suspend fun getById(id: Int) {
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

    suspend fun getAll() {
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

    fun release() {
        compositeDisposable.clear()
        _operationListener = null
        operationListener = null
    }

}