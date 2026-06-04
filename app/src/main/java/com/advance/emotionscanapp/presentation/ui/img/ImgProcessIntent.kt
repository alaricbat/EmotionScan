package com.advance.emotionscanapp.presentation.ui.img

import android.net.Uri
import com.advance.emotionscanapp.presentation.core.ViewIntent

sealed class ImgProcessIntent : ViewIntent {

    data class ImgProcessing(val uri: Uri?) : ImgProcessIntent()

}