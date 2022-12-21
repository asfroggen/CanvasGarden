package com.esaudev.canvasgarden.presentation.canvas_basic

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.esaudev.canvasgarden.presentation.canvas_basic.clock.ClockWidget

@Composable
fun ClockScreen() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        ClockWidget(
            modifier = Modifier.padding(20.dp)
                .size(300.dp).align(Alignment.Center)
        )
    }
}