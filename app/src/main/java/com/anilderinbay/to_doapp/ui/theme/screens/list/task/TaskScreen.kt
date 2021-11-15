package com.anilderinbay.to_doapp.ui.theme.screens.list.task

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.anilderinbay.to_doapp.data.models.Model.Priority
import com.anilderinbay.to_doapp.data.models.Model.ToDoTask
import com.anilderinbay.to_doapp.ui.theme.viewmodels.SharedViewModel
import com.anilderinbay.to_doapp.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel : SharedViewModel,
    navigateToListScreen: (Action) -> Unit
)  {
    val title : String by sharedViewModel.title
    val description : String by sharedViewModel.description
    val priority : Priority by sharedViewModel.priority
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    }else {
                        if (sharedViewModel.validateFields()) {
                            navigateToListScreen(action)
                        } else {
                            displayToast(context = context)
                        }
                    }
                }
            )
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = {
                    sharedViewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    sharedViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.priority.value = it

                }
            )
        }
    )

    }

fun displayToast(context : Context) {
Toast.makeText(
    context,
    "Fields Empty",
    Toast.LENGTH_SHORT
).show()
}
