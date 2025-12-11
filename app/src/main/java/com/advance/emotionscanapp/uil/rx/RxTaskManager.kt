package com.advance.emotionscanapp.uil.rx

import com.advance.emotionscanapp.uil.log.Log
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

object RxTaskManager {

    private val TAG = RxTaskManager::javaClass.name

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
        fun onSubscribe()
        fun onSuccess(t: T) {}
        fun onSuccess() {}
        fun onError(e: Throwable)
        fun onComplete()
    }

    fun execCompletable(
        task: () -> Unit,
        callback: TaskCallback<Unit>? = null
    ): Disposable {
        Log.funIn(TAG, "execCompletable")
        val disposable = Completable.fromCallable {
            activeThreadCount.incrementAndGet()
            try {
                task()
            } finally {
                callback?.onComplete()
                activeThreadCount.decrementAndGet()
            }
        }
            .subscribeOn(scheduler)
            .observeOn(Schedulers.io())
            .doOnSubscribe {
                callback?.onSubscribe()
            }
            .subscribe({ callback?.onSuccess(Unit) },
                { e -> callback?.onError(e) }
            )
        compositeDisposable.add(disposable)
        Log.funOut(TAG, "execCompletable")
        return disposable
    }

    fun <T : Any> execSingle(
        task: () -> T,
        callback: TaskCallback<T>? = null
    ): Disposable {
        Log.funIn(TAG, "execSingle")
        val disposable = Single
            .fromCallable<T> {
                activeThreadCount.incrementAndGet()
                try {
                    task()
                } finally {
                    callback?.onComplete()
                    activeThreadCount.decrementAndGet()
                }
            }
            .subscribeOn(scheduler)
            .observeOn(Schedulers.io())
            .subscribe({ result -> callback?.onSuccess(result) },
                { e -> callback?.onError(e) }
            )
        compositeDisposable.add(disposable)
        Log.funOut(TAG, "execSingle")
        return disposable
    }

    fun clear() {
        compositeDisposable.clear()
    }

    fun getActiveThreadCount(): Int = activeThreadCount.get()

}
