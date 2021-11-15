package com.anilderinbay.to_doapp.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.anilderinbay.to_doapp.R

@Composable
fun DisplayAlertDialog(
    title : String ,
    message : String,
    openDialog : Boolean,
    closeDialog : () -> Unit,
    onYesClicked : () -> Unit,
) {
    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal
                )
            },
            confirmButton =  {
             Button(
                 onClick = {
                     closeDialog()
                     onYesClicked()

             }) {
                Text(text = stringResource(R.string.yes))
            }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {closeDialog() })
                {
                    Text(text = stringResource(R.string.no))
                }
            },
            onDismissRequest = { closeDialog() }
        )
    }
}

