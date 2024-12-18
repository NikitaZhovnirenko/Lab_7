package com.example.lab_7.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_7.data.dao.ApodDao
import com.example.lab_7.data.entity.ApodEntity
import kotlinx.coroutines.launch

class RandomImageViewModel(private val dao: ApodDao) : ViewModel() {
    private val _currentApod = mutableStateOf<ApodEntity?>(null)
    val currentApod = _currentApod

    fun loadRandomImage() {
        viewModelScope.launch {
            _currentApod.value = dao.getRandomApod()
        }
    }
}
