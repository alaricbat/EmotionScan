package com.advance.emotionscanapp.presentation.ui.img

import android.net.Uri
import com.advance.emotionscanapp.presentation.core.ViewEvent

sealed class ImgProcessEvent : ViewEvent {

    data class ImgProcessing(val uri: Uri?) : ImgProcessEvent()

}