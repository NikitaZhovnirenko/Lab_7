package com.example.lab_7.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apod_table")
data class ApodEntity(
    @PrimaryKey
    val date: String,
    val explanation: String,
    val hdurl: String?,
    val url: String,
    val title: String,
    val timestamp: Long = System.currentTimeMillis()
)
