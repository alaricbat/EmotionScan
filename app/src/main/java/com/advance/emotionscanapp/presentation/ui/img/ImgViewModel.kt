package com.advance.emotionscanapp.presentation.ui.img

import android.annotation.SuppressLint
import com.advance.emotionscanapp.presentation.core.BaseViewModel
import com.advance.emotionscanapp.presentation.core.ViewEvent
import com.advance.emotionscanapp.util.log.Log

class ImgProcessViewModel : BaseViewModel<ImgProcessIntent, ImgProcessState, ImgProcessEvent>() {

    companion object {
        private val TAG = ImgProcessViewModel::javaClass.name
    }

    @SuppressLint("Recycle")
    override fun processIntent(intent: ImgProcessIntent) {
        Log.funIn(TAG, "[processIntent]")
        when (intent) {
            is ImgProcessIntent.ImgProcessing -> {
                Log.i(TAG, "[processIntent]: ImgProcessIntent.ImgProcessing")
                _events.postValue(ImgProcessEvent.ImgProcessing(intent.uri))
            }
        }
        Log.funOut(TAG, "[processIntent]")
    }

    override fun createErrorEvent(throwable: Throwable): ViewEvent {
        TODO("Not yet implemented")
    }


}