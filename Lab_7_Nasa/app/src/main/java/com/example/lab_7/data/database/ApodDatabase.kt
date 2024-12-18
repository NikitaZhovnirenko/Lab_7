package com.example.lab_7.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab_7.data.dao.ApodDao
import com.example.lab_7.data.entity.ApodEntity

@Database(entities = [ApodEntity::class], version = 1)
abstract class ApodDatabase : RoomDatabase() {
    abstract fun apodDao(): ApodDao
}
