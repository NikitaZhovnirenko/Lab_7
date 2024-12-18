package com.example.lab_7.di

import androidx.room.Room
import com.example.lab_7.data.database.ApodDatabase
import com.example.lab_7.network.ApodApi
import com.example.lab_7.ui.view_models.DateSelectionViewModel
import com.example.lab_7.ui.view_models.RandomImageViewModel
import com.example.lab_7.ui.view_models.TodayImageViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(ApodApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApodApi::class.java)
    }
    factory { TodayImageViewModel(get()) }
    factory { DateSelectionViewModel(get(), get()) }
    factory { RandomImageViewModel(get()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            ApodDatabase::class.java,
            "apod_database"
        ).build()
    }
    single { get<ApodDatabase>().apodDao() }
}
