package com.anilderinbay.to_doapp.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.anilderinbay.to_doapp.navigation.destinations.listComposable
import com.anilderinbay.to_doapp.navigation.destinations.taskComposable
import com.anilderinbay.to_doapp.ui.theme.viewmodels.SharedViewModel
import com.anilderinbay.to_doapp.util.Constants.LIST_SCREEN


@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController : NavHostController,
    sharedViewModel : SharedViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)

    }

    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {

        listComposable(
                navigateToTaskScreen = screen.task,
                sharedViewModel = sharedViewModel
        )
        taskComposable (
            navigateToListScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
    }
}