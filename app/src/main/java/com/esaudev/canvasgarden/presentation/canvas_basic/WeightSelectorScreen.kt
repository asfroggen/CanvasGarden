package com.esaudev.canvasgarden.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.esaudev.canvasgarden.presentation.canvas_basic.weight.Scale
import com.esaudev.canvasgarden.presentation.canvas_basic.weight.WeightScaleStyle

@Composable
fun WeightSelectorScreen() {
    var weight by remember {
        mutableStateOf(80)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Scale(
            style = WeightScaleStyle(
                scaleWidth = 150.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.BottomCenter),
        ) {
            weight = it
        }
    }
}