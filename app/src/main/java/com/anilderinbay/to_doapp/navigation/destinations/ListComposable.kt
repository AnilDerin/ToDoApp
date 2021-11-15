package com.anilderinbay.to_doapp.navigation.destinations

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.anilderinbay.to_doapp.ui.theme.screens.list.ListScreen
import com.anilderinbay.to_doapp.ui.theme.viewmodels.SharedViewModel
import com.anilderinbay.to_doapp.util.Constants.LIST_ARGUMENT_KEY
import com.anilderinbay.to_doapp.util.Constants.LIST_SCREEN
import com.anilderinbay.to_doapp.util.toAction

@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen : (taskId : Int) -> Unit,
    sharedViewModel : SharedViewModel,

    ) {
    composable(
        route = LIST_SCREEN,
        arguments = mutableStateListOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        } )
    ) { navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }

        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}