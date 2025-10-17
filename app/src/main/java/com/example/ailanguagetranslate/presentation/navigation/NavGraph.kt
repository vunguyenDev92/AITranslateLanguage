package com.example.ailanguagetranslate.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ailanguagetranslate.LanguageApplication
import com.example.ailanguagetranslate.presentation.screen.LanguageListScreen
import com.example.ailanguagetranslate.presentation.screen.SplashScreen
import com.example.ailanguagetranslate.presentation.viewmodel.LanguageViewModel

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object LanguageList : Screen("language_list")
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val appContainer = (context.applicationContext as LanguageApplication).container

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToList = {
                    navController.navigate(Screen.LanguageList.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
            )
        }

        composable(Screen.LanguageList.route) {
            val viewModel = LanguageViewModel(appContainer.getListLanguageUseCase)
            LanguageListScreen(
                viewModel = viewModel,
                onNavigate = { route -> navController.navigate(route) },
            )
        }
    }
}
