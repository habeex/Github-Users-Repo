package com.example.githubrepo.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.githubrepo.presentation.navigator.AppNavigator

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppNavigation.route,
            startDestination = Route.AppNavigatorScreen.route
        ) {
            composable(route = Route.AppNavigatorScreen.route){
                AppNavigator()
            }
        }
    }
}