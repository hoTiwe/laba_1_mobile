package com.example.circle_wear.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.circle_wear.presentation.AuthScreen
import com.example.circle_wear.presentation.MainScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
) {

    fun navigateByRoute(
        route: String,
        popUpRoute: String? = null,
        isInclusive: Boolean = false,
        isSingleTop: Boolean = true,
    ) {
        navController.navigate(route) {
            popUpRoute?.let { popUpToRoute ->
                popUpTo(popUpToRoute) {
                    inclusive = isInclusive
                }
            }
            launchSingleTop = isSingleTop
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route,
    ) {
        composable(route = Screen.Auth.route) {
            AuthScreen(navigationByRoute = {navigateByRoute(it)})
        }
        composable(route = Screen.Main.route){
            MainScreen()
        }
    }
}