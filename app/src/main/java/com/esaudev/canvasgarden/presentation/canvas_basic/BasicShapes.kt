package com.esaudev.canvasgarden.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

/**
 * Como entrar a el contexto de canvas/drawScope, en este caso
 * creamos un objeto con nuestro canvas, en el caso de la box
 * el contenido del draw siempre estara atras de nuestro composable
 *
 * Variables importantes:
 * [center] -> Se refiere al centro del canvas, NO de la pantalla
 * [size] -> Tiene el alto y el ancho de nuestro canvas (normalmente
 * definido en el modifier con size o height y width)
 *
 * Funciones importantes:
 * [drawRect] -> Dibuja un rectangulo segun los valores dados
 * [drawCircle] -> Dibuja un circulo segun los valores dados
 * [drawArc] -> Dibuja un arco segun los valores dados
 * [drawOval] -> Dibuja un ovalo segun los valores dados
 * [drawLine] -> Dibuja una linea segun los valores dados
 */
@Composable
fun MyCanvas() {
    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp)
    ) {
        drawRect(
            color = Color.Black,
            size = size
        )

        drawRect(
            color = Color.Red,
            topLeft = Offset(100f, 100f),
            size = Size(100f, 100f),
            style = Stroke(
                width = 3.dp.toPx()
            )
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color.Red, Color.Yellow
                ),
                center = center,
                radius = 100f
            ),
            radius = 100f
        )

        drawArc(
            color = Color.Green,
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = true,
            topLeft = Offset(100f, 500f),
            size = Size(200f, 200f)
        )

        drawOval(
            color = Color.Magenta,
            topLeft = Offset(500f, 100f),
            size = Size(200f, 300f)
        )

        drawLine(
            color = Color.Cyan,
            start = Offset(300f, 700f),
            end = Offset(700f, 700f),
            strokeWidth = 5.dp.toPx()
        )
    }
}

/**
 * Tambien se puede acceder al drawScope desde un box con la
 * funcion drawBehind, cualquier cosa que se dibuje ahi, se pintara
 * detras de nuestro composable
 */
@Composable
fun ExampleWithBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {

            })
}