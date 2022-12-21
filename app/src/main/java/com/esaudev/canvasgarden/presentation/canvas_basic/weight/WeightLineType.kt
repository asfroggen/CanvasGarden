package com.esaudev.canvasgarden.presentation.canvas_basic.weight

sealed class WeightLineType {
    object Normal: WeightLineType()
    object FiveStep: WeightLineType()
    object TenStep: WeightLineType()
}
