package com.advance.emotionscanapp.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.advance.emotionscanapp.domain.core.operation.OperationListener
import com.advance.emotionscanapp.domain.model.BaseModel
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.usecase.factory.UseCaseFactoryProvider
import com.advance.emotionscanapp.domain.usecase.factory.UseCaseType
import com.advance.emotionscanapp.util.log.Log
import com.advance.emotionscanapp.presentation.core.BaseViewModel
import com.advance.emotionscanapp.presentation.core.ViewEvent
import com.advance.emotionscanapp.presentation.ui.ImgProcessScreen
import kotlinx.coroutines.launch

class HomeViewModel(
    private val factoryProvider: UseCaseFactoryProvider
) : BaseViewModel<HomeIntent, HomeState, HomeEvent>() {

    companion object {
        private val TAG = HomeViewModel::javaClass.name
        private const val STR_UNKNOWN_ERR = "Unknown error occurred"
    }

    private var allUsers = emptyList<User>()

    init {
        _state.value = HomeState()
    }

    override fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.NavigateToImgProcessScreen -> {
                Log.i(TAG, "[processIntent]: HomeIntent.NavigateToImage")
                _events.postValue(HomeEvent.NavigateToImgProcessScreen)
            }
            is HomeIntent.InsertUser -> viewModelScope.launch {
                insertUser(intent.user)
            }
            is HomeIntent.SearchUsers -> viewModelScope.launch {
                searchUsers(intent.query)
            }
        }
    }

    override fun createErrorEvent(throwable: Throwable): ViewEvent {
        return HomeEvent.ShowError(throwable.message ?: STR_UNKNOWN_ERR)
    }

    private suspend fun insertUser(user: User) {
        Log.funIn(TAG, "insertUser")
        val useCase = factoryProvider.getFactory<User>(UseCaseType.UserFactory).createInsertUseCase()
        useCase(user, object : OperationListener<BaseModel> {
            override fun onStart() {
                Log.funIn(TAG, "[insertUser] - [onStart]")
                _state.postValue(_state.value?.copy(
                    isStart = true,
                    error = null
                ))
                Log.funOut(TAG, "[insertUser] - [onStart]")
            }

            override fun onLoading() {
                Log.funIn(TAG, "[insertUser] - [onLoading]")
                _state.postValue(_state.value?.copy(
                    isStart = false,
                    isLoading = true
                ))
                Log.funOut(TAG, "[insertUser] - [onLoading]")
            }

            override fun onSuccess() {
                Log.funIn(TAG, "[insertUser] - [onSuccess]")
                _state.postValue(_state.value?.copy(
                    isLoading = false
                ))
                Log.funOut(TAG, "[insertUser] - [onSuccess]")
            }

            override fun onError(throwable: Throwable) {
                Log.funIn(TAG, "[insertUser] - [onError]")
                _state.postValue(_state.value?.copy(
                    isStart = false,
                    isLoading = false,
                    isCompleted = false,
                    error = throwable.message
                ))
                _events.postValue(createErrorEvent(throwable) as HomeEvent?)
                Log.funOut(TAG, "[insertUser] - [onError]")
            }

            override fun onCompleted() {
                Log.funIn(TAG, "[insertUser] - [onCompleted]")
                _state.postValue(_state.value?.copy(
                    isStart = false,
                    isLoading = false,
                    isCompleted = true,
                ))
                Log.funOut(TAG, "[insertUser] - [onCompleted]")
            }

        })
        Log.funOut(TAG, "insertUser")
    }

    private fun searchUsers(query: String) {
        val filteredUsers = applySearchFilter(allUsers, query)
        _state.value = _state.value?.copy(
            searchQuery = query,
            filteredUsers = filteredUsers
        )
    }

    private fun applySearchFilter(users: List<User>, query: String): List<User> {
        return if (query.isBlank()) users
        else users.filter { users ->
            users.name.contains(query, ignoreCase = true) ||
                    users.email.contains(query, ignoreCase = true)
        }
    }

}