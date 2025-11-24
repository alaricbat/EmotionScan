package com.advance.emotionscanapp.presentation.vm

import androidx.lifecycle.ViewModel
import com.advance.emotionscanapp.di.ServiceLocator
import com.advance.emotionscanapp.presentation.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
object ViewModelFactory : androidx.lifecycle.ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                val provider = ServiceLocator.provideUserRepository()
                return HomeViewModel(provider) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.simpleName}")
        }
    }
}