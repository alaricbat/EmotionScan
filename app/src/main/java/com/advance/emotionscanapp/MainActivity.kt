package com.advance.emotionscanapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.advance.emotionscanapp.presentation.ui.HomeScreen
import com.advance.emotionscanapp.presentation.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeScreen (
                homeViewModel,
                {
                    Log.d("MainActivity", "onNavigationToDetail")
                }
            )
        }
    }
}