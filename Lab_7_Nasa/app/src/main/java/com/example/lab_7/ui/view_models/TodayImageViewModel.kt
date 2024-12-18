package com.example.lab_7.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_7.network.ApodApi
import com.example.lab_7.data.ApodResponse
import kotlinx.coroutines.launch

class TodayImageViewModel(private val api: ApodApi) : ViewModel() {
    private val _apod = mutableStateOf<ApodResponse?>(null)
    val apod: ApodResponse? get() = _apod.value

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: String? get() = _errorMessage.value

    fun fetchApod(apiKey: String, date: String? = null) {
        viewModelScope.launch {
            try {
                _apod.value = api.getApod(apiKey, date = date)
                _errorMessage.value = null
                println("Fetched APOD: ${_apod.value}")
            } catch (e: retrofit2.HttpException) {
                _errorMessage.value = if (e.code() == 403) {
                    "Доступ заборонено. Перевірте свій API-ключ."
                } else {
                    "Помилка: ${e.message()}"
                }
                e.printStackTrace()
            } catch (e: Exception) {
                _errorMessage.value = "Невідома помилка: ${e.message}"
                e.printStackTrace()
            }
        }
    }
}
