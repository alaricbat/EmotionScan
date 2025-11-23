package com.advance.emotionscanapp.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.advance.emotionscanapp.R
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.presentation.ui.home.HomeEvent
import com.advance.emotionscanapp.presentation.ui.home.HomeState
import com.advance.emotionscanapp.presentation.ui.home.HomeViewModel

@Composable
fun HomeScreen (
//    viewModel: HomeViewModel = hiltViewModel(),
//    onNavigationToDetail: (User) -> Unit
) {
    val context = LocalContext.current

//    val state by viewModel.state.observeAsState( HomeState())
//
//    viewModel.events.observeAsState().value?.let { event ->
//        when (event) {
//            is HomeEvent.NavigateToUserDetail -> onNavigationToDetail(event.user)
//            is HomeEvent.ShowError -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
//            else -> {
//
//            }
//        }
//    }

    LaunchedEffect(Unit) {

    }

    HomeContent(
//        state = state,
//        onIntent = viewModel::processIntent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
//    state: HomeState,
//    onIntent: (HomeIntent) -> Unit
) {
    val scaffoldState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.str_home_screen_title)
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                }
            ) {
                Text(
                    stringResource(R.string.str_home_screen_floating_btn_title)
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Button(
                onClick = {
                    //TODO
                }
            ) {
                Text(
                    stringResource(R.string.str_home_screen_center_btn)
                )
            }
        }
    }

}

//@Preview
//@Composable
//fun ShowHomeScreen() {
//    HomeScreen {  }
//}