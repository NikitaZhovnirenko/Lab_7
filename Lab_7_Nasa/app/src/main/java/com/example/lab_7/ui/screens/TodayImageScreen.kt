package com.example.lab_7.ui.screens

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.lab_7.ui.view_models.TodayImageViewModel

@Composable
fun ApodScreen(viewModel: TodayImageViewModel) {
    val apod = viewModel.apod
    val errorMessage = viewModel.errorMessage

    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = Color.Red,
            modifier = Modifier.padding(16.dp)
        )
    } else if (apod != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = apod.title, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = apod.date, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = apod.url).apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        placeholder(R.drawable.ic_menu_gallery)
                        error(R.drawable.ic_menu_close_clear_cancel)
                    }).build()
                ),
                contentDescription = apod.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)  // максимальна висота
                    .aspectRatio(16f/9f)
                    .padding(vertical = 8.dp),
                contentScale = ContentScale.Fit  // масштаб за aspectRatio
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = apod.explanation, style = MaterialTheme.typography.bodySmall)
        }
    } else {
        Text(text = "Завантаження...", modifier = Modifier.padding(16.dp))
    }
}
