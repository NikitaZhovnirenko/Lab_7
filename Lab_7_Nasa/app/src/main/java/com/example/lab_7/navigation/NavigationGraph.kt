package com.example.lab_7.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab_7.ui.screens.*
import com.example.lab_7.ui.view_models.DateSelectionViewModel
import com.example.lab_7.ui.view_models.RandomImageViewModel
import com.example.lab_7.ui.view_models.TodayImageViewModel
import org.koin.androidx.compose.getViewModel
import java.time.LocalDate

const val SCREEN_MENU = "menu"
const val SCREEN_TODAY = "today"
const val SCREEN_DATE_SELECTION = "date_selection"
const val SCREEN_RANDOM = "random"
const val API_KEY = "uLbu6irN8b1qHE5bJkLgeinyVPgHSxeIcv0OIKWq"
@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SCREEN_MENU
    ) {
        composable(route = SCREEN_MENU) {
            MenuScreen(
                onTodayImage = { navController.navigate(SCREEN_TODAY) },
                onDateSelection = { navController.navigate(SCREEN_DATE_SELECTION) },
                onRandomImage = { navController.navigate(SCREEN_RANDOM) }
            )
        }

        composable(route = SCREEN_TODAY) {
            val viewModel: TodayImageViewModel = getViewModel()

            viewModel.fetchApod(API_KEY)
            ApodScreen(viewModel)
        }

        composable(route = SCREEN_DATE_SELECTION) {
            val viewModel: DateSelectionViewModel = getViewModel()
            viewModel.fetchApodForDate(API_KEY, date = LocalDate.now())
            DateSelectionScreen(viewModel)
        }

        composable(route = SCREEN_RANDOM) {
            val viewModel: RandomImageViewModel = getViewModel()

            RandomImageScreen(viewModel)
        }
    }
}
