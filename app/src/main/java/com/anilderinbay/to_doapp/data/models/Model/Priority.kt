package com.anilderinbay.to_doapp.data.models.Model


import androidx.compose.ui.graphics.Color
import com.anilderinbay.to_doapp.ui.theme.HighPriorityColor
import com.anilderinbay.to_doapp.ui.theme.LowPriorityColor
import com.anilderinbay.to_doapp.ui.theme.MediumPriorityColor
import com.anilderinbay.to_doapp.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}