package com.esaudev.canvasgarden.presentation.canvas_basic.clock

sealed class ClockLineType {
    object Normal: ClockLineType()
    object FiveStep: ClockLineType()
}
