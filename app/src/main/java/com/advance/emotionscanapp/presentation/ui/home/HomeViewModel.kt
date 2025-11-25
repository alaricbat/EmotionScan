package com.advance.emotionscanapp.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.advance.emotionscanapp.domain.core.operation.OperationListener
import com.advance.emotionscanapp.domain.model.BaseModel
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.usecase.factory.UseCaseFactoryProvider
import com.advance.emotionscanapp.domain.usecase.factory.UseCaseType
import com.advance.emotionscanapp.log.Log
import com.advance.emotionscanapp.presentation.core.BaseViewModel
import com.advance.emotionscanapp.presentation.core.ViewEvent
import kotlinx.coroutines.launch

class HomeViewModel(
    private val factoryProvider: UseCaseFactoryProvider
) : BaseViewModel<HomeIntent, HomeState, HomeEvent>() {

    private val TAG = HomeViewModel::javaClass.name

    private var allUsers = emptyList<User>()

    init {
        _state.value = HomeState()
    }

    override fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadUsers -> viewModelScope.launch {
                Log.i(TAG, "processIntent [HomeIntent.loadUsers]")
                loadUsers()
            }
            is HomeIntent.UserClick -> viewModelScope.launch {
                Log.i(TAG, "processIntent [HomeIntent.onUserClick]")
                onUserClick(intent.user)
            }
            is HomeIntent.InsertUser -> viewModelScope.launch {
                Log.i(TAG, "processIntent [HomeIntent.InsertUser]")
                insertUser(intent.user)
            }
            is HomeIntent.SearchUsers -> viewModelScope.launch {
                Log.i(TAG, "processIntent [HomeIntent.searchUsers]")
                searchUsers(intent.query)
            }
        }
    }

    override fun createErrorEvent(throwable: Throwable): ViewEvent {
        return HomeEvent.ShowError(throwable.message ?: "Unknown error occurred")
    }

    private suspend fun loadUsers() {

        val useCase = factoryProvider.getFactory<User>(UseCaseType.UserFactory).createGetAllUseCase()
        useCase(object : OperationListener<BaseModel> {
            override fun onStart() {
                _state.value = _state.value?.copy(
                    isStart = false,
                    error = null
                )
            }

            override fun onLoading() {
                _state.value = _state.value?.copy(
                    isStart = false,
                    isLoading = true
                )
            }

            override fun onSuccessWithListData(t: List<BaseModel>) {
                _state.value = _state.value?.copy(
                    isLoading = false,
                    users = t.map { baseModel -> baseModel as User }.toList()
                )
            }

            override fun onError(throwable: Throwable) {
                _state.value = _state.value?.copy(
                    isStart = false,
                    isLoading = false,
                    isCompleted = false,
                    error = throwable.message
                )
            }

            override fun onCompleted() {
                _state.value = _state.value?.copy(
                    isStart = false,
                    isLoading = false,
                    isCompleted = true,
                )
            }

        })

    }

    private suspend fun insertUser(user: User) {
        Log.funIn(TAG, "insertUser")
        val useCase = factoryProvider.getFactory<User>(UseCaseType.UserFactory).createInsertUseCase()
        useCase(user, object : OperationListener<BaseModel> {
            override fun onStart() {
                Log.funIn(TAG, "onStart")
                _state.value = _state.value?.copy(
                    isStart = false,
                    error = null
                )
                Log.funOut(TAG, "onStart")
            }

            override fun onLoading() {
                Log.funIn(TAG, "onLoading")
                _state.value = _state.value?.copy(
                    isStart = false,
                    isLoading = true
                )
                Log.funOut(TAG, "onLoading")
            }

            override fun onSuccess() {
                Log.funIn(TAG, "onSuccess")
                _state.value = _state.value?.copy(
                    isLoading = false
                )
                Log.funOut(TAG, "onSuccess")
            }

            override fun onError(throwable: Throwable) {
                Log.funIn(TAG, "onError")
                _state.value = _state.value?.copy(
                    isStart = false,
                    isLoading = false,
                    isCompleted = false,
                    error = throwable.message
                )
                Log.funOut(TAG, "onError")
            }

            override fun onCompleted() {
                Log.funIn(TAG, "onCompleted")
                _state.value = _state.value?.copy(
                    isStart = false,
                    isLoading = false,
                    isCompleted = true,
                )
                Log.funIn(TAG, "onCompleted")
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

    private fun onUserClick(user: User) {
        _events.value = HomeEvent.NavigateToUserDetail(user)
    }

}