package com.example.lab_7.data.dao

import androidx.room.*
import com.example.lab_7.data.entity.ApodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ApodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(apod: ApodEntity)

    @Query("SELECT * FROM apod_table WHERE date = :date")
    suspend fun getApodByDate(date: String): ApodEntity?

    @Query("SELECT * FROM apod_table ORDER BY timestamp DESC")
    fun getAllApods(): Flow<List<ApodEntity>>

    @Query("SELECT * FROM apod_table ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomApod(): ApodEntity?

    @Delete
    suspend fun delete(apod: ApodEntity)
}
