package com.anilderinbay.to_doapp.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anilderinbay.to_doapp.R
import com.anilderinbay.to_doapp.data.models.Model.Priority
import com.anilderinbay.to_doapp.ui.theme.PRIORITY_DROP_DOWN_HEIGHT
import com.anilderinbay.to_doapp.ui.theme.PRIORITY_INDICATOR_SIZE
import kotlin.math.exp

@Composable

fun PriorityDropDown(
    priority : Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val angel:Float by animateFloatAsState(
        targetValue = if(expanded) 180f else 0f)

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.background)
        .height(PRIORITY_DROP_DOWN_HEIGHT)
        .clickable { expanded = true }
        .border(
            width = 1.dp,
            color = MaterialTheme.colors.onSurface.copy(
                alpha = ContentAlpha.disabled
            ),
            shape = MaterialTheme.shapes.small
        ),

        verticalAlignment = Alignment.CenterVertically

    ) {
        Canvas(modifier = Modifier
            .size(PRIORITY_INDICATOR_SIZE)
            .weight(1f)


        ){
            drawCircle(color = priority.color)

        }
        Text(
            modifier = Modifier
                .weight(8f),
            text = priority.name,
            style = MaterialTheme.typography.subtitle2,


            )
        IconButton(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .rotate(degrees = angel)
                .weight(weight = 1.5f),

            onClick = {expanded = true}) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(R.string.drop_down_arrow)
            )
        }
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.94f),
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.LOW)
                }

            ) {
                PriorityItem(priority = Priority.LOW)
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.MEDIUM)
                }

            ) {
                PriorityItem(priority = Priority.MEDIUM)
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.HIGH)
                }

            ) {
                PriorityItem(priority = Priority.HIGH)
            }
        }
    }

}

@Composable
@Preview
fun PriorityDropDownPreview() {

    PriorityDropDown(
        priority = Priority.LOW,
        onPrioritySelected = {}
    )
}