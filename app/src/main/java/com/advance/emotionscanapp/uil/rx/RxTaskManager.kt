package com.advance.emotionscanapp.uil.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

object RxTaskManager {

    private const val DEFAULT_MAX_THREADS = 5

    @Volatile
    private var maxThreads: Int = DEFAULT_MAX_THREADS

    private var threadPool = Executors.newFixedThreadPool(maxThreads)

    private var scheduler = Schedulers.from(threadPool)

    private val activeThreadCount = AtomicInteger(0)

    private val compositeDisposable = CompositeDisposable()

    fun setMaxThread(numOfThreads: Int) {
        maxThreads = numOfThreads
        threadPool.shutdownNow()
        threadPool = Executors.newFixedThreadPool(maxThreads)
        scheduler = Schedulers.from(threadPool)
    }

    interface TaskCallback<T> {
        fun onSuccess(t: T)
        fun onError(e: Throwable)
        fun onComplete()
    }

    fun execCompletable(
        task: () -> Unit,
        callback: TaskCallback<Unit>? = null
    ): Disposable {
        val disposable = Completable.fromCallable {
            activeThreadCount.incrementAndGet()
            try {
                task()
            } finally {
                activeThreadCount.decrementAndGet()
            }
        }
            .subscribeOn(scheduler)
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    callback?.onSuccess(Unit)
                    callback?.onComplete()
                },
                { e ->
                    callback?.onError(e)
                    callback?.onComplete()
                }
            )
        compositeDisposable.add(disposable)
        return disposable
    }

    fun <T : Any> execSingle(
        task: () -> T,
        callback: TaskCallback<T>? = null
    ): Disposable {
        val disposable = Single
            .fromCallable<T> {
                activeThreadCount.incrementAndGet()
                try {
                    task()
                } finally {
                    activeThreadCount.decrementAndGet()
                }
            }
            .subscribeOn(scheduler)
            .observeOn(Schedulers.io())
            .subscribe(
                { result ->
                    callback?.onSuccess(result)
                    callback?.onComplete()
                },
                { e ->
                    callback?.onError(e)
                    callback?.onComplete()
                }
            )
        compositeDisposable.add(disposable)
        return disposable
    }

    fun clear() {
        compositeDisposable.clear()
    }

    fun getActiveThreadCount(): Int = activeThreadCount.get()

}
