package com.example.lab_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lab_7.navigation.NavigationGraph
import com.example.lab_7.ui.theme.Lab_7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab_7Theme {
                NavigationGraph()
            }
        }
    }
}

/*
// моделька даних
data class ApodResponse(
    val date: String,
    val explanation: String,
    val hdurl: String?,
    val url: String,
    val title: String
)

// Retrofit API сервіс
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

// ViewModel
class ApodViewModel(private val api: ApodApi) : ViewModel() {
    // додав mutable state, оскільки була проблема на відмальовуванні даних та пікчі
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


// Koin module
val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(ApodApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApodApi::class.java)
    }
    factory { ApodViewModel(get()) }
}

// Main Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab_7Theme {
                val viewModel: ApodViewModel = getViewModel()
                val apiKey = "uLbu6irN8b1qHE5bJkLgeinyVPgHSxeIcv0OIKWq" // ключ

                viewModel.fetchApod(apiKey)


                ApodScreen(viewModel)
            }
        }
    }
}

// UI Function
@Composable
fun ApodScreen(viewModel: ApodViewModel) {
    val apod = viewModel.apod
    val errorMessage = viewModel.errorMessage

    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = Color.Red,
            modifier = Modifier.padding(16.dp)
        )
    } else if (apod != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = apod.title, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = apod.date, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberImagePainter(
                    data = apod.url,
                    builder = {
                        crossfade(true)
                        placeholder(android.R.drawable.ic_menu_gallery)
                        error(android.R.drawable.ic_menu_close_clear_cancel)
                    }
                ),
                contentDescription = apod.title,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = apod.explanation, style = MaterialTheme.typography.bodySmall)
        }
    } else {
        Text(text = "Завантаження...", modifier = Modifier.padding(16.dp))
    }
}

// Application class
class Lab7App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Lab7App)
            modules(appModule)
        }
    }
}

*/