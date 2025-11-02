package com.advance.emotionscanapp.presentation.ui.home

import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.presentation.core.ViewIntent

sealed class HomeIntent : ViewIntent {
    object LoadUsers : HomeIntent()
    object RefreshUsers : HomeIntent()
    data class UserClick(val user: User) : HomeIntent()
    object LoadMoreUsers : HomeIntent()
    data class SearchUsers(val query: String) : HomeIntent()
}