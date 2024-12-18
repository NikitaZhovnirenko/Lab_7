package com.example.lab_7.network

import com.example.lab_7.data.ApodResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApi {
    @GET("planetary/apod")
    suspend fun getApod(
        @Query("api_key") apiKey: String,
        @Query("date") date: String? = null,
        @Query("start_date") startDate: String? = null,
        @Query("end_date") endDate: String? = null,
        @Query("count") count: Int? = null,
        @Query("thumbs") thumbs: Boolean = false
    ): ApodResponse

    companion object {
        const val BASE_URL = "https://api.nasa.gov/"
    }
}
