package com.esaudev.canvasgarden.presentation.paths

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun FirstPathExample() {
    /**
     * El path inicia en el 0,0 de la pantalla
     * con moveTo podemos movernos a donde queramos iniciar
     * el dibujo
     */
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(100f, 100f)
            lineTo(100f, 500f)
            lineTo(500f, 500f)

            /**
             * Punto de control, x1 es el pico de la curva, x2 es la conexion con el resto

            quadraticBezierTo(
                x1 = 800f,
                y1 = 300f,
                x2 = 500f,
                y2 = 100f
            )
            */
            cubicTo(
                x1 = 800f,
                y1 = 500f,
                x2 = 800f,
                y2 = 100f,
                x3 = 500f,
                y3 = 100f
            )
            close()
        }

        drawPath(
            path = path,
            color = Color.Red,
            style = Stroke(
                width = 5.dp.toPx(),
                cap = StrokeCap.Round // Hace redondeado los puntos del path
            )
        )
    }
}