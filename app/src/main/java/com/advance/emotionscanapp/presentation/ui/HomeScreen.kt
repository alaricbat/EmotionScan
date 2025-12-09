package com.advance.emotionscanapp.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.advance.emotionscanapp.R
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.uil.log.Log
import com.advance.emotionscanapp.presentation.ui.home.HomeEvent
import com.advance.emotionscanapp.presentation.ui.home.HomeIntent
import com.advance.emotionscanapp.presentation.ui.home.HomeState
import com.advance.emotionscanapp.presentation.ui.home.HomeViewModel
import com.advance.emotionscanapp.presentation.vm.diViewModel

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen (
    viewModel: HomeViewModel = diViewModel<HomeViewModel>(),
    onNavigationToDetail: @Composable (User) -> Unit
) {
    val context = LocalContext.current

    val state by viewModel.state.observeAsState(HomeState())

    viewModel.events.observeAsState().value?.let { event ->
        Log.funIn(TAG, "[events.observeAsState()]-[onChange]")
        when (event) {
            is HomeEvent.NavigateToUserDetail -> onNavigationToDetail(event.user)
            is HomeEvent.ShowError -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            is HomeEvent.ShowSuccess -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
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

    var tfName by remember { mutableStateOf("") }

    var tfEmail by remember { mutableStateOf("") }

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
                //TODO update later
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                modifier = Modifier.padding(vertical = 10.dp),
                value = tfName,
                onValueChange = {
                    tfName = it
                },
                label = {
                    Text(stringResource(R.string.str_home_screen_user_name_text))
                },
                singleLine = true
            )

            TextField(
                modifier = Modifier.padding(vertical = 10.dp),
                value = tfEmail,
                onValueChange = {
                    tfEmail = it
                },
                label = {
                    Text(stringResource(R.string.str_home_screen_email_text))
                },
                singleLine = true
            )

            Button(
                onClick = {
                    Log.funIn(TAG, "AddUserBtn.onClick")
                    val user = User.RegularUser(1, tfName, tfEmail)
                    onIntent(HomeIntent.InsertUser(user))
                    tfName = ""
                    tfEmail = ""
                    Log.funOut(TAG, "AddUserBtn.onClick")
                }
            ) {
                Text(
                    stringResource(R.string.str_home_screen_add_user_btn)
                )
            }
        }

        if (state.isLoading) {
            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

    }
}

@Preview
@Composable
fun ShowHomeScreen() {
    HomeScreen(
        onNavigationToDetail = { null }
    )
}