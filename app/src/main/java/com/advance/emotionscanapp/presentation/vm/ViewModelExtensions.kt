package com.advance.emotionscanapp.presentation.vm

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
inline fun <reified T : ViewModel> diViewModel() : T {
    return viewModel(factory = ViewModelFactory)
}