package com.example.githubrepo.presentation.navigator

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.githubrepo.R
import com.example.githubrepo.data.manager.AppConnectivityManger
import com.example.githubrepo.data.manager.ConnectionState
import com.example.githubrepo.presentation.home.HomeScreen
import com.example.githubrepo.presentation.navgraph.Route
import com.example.githubrepo.presentation.repositories.RepositoriesScreen
import com.example.githubrepo.presentation.repositories.state.ReposViewModel
import com.example.githubrepo.presentation.userdetails.UserDetailsScreen
import com.example.githubrepo.presentation.userdetails.state.UserEvent
import com.example.githubrepo.presentation.userdetails.state.UserViewModel
import com.example.githubrepo.presentation.users.UsersScreen
import com.example.githubrepo.presentation.users.viewmodel.UsersViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigator(
    connectivityManger: AppConnectivityManger
) {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, selectedIcon = R.drawable.ic_selcted_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, selectedIcon = R.drawable.ic_selected_search, text = "Repositories"),
            BottomNavigationItem(icon = R.drawable.ic_user, selectedIcon = R.drawable.ic_selected_user, text = "Users"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val userViewModel: UserViewModel = hiltViewModel()

    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.RepositoryScreen.route -> 1
        Route.UsersScreen.route -> 2
        else -> 0
    }

    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.RepositoryScreen.route ||
                backStackState?.destination?.route == Route.UsersScreen.route
    }

    LaunchedEffect(key1 = true) {
        connectivityManger.connectionState.collect {
            when (it) {
                ConnectionState.Connected -> {
                    snackbarHostState.currentSnackbarData?.dismiss()
                }

                ConnectionState.NotConnected -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "No internet connection",
                            duration = SnackbarDuration.Indefinite
                        )
                    }
                }

                ConnectionState.Unknown -> {
                    snackbarHostState.currentSnackbarData?.dismiss()
                }
            }

        }

    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            AppBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.RepositoryScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.UsersScreen.route
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                HomeScreen(onNavigate = { route ->
                    navigateToTab(
                        navController = navController,
                            route = route
                    )
                })
            }
            composable(route = Route.UsersScreen.route) {
                val viewModel: UsersViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                UsersScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { user ->
                        userViewModel.onEvent(UserEvent.GetUser(user))
                        navigateToDetails(navController)
                    }
                )
            }
            composable(route = Route.RepositoryScreen.route) {
                val viewModel: ReposViewModel = hiltViewModel()
                val state = viewModel.state.value
                RepositoriesScreen(
                    state = state,
                    event = viewModel::onEvent,
                )
            }

            composable(route = Route.UserDetailsScreen.route) {
                val state by userViewModel.state.collectAsState()
                UserDetailsScreen(
                    state = state,
                    navigateUp = { navController.navigateUp() },
                )

            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController) {
    navController.navigate(
        route = Route.UserDetailsScreen.route
    )
}