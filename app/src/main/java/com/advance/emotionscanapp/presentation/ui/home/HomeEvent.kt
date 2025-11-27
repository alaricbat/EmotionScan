package com.advance.emotionscanapp.presentation.ui.home

import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.presentation.core.ViewEvent

sealed class HomeEvent : ViewEvent {

    data class NavigateToUserDetail(val user: User) : HomeEvent()

    data class ShowError(val message: String, val id: Long = System.currentTimeMillis()) : HomeEvent()

}