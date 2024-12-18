package com.example.lab_7.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lab_7.ui.view_models.RandomImageViewModel

@Composable
fun RandomImageScreen(viewModel: RandomImageViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { viewModel.loadRandomImage() }) {
            Text("Load Random Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

        viewModel.currentApod.value?.let { apod ->
            Text(text = apod.title)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = apod.url,
                contentDescription = apod.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = apod.explanation)
        }
    }
}
