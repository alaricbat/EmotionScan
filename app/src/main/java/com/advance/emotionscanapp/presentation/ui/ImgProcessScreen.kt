package com.advance.emotionscanapp.presentation.ui

import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.advance.emotionscanapp.R
import com.advance.emotionscanapp.presentation.ui.img.ImgProcessEvent
import com.advance.emotionscanapp.presentation.ui.img.ImgProcessIntent
import com.advance.emotionscanapp.presentation.ui.img.ImgProcessViewModel
import com.advance.emotionscanapp.presentation.vm.diViewModel
import com.advance.emotionscanapp.util.array.ArrayUtils
import com.advance.emotionscanapp.util.log.Log
import com.advance.emotionscanapp.util.svm.SVMClassifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "ImgProcessScreen"

@Composable
fun ImgProcessScreen(
    viewModel: ImgProcessViewModel = diViewModel<ImgProcessViewModel>()
) {

    val context = LocalContext.current

    var isLoading by remember { mutableStateOf(false) }

    var result by remember { mutableStateOf("") }

    val state by viewModel.state.observeAsState()

    val event by viewModel.events.observeAsState()

    val happyText = stringResource(R.string.str_img_process_happy_label)

    val sadText = stringResource(R.string.str_img_process_sad_label)

    val svmListener: SVMClassifier.SVMClassifierListener = object : SVMClassifier.SVMClassifierListener {
        override fun onPredicted(label: SVMClassifier.SVMClassifierLabel) {
            CoroutineScope(Dispatchers.Main).launch {
                Log.i(TAG, "[onPredicted]: execute.")
                isLoading = false
                result = if (label == SVMClassifier.SVMClassifierLabel.LABEL_HAPPY) happyText else sadText
                Log.i(TAG, "[onPredicted]: result = $result")
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
            }
        }

        override fun onError(msg: String) {
            CoroutineScope(Dispatchers.Main).launch {
                Log.i(TAG, "[onError]: execute.")
                Log.i(TAG, "[onPredicted]: msg = $msg")
                isLoading = false
                result = msg
            }
        }

    }

    SVMClassifier.setListener(svmListener)

    LaunchedEffect(event) {
        event?.let { currentEvent ->
            Log.funIn(TAG, "[events.observeAsState()][onChange]")
            when(currentEvent) {
                is ImgProcessEvent.ImgProcessing -> {
                    isLoading = true
                    val uri = currentEvent.uri ?: return@let

                    delay(1000L)

                    withContext(Dispatchers.IO) {
                        Log.i(TAG, "[events.observeAsState()][onChange]: start predicting.")
                        val inputStream = context.contentResolver.openInputStream(uri)
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        SVMClassifier.predict(ArrayUtils.bitmapToFloatArray(bitmap))
                    }
                }
            }
            Log.funOut(TAG, "[events.observeAsState()][onChange]")
        }
    }
    
    ImgContent(
        isLoading,
        result,
        onIntent = viewModel::processIntent
    )
}

@Composable
fun ImgContent(
    isLoading: Boolean,
    resultString: String,
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

        Column(
            modifier = Modifier.fillMaxWidth()
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
                    )
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(vertical = 10.dp),
                text = resultString,

            )

        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

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