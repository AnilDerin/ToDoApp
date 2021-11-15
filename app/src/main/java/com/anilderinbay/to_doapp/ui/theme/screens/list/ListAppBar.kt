package com.anilderinbay.to_doapp.ui.theme.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.anilderinbay.to_doapp.R
import com.anilderinbay.to_doapp.components.DisplayAlertDialog
import com.anilderinbay.to_doapp.components.PriorityItem
import com.anilderinbay.to_doapp.data.models.Model.Priority
import com.anilderinbay.to_doapp.ui.theme.*
import com.anilderinbay.to_doapp.ui.theme.viewmodels.SharedViewModel
import com.anilderinbay.to_doapp.util.Action
import com.anilderinbay.to_doapp.util.SearchAppBarState
import com.anilderinbay.to_doapp.util.TrailingIconState

@Composable
fun ListAppBar(
    sharedViewModel : SharedViewModel,
    searchAppBarState : SearchAppBarState,
    searchTextState : String
) {
    when(searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClicked = {
                sharedViewModel.searchAppBarState.value =
                    SearchAppBarState.OPENED
                },
                onSortClicked = {sharedViewModel.persistSortState(it)},
                onDeleteAllConfirmed = {
                    sharedViewModel.action.value = Action.DELETE_ALL
                }
            )
        }
        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = {newText ->
                    sharedViewModel.searchTextState.value = newText
                },
                onCloseClicked = {
                    sharedViewModel.searchAppBarState.value =
                        SearchAppBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                },
                onSearchClicked = {
                    sharedViewModel.searchDatabase(searchQuery = it)
                }
            )
        }
    }


}

@Composable
fun DefaultListAppBar(
    onSearchClicked : () -> Unit,
    onSortClicked : (Priority) -> Unit,
    onDeleteAllConfirmed : () -> Unit

) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.list_screen_title),
                color = MaterialTheme.colors.topAppBarContentColor,
            )
        },
        actions = {
        ListAppBarActions(
            onSearchClicked = onSearchClicked,
            onSortClicked = onSortClicked,
            onDeleteAllConfirmed = onDeleteAllConfirmed
        )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked : () -> Unit,
    onSortClicked : (Priority) -> Unit,
    onDeleteAllConfirmed : () -> Unit
) {
    var openDialog by remember {mutableStateOf(false) }
    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_all_tasks),
        message = stringResource(id = R.string.delete_all_tasks_confirmation),
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = { onDeleteAllConfirmed() }
    )

    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(
        onDeleteAllConfirmed = { openDialog = true})

}

@Composable
fun SearchAction(
    onSearchClicked : () -> Unit
) {


    IconButton(onClick = onSearchClicked) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.search_action),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}


@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false)}
    IconButton(onClick = {expanded = true}
    ) {
    Icon(
        painter = painterResource(id = R.drawable.ic_filter_list),
        contentDescription = stringResource(id = R.string.sort_action),
        tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded ,
            onDismissRequest = { expanded = false}
        ) {
            DropdownMenuItem(
                    onClick = {expanded = false
                    onSortClicked(Priority.LOW)
            }
            ) {
            PriorityItem(priority = Priority.LOW)
            }
            DropdownMenuItem(
                    onClick = {expanded = false
                    onSortClicked(Priority.HIGH)
            }
            ) {
                PriorityItem(priority = Priority.HIGH)
            }
            DropdownMenuItem(
                    onClick = {expanded = false
                    onSortClicked(Priority.NONE)
            }
            ) {
                PriorityItem(priority = Priority.NONE)
            }
        }


    }
}

@Composable
fun DeleteAllAction(
    onDeleteAllConfirmed: () -> Unit
 ) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = stringResource(id = R.string.delete_all_action),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
        DropdownMenuItem(
            onClick = {
                expanded = false
                onDeleteAllConfirmed()
            }
        ) {
        Text (
            modifier = Modifier
                .padding(start = LARGE_PADDING),
            text = stringResource(R.string.delete_all_action),
            style = Typography.subtitle2

        )
        }
        }
    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange : (String) -> Unit,
    onCloseClicked : () -> Unit,
    onSearchClicked :  (String) -> Unit
){
    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.READY_TO_DELETE)
    }

    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(TOP_APP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor

    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = stringResource(R.string.search_placeholder),
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topAppBarContentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.disabled),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.search_icon),
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        when(trailingIconState) {
                            TrailingIconState.READY_TO_DELETE -> {
                                onTextChange("")
                                trailingIconState = TrailingIconState.READY_TO_CLOSE
                            }
                            TrailingIconState.READY_TO_CLOSE -> {
                                if (text.isNotEmpty()) {
                                    onTextChange("")
                                }
                                else {
                                    onCloseClicked()
                                    trailingIconState = TrailingIconState.READY_TO_DELETE
                                    
                                }
                            }
                        }

                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.close_icon),
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.topAppBarContentColor,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )
    }
}

    @Composable
    @Preview
    fun DefaultListAppBarPreview() {
        DefaultListAppBar(
            onSearchClicked = {},
            onSortClicked = {},
            onDeleteAllConfirmed = {}
        )
    }


@Composable
@Preview
private fun SearchAppBarPreview() {
    SearchAppBar(
        text = "",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {}
    )

}