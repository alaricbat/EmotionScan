package com.advance.emotionscanapp.presentation.ui.home

import androidx.room.Query
import androidx.room.util.query
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.domain.repository.UserRepository
import com.advance.emotionscanapp.domain.usecase.GetUserByIdUseCase
import com.advance.emotionscanapp.domain.usecase.GetUsersUseCase
import com.advance.emotionscanapp.domain.usecase.UseCaseFactory
import com.advance.emotionscanapp.domain.usecase.strategy.CachedUserStrategy
import com.advance.emotionscanapp.presentation.core.BaseViewModel
import com.advance.emotionscanapp.presentation.core.ViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val useCaseFactory: UseCaseFactory
) : BaseViewModel<HomeIntent, HomeState, HomeEvent>() {

    private var currentPage = 0
    private var pageSize = 20

    private var allUsers = emptyList<User>()

    init {
        _state.value = HomeState()
    }

    override fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadUsers -> loadUsers()
            is HomeIntent.RefreshUsers -> refreshUsers()
            is HomeIntent.UserClick -> onUserClick(intent.user)
            is HomeIntent.LoadMoreUsers -> loadMoreUsers()
            is HomeIntent.SearchUsers -> searchUsers(intent.query)
        }
    }

    override fun createErrorEvent(throwable: Throwable): ViewEvent {
        return HomeEvent.ShowError(throwable.message ?: "Unknown error occurred")
    }

    private fun loadUsers() {
        _state.value = _state.value?.copy(isLoading = true, error = null)

        getUsersUseCase(Unit).execute(
            onSuccess = { users ->
                _state.value = _state.value?.copy(
                    isLoading = false,
                    users = users,
                    filteredUsers = users,
                    hasMore = users.size >= pageSize
                )
            }, onError = { error ->
                _state.value = _state.value?.copy(
                    isLoading = false,
                    error = error.message
                )
            }
        )

    }

    private fun refreshUsers() {
        _state.value = _state.value?.copy(isRefreshing = true, error = null)

        val refreshUseCase = useCaseFactory.createGetUsersUseCase(
            strategy = CachedUserStrategy(useCaseFactory.userRepository)
        )

        refreshUseCase(Unit).execute(
            onSuccess = { users ->
                _state.value = _state.value?.copy(
                    isRefreshing = false,
                    users = users,
                    filteredUsers = applySearchFilter(users, _state.value?. searchQuery ?: ""),
                    hasMore = users.size >= pageSize
                )
            }, onError = { error ->
                _state.value = _state.value?.copy(
                    isRefreshing = false,
                    error = error.message
                )
            }
        )

    }

    private fun loadMoreUsers() {

        if (_state.value?.isLoadingMore == true || _state.value?.hasMore == false) return

        _state.value = _state.value?.copy(isLoadingMore = true)
        currentPage++

        getUsersUseCase(Unit).execute(
            onSuccess = { newUsers ->
                val updatedUsers = allUsers + newUsers
                _state.value = _state.value?.copy(
                    isLoadingMore = false,
                    users = updatedUsers,
                    filteredUsers = applySearchFilter(newUsers, _state.value?.searchQuery?:""),
                    hasMore = newUsers.isNotEmpty()
                )
            }, onError = { error ->
                currentPage--
                _state.value = _state.value?.copy(
                    isLoadingMore = false,
                    error = "Failed to load more: ${error.message}"
                )
            }
        )
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