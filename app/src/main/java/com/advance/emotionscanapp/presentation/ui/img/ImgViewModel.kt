package com.advance.emotionscanapp.presentation.ui.img

import com.advance.emotionscanapp.presentation.core.BaseViewModel
import com.advance.emotionscanapp.presentation.core.ViewEvent

class ImgProcessViewModel : BaseViewModel<ImgProcessIntent, ImgProcessState, ImgProcessEvent>() {

    companion object {
        private val TAG = ImgProcessViewModel::javaClass.name
    }

    override fun processIntent(intent: ImgProcessIntent) {

    }

    override fun createErrorEvent(throwable: Throwable): ViewEvent {
        TODO("Not yet implemented")
    }


}