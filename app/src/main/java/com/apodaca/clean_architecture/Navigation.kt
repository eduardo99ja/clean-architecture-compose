package com.apodaca.clean_architecture

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.apodaca.clean_architecture.ui.view.LoginScreen
import com.apodaca.clean_architecture.utils.Screens


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Navigation(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        modifier = Modifier.fillMaxSize(),
        startDestination = Screens.SCREEN_LOGIN
    ) {
        composable(Screens.SCREEN_LOGIN) {
            LoginScreen(navController = navController)
        }
    }
}