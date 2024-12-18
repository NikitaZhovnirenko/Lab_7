package com.example.lab_7.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import com.example.lab_7.ui.view_models.DateSelectionViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelectionScreen(viewModel: DateSelectionViewModel) {
    // контроль видимості для пікера дати
    var isDatePickerVisible by remember { mutableStateOf(true) }
    val datePickerState = rememberDatePickerState()

    Column {
        if (isDatePickerVisible) {
            DatePicker(state = datePickerState)
        }

        val selectedDateMillis = datePickerState.selectedDateMillis

        // оновлення стану, якщо вибрана дата
        LaunchedEffect(selectedDateMillis) {
            selectedDateMillis?.let { millis ->
                val date = Instant.ofEpochMilli(millis)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                viewModel.updateDate(date)
                isDatePickerVisible = false // Приховати календар після вибору дати
            }
        }

        // підтвердження (опціонально)
        Button(onClick = {
            selectedDateMillis?.let { millis ->
                val date = Instant.ofEpochMilli(millis)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                viewModel.updateDate(date)
                isDatePickerVisible = false // Приховати календар після натискання на кнопку
            }
        }) {
            Text(text = "Підтвердити дату")
        }

        // пікер дати
        if (!isDatePickerVisible) {
            Button(onClick = { isDatePickerVisible = true }) {
                Text(text = "Вибрати іншу дату")
            }
        }
    }
}

