package com.example.lab_7.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_7.data.dao.ApodDao
import com.example.lab_7.data.entity.ApodEntity
import com.example.lab_7.network.ApodApi
import kotlinx.coroutines.launch
import java.time.LocalDate

class DateSelectionViewModel(
    private val api: ApodApi,
    private val dao: ApodDao
) : ViewModel() {
    private val _selectedDate = mutableStateOf<LocalDate?>(null)
    val selectedDate = _selectedDate

    private val _isLoading = mutableStateOf(false)
    val isLoading = _isLoading

    fun updateDate(date: LocalDate) {
        selectedDate.value = date
        fetchApodForDate("uLbu6irN8b1qHE5bJkLgeinyVPgHSxeIcv0OIKWq", date)
    }

    fun fetchApodForDate(apiKey: String, date: LocalDate) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = api.getApod(apiKey, date.toString())
                dao.insert(
                    ApodEntity(
                        date = response.date,
                        explanation = response.explanation,
                        hdurl = response.hdurl,
                        url = response.url,
                        title = response.title
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
