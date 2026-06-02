package com.advance.emotionscanapp.presentation.ui.img

import com.advance.emotionscanapp.presentation.core.BaseViewModel
import com.advance.emotionscanapp.presentation.core.ViewEvent

class ImgViewModel : BaseViewModel<ImgIntent, ImgState, ImgEvent>() {


    override fun processIntent(intent: ImgIntent) {

    }

    override fun createErrorEvent(throwable: Throwable): ViewEvent {
        TODO("Not yet implemented")
    }


}