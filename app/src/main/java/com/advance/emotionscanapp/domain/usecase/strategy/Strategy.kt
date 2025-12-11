package com.advance.emotionscanapp.domain.usecase.strategy

import com.advance.emotionscanapp.domain.core.operation.OperationListener
import com.advance.emotionscanapp.domain.model.BaseModel
import com.advance.emotionscanapp.domain.repository.IRepository
import com.advance.emotionscanapp.uil.log.Log
import com.advance.emotionscanapp.uil.rx.RxTaskManager
import com.advance.emotionscanapp.uil.rx.RxTaskManager.TaskCallback

abstract class Strategy<T : BaseModel, in R : IRepository<T>> (
    private val repository: R
) {

    companion object {
        private val TAG: String by lazy { this::class.java.simpleName }
    }

    private var _operationListener: OperationListener<T>? = null

    var operationListener: OperationListener<T>? = null
        set(value) {
            field = value
            _operationListener = value
        }

    fun insert(t: T) {
        Log.funIn(TAG, "insert")
        RxTaskManager.execCompletable(
            {
                Log.i(TAG, "Strategy-execCompletable execute.")
                repository.insert(t)
            }, object : TaskCallback<Unit> {
                override fun onSubscribe() {
                    _operationListener?.onLoading()
                }

                override fun onSuccess() {
                    _operationListener?.onSuccess()
                }

                override fun onError(e: Throwable) {
                    _operationListener?.onError(e)
                }

                override fun onComplete() {
                    Log.d(TAG, "insert completed.")
                }

            }
        )
        Log.funIn(TAG, "Strategy-insert")
    }

    fun update(t: T) {
        Log.funIn(TAG, "Strategy-update")

        RxTaskManager.execCompletable(
            {
                repository.update(t)
            }, object : TaskCallback<Unit> {
                override fun onSubscribe() {
                    _operationListener?.onLoading()
                }

                override fun onSuccess() {
                    _operationListener?.onSuccess()
                }

                override fun onError(e: Throwable) {
                    _operationListener?.onError(e)
                }

                override fun onComplete() {
                    Log.d(TAG, "insert completed.")
                }

            }
        )
        Log.funIn(TAG, "Strategy-update")
    }

    fun delete(t: T) {
        Log.funIn(TAG, "Strategy-delete")
        RxTaskManager.execCompletable(
            {
                repository.delete(t)
            }, object : TaskCallback<Unit> {
                override fun onSubscribe() {
                    _operationListener?.onLoading()
                }

                override fun onSuccess() {
                    _operationListener?.onSuccess()
                }

                override fun onError(e: Throwable) {
                    _operationListener?.onError(e)
                }

                override fun onComplete() {
                    Log.d(TAG, "insert completed.")
                }

            }
        )
        Log.funIn(TAG, "Strategy-delete")
    }

    fun getById(id: Int) {
        Log.funIn(TAG, "Strategy-getById")
        RxTaskManager.execSingle(
            {
                repository.getById(id)
            }, object : TaskCallback<T> {
                override fun onSubscribe() {
                    _operationListener?.onLoading()
                }

                override fun onSuccess(t: T) {
                    _operationListener?.onSuccessWithSingleData(t)
                }

                override fun onError(e: Throwable) {
                    _operationListener?.onError(e)
                }

                override fun onComplete() {
                    Log.d(TAG, "insert completed.")
                }

            }
        )
        Log.funIn(TAG, "Strategy-getById")
    }

    fun getAll() {
        Log.funIn(TAG, "Strategy-getById")
        RxTaskManager.execSingle(
            {
                repository.getAll()
            }, object : TaskCallback<List<T>> {
                override fun onSubscribe() {
                    _operationListener?.onLoading()
                }

                override fun onSuccess(t: List<T>) {
                    _operationListener?.onSuccessWithListData(t)
                }

                override fun onError(e: Throwable) {
                    _operationListener?.onError(e)
                }

                override fun onComplete() {
                    Log.d(TAG, "insert completed.")
                }

            }
        )
        Log.funIn(TAG, "Strategy-getById")
    }

    fun release() {
        RxTaskManager.clear()
        _operationListener = null
        operationListener = null
    }

}