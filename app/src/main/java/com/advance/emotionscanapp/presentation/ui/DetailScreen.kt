package com.advance.emotionscanapp.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.advance.emotionscanapp.domain.model.User

@Composable
fun DetailScreen(
    user: User?
) {

}

@Preview
@Composable
fun PreviewDetailScreen() {
    DetailScreen(
        user = null
    )
}