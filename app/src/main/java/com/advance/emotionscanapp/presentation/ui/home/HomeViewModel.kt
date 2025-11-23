package com.advance.emotionscanapp.presentation.ui.home

import com.advance.emotionscanapp.domain.core.operation.OperationListener
import com.advance.emotionscanapp.domain.model.BaseModel
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.usecase.factory.UseCaseFactoryProvider
import com.advance.emotionscanapp.domain.usecase.factory.UseCaseType
import com.advance.emotionscanapp.presentation.core.BaseViewModel
import com.advance.emotionscanapp.presentation.core.ViewEvent

class HomeViewModel(
    private val factoryProvider: UseCaseFactoryProvider
) : BaseViewModel<HomeIntent, HomeState, HomeEvent>() {

    private var allUsers = emptyList<User>()

    init {
        _state.value = HomeState()
    }

    override suspend fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadUsers -> loadUsers()
            is HomeIntent.UserClick -> onUserClick(intent.user)
            is HomeIntent.InsertUser -> insertUser(intent.user)
            is HomeIntent.SearchUsers -> searchUsers(intent.query)
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
        val useCase = factoryProvider.getFactory<User>(UseCaseType.UserFactory).createInsertUseCase()
        useCase(user, object : OperationListener<BaseModel> {
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

            override fun onSuccess() {
                _state.value = _state.value?.copy(
                    isLoading = false
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