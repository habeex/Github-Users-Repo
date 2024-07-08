package com.example.githubrepo.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object HomeScreen : Route(route = "homeScreen")

    object UsersScreen : Route(route = "usersScreen")

    object RepositoryScreen : Route(route = "repositoryScreen")

    object UserDetailsScreen : Route(route = "userDetailsScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object AppNavigation : Route(route = "appNavigation")

    object AppNavigatorScreen : Route(route = "appNavigator")
}

