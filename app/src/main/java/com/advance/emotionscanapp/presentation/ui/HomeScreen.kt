package com.advance.emotionscanapp.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.advance.emotionscanapp.domain.model.User
import com.advance.emotionscanapp.presentation.ui.home.HomeEvent
import com.advance.emotionscanapp.presentation.ui.home.HomeIntent
import com.advance.emotionscanapp.presentation.ui.home.HomeState
import com.advance.emotionscanapp.presentation.ui.home.HomeViewModel

@Composable
fun HomeScreen (
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigationToDetail: (User) -> Unit
) {
    val context = LocalContext.current

    val state by viewModel.state.observeAsState( HomeState())

    LaunchedEffect(Unit) {
        viewModel.events.observeAsState().value?.let { event ->
            when (event) {
                is HomeEvent.NavigateToUserDetail -> onNavigationToDetail(event.user)
                is HomeEvent.ShowError -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.processIntent(HomeIntent.LoadMoreUsers)
    }

    HomeContent(
        state = state,
        onIntent = viewModel::processIntent
    )
}

@Composable
private fun HomeContent(
    state: HomeState,
    onIntent: (HomeIntent) -> Unit
) {
    val scaffoldState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {

        },
        floatingActionButton = {

        }
    ) { padding ->
        when {
            state.isLoading -> "FullScreenLoading()"
            state.error != null -> "ErrorContent(\n" +
                    "                error = state.error,\n" +
                    "                onRetry = { onIntent(HomeIntent.LoadUsers) }\n" +
                    "            )"
            state.filteredUsers.isEmpty() -> "EmptyContent(\n" +
                    "                onRetry = { onIntent(HomeIntent.LoadUsers) }\n" +
                    "            )"
            else -> UserListContent(
                state = state,
                onIntent = onIntent,
                modifier = Modifier.padding(padding)
            )
        }
    }

}

@Composable
private fun UserListContent(
    state: HomeState,
    onIntent: (HomeIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                if (visibleItems.isNotEmpty() &&
                    visibleItems.last().index >= listState.layoutInfo.totalItemsCount - 5)
                    onIntent(HomeIntent.LoadMoreUsers)
            }
    }

    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.filteredUsers) { user ->
            UserItem(
                user = user,
                onClick = {
                    onIntent(HomeIntent.UserClick(user))
                }
            )
        }
    }

}

@Composable
private fun UserItem(user: User, onClick: () -> Unit) {

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val avatarColor = when (user) {
                is User.AdminUser -> Color.Red
                is User.GuestUser -> Color.Yellow
                is User.RegularUser -> Color.Blue
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(avatarColor.copy(alpha = 0.2F), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = user.name.take(2).uppercase(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = avatarColor
                )
            }
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1F)) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                )
                val userType = when (user) {
                    is User.AdminUser -> "Admin"
                    is User.GuestUser -> "Guest"
                    is User.RegularUser -> "Regular"
                }
                Text(
                    text = userType,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    modifier = Modifier
                        .background(Color.LightGray, RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
            Icon(Icons.Default.AccountBox, contentDescription = "View details")
        }
    }

}