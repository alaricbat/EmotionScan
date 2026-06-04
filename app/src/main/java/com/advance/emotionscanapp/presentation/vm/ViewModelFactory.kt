package com.advance.emotionscanapp.presentation.vm

import androidx.lifecycle.ViewModel
import com.advance.emotionscanapp.util.di.ServiceLocator
import com.advance.emotionscanapp.presentation.ui.home.HomeViewModel
import com.advance.emotionscanapp.presentation.ui.img.ImgProcessViewModel

@Suppress("UNCHECKED_CAST")
object ViewModelFactory : androidx.lifecycle.ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                val provider = ServiceLocator.provideUserRepository()
                return HomeViewModel(provider) as T
            }
            modelClass.isAssignableFrom(ImgProcessViewModel::class.java) -> {
                return ImgProcessViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.simpleName}")
        }
    }
}