package com.advance.emotionscanapp.presentation.ui.home

import com.advance.emotionscanapp.presentation.core.ViewEvent

sealed class HomeEvent : ViewEvent {

    object NavigateToImgProcessScreen : HomeEvent()

    data class ShowError(val message: String, val id: Long = System.currentTimeMillis()) : HomeEvent()

    data class ShowSuccess(val message: String) : HomeEvent()

}