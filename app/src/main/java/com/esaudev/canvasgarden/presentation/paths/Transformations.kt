package com.esaudev.canvasgarden.presentation.paths

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate


/**
 * Rota nuestro canvas
 */
@Composable
fun RotateExample() {
    Canvas(modifier = Modifier.fillMaxSize()){
        rotate(45f, pivot = Offset(200f, 200f)) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(100f, 100f),
                size = Size(200f, 200f)
            )
        }
    }
}

/**
 * Mueve nuestro canvas
 */
@Composable
fun TranslateExample() {
    Canvas(modifier = Modifier.fillMaxSize()){
        translate(left = 300f, top = 300f) {
            rotate(45f, pivot = Offset(100f, 100f)) {
                drawRect(
                    color = Color.Red,
                    topLeft = Offset(100f, 100f),
                    size = Size(200f, 200f)
                )
            }
        }
    }
}


/**
 * Cambia la escala de nuestro canvas
 */
@Composable
fun ScaleExample() {
    Canvas(modifier = Modifier.fillMaxSize()){
        scale(0.5f, pivot = Offset(200f, 200f)) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(100f, 100f),
                size = Size(200f, 200f)
            )
        }
    }
}