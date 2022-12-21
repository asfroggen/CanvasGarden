package com.esaudev.canvasgarden.presentation.canvas_basic.clock

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ClockStyle (
    val radius: Dp = 100.dp,
    val normalLineColor: Color = Color.LightGray,
    val fiveStepLineColor: Color = Color.Black,
    val normalLineLength: Dp = 15.dp,
    val fiveStepLineLength: Dp = 25.dp,
    val secondsIndicatorColor: Color = Color.Black,
    val minutesIndicatorColor: Color = Color.Black,
    val hoursIndicatorColor: Color = Color.Green,
    val secondsIndicatorLength: Dp = 80.dp,
    val minutesIndicatorLength: Dp = 60.dp,
    val hoursIndicatorLength: Dp = 50.dp,
    val textSize: TextUnit = 18.sp
)
