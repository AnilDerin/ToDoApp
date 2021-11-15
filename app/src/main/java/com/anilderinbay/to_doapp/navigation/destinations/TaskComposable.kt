package com.anilderinbay.to_doapp.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.anilderinbay.to_doapp.ui.theme.screens.list.task.TaskScreen
import com.anilderinbay.to_doapp.ui.theme.viewmodels.SharedViewModel
import com.anilderinbay.to_doapp.util.Action
import com.anilderinbay.to_doapp.util.Constants
import com.anilderinbay.to_doapp.util.Constants.TASK_ARGUMENT_KEY
import com.anilderinbay.to_doapp.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    sharedViewModel : SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = mutableStateListOf(navArgument(Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        } )
    ) {
        navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        LaunchedEffect(key1 = taskId) {
            sharedViewModel.getSelectedTask(taskId = taskId)
        }
        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask) {
            if (selectedTask != null || taskId == -1){
                sharedViewModel.updateTaskFields(selectedTask = selectedTask)
            }
        }


        TaskScreen(
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel,
            navigateToListScreen = navigateToListScreen
        )

    }
}