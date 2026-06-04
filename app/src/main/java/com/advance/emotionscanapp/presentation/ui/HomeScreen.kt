package com.advance.emotionscanapp.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.advance.emotionscanapp.R
import com.advance.emotionscanapp.util.log.Log
import com.advance.emotionscanapp.presentation.ui.home.HomeEvent
import com.advance.emotionscanapp.presentation.ui.home.HomeIntent
import com.advance.emotionscanapp.presentation.ui.home.HomeState
import com.advance.emotionscanapp.presentation.ui.home.HomeViewModel
import com.advance.emotionscanapp.presentation.vm.diViewModel

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen (
    viewModel: HomeViewModel = diViewModel<HomeViewModel>(),
) {
    val context = LocalContext.current

    val state by viewModel.state.observeAsState(HomeState())

    viewModel.events.observeAsState().value?.let { event ->
        Log.funIn(TAG, "[events.observeAsState()]-[onChange]")
        when (event) {
            is HomeEvent.ShowError -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            is HomeEvent.ShowSuccess -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            is HomeEvent.NavigateToImgProcessScreen -> ImgProcessScreen()
        }
        Log.funOut(TAG, "[events.observeAsState()]-[onChange]")
    }

    HomeContent(
        state = state,
        onIntent = viewModel::processIntent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    state: HomeState,
    onIntent: (HomeIntent) -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Log.i(TAG, "[FloatingActionButton][onClick]: execute.")
                    onIntent(HomeIntent.NavigateToImgProcessScreen)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "Home Page"
                )
            }
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Column {

                Text(
                    text = stringResource(R.string.str_home_screen_title),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(R.string.str_home_screen_author)
                )

            }

        }
    }
}

@Preview
@Composable
fun ShowHomeScreen() {
    HomeScreen()
}