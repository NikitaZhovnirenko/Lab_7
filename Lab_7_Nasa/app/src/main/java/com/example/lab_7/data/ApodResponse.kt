package com.example.lab_7.data

data class ApodResponse(
    val date: String,
    val explanation: String,
    val hdurl: String?,
    val url: String,
    val title: String
)
