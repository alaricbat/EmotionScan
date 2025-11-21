package com.advance.emotionscanapp.presentation.ui.home

import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.presentation.core.ViewState

data class HomeState (
    val users: List<User> = emptyList(),
    val filteredUsers: List<User> = emptyList(),
    val isStart: Boolean = false,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val hasMore: Boolean = true,
    val isCompleted: Boolean = false
) : ViewState