package com.advance.emotionscanapp.presentation.ui.img

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import com.advance.emotionscanapp.presentation.core.BaseViewModel
import com.advance.emotionscanapp.presentation.core.ViewEvent
import com.advance.emotionscanapp.util.array.ArrayUtils
import com.advance.emotionscanapp.util.log.Log
import com.advance.emotionscanapp.util.svm.SVMClassifier

class ImgProcessViewModel(context: Context) : BaseViewModel<ImgProcessIntent, ImgProcessState, ImgProcessEvent>(context) {

    companion object {
        private val TAG = ImgProcessViewModel::javaClass.name
    }

    @SuppressLint("Recycle")
    override fun processIntent(intent: ImgProcessIntent) {
        Log.funIn(TAG, "[processIntent]")
        when (intent) {
            is ImgProcessIntent.ImgProcessing -> {
                Log.i(TAG, "[processIntent]: ImgProcessIntent.ImgProcessing")
                val inputStream = context.contentResolver.openInputStream(intent.uri!!)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                SVMClassifier.predict(ArrayUtils.bitmapToFloatArray(bitmap))
            }
        }
        Log.funOut(TAG, "[processIntent]")
    }

    override fun createErrorEvent(throwable: Throwable): ViewEvent {
        TODO("Not yet implemented")
    }


}