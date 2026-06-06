package com.advance.emotionscanapp.presentation.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.advance.emotionscanapp.R
import com.advance.emotionscanapp.presentation.ui.img.ImgProcessIntent
import com.advance.emotionscanapp.presentation.ui.img.ImgProcessViewModel
import com.advance.emotionscanapp.presentation.vm.diViewModel
import com.advance.emotionscanapp.util.log.Log

private const val TAG = "ImgScreen"

@Composable
fun ImgProcessScreen(
    viewModel: ImgProcessViewModel = diViewModel<ImgProcessViewModel>()
) {
    ImgContent(
        onIntent = viewModel::processIntent
    )
}

@Composable
fun ImgContent(
    onIntent: (ImgProcessIntent) -> Unit
) {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    var selectedImageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri = uri }
    )

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris -> selectedImageUris = uris }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {

        AsyncImage(
            model = selectedImageUri,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(
                    horizontal = 10.dp,
                    vertical = 10.dp
                ),
            contentScale = ContentScale.Crop
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            item {
                Row (
                    modifier = Modifier.fillMaxWidth().systemBarsPadding(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick =  {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.str_img_process_pick_one_photo)
                        )
                    }
                    Button(
                        onClick = {
                            multiplePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.str_img_process_pick_multiple_photo)
                        )
                    }
                }
            }

            item {
                Button(
                    onClick = {
                        Log.funIn(TAG, "[ImgProcessing][onClick]")
                        onIntent(
                            ImgProcessIntent.ImgProcessing(selectedImageUri)
                        )
                        Log.funOut(TAG, "[ImgProcessing][onClick]")
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(
                            R.string.str_img_process_start_guessing_emotion
                        )
                    )
                }
            }

        }



    }

}

@Preview
@Composable
fun PreviewImgScreen() {
    ImgProcessScreen()
}