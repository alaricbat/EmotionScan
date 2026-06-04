package com.advance.emotionscanapp.presentation.ui.home

import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.presentation.core.ViewIntent

sealed class HomeIntent : ViewIntent {

    object NavigateToImgProcessScreen : HomeIntent()

    data class InsertUser(val user: User) : HomeIntent()

    data class SearchUsers(val query: String) : HomeIntent()

}