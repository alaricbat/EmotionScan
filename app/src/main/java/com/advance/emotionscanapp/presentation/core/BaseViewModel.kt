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

    abstract suspend fun processIntent(intent: Intent)

    protected abstract fun createErrorEvent(throwable: Throwable): ViewEvent

}