package com.advance.emotionscanapp.presentation.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Base MVI Architecture.
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseViewModel<Intent: ViewIntent, State: ViewState, Event: ViewEvent> : ViewModel() {

    protected val _state: MutableLiveData<State> = MutableLiveData()
    val state: LiveData<State> get() = _state

    protected val _events: SingleLiveEvent<Event> = SingleLiveEvent()
    val events: LiveData<Event> get() = _events

    protected val compositeDisposable = CompositeDisposable()

    abstract fun processIntent(intent: Intent)

    protected fun <T : Any> Single<T>.execute(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit = {
            _events.value = createErrorEvent(it) as Event
        }
    ) {
        compositeDisposable.add(
            this.subscribe(onSuccess, onError)
        )
    }

    protected fun Completable.execute(
        onComplete: () -> Unit = {},
        onError: (Throwable) -> Unit = {
            _events.value = createErrorEvent(it) as Event
        }
    ) {
        compositeDisposable.add(
            this.subscribe(onComplete, onError)
        )
    }

    protected abstract fun createErrorEvent(throwable: Throwable): ViewEvent

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}