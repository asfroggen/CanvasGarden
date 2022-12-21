package com.esaudev.canvasgarden.presentation.canvas_basic.clock

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ClockWidget(
    modifier: Modifier = Modifier,
    style: ClockStyle = ClockStyle(),
) {

    var secondsAngle by remember {
        mutableStateOf(0f)
    }

    var ticks by remember {
        mutableStateOf(1)
    }

    LaunchedEffect(Unit) {
        while(true) {
            delay(1000)
            if (ticks == 60) {
                ticks = 1
            } else {
                ticks++
            }
        }
    }

    Canvas(modifier = modifier) {
       drawContext.canvas.nativeCanvas.apply {
           drawCircle(
               center.x,
               center.y,
               style.radius.toPx(),
               Paint().apply {
                   color = Color.WHITE
                   setStyle(Paint.Style.FILL)
                   setShadowLayer(
                       60f,
                       0f,
                       0f,
                       Color.argb(50, 0, 0, 0)
                   )
               }
           )
       }

       drawCircle(
           color = androidx.compose.ui.graphics.Color.Black,
           radius = 4.dp.toPx()
       )

       /**
        * Dibujando las lineas indicadoras de los minutos/horas
        */
       for(i in 1..60) {
           val angleInRad = (i * 360/60 * PI/180f).toFloat()

           val clockLineType = when {
               i % 5 == 0 -> ClockLineType.FiveStep
               else -> ClockLineType.Normal
           }

           val lineLength = when(clockLineType) {
               ClockLineType.Normal -> style.normalLineLength.toPx()
               ClockLineType.FiveStep -> style.fiveStepLineLength.toPx()
           }

           val lineColor = when(clockLineType) {
               ClockLineType.Normal -> style.normalLineColor
               ClockLineType.FiveStep -> style.fiveStepLineColor
           }

           /**
            * El punto inferior de la linea
            */
           val lineStart = Offset(
               x = (style.radius.toPx() - lineLength) * cos(angleInRad) + center.x,
               y = (style.radius.toPx() - lineLength) * sin(angleInRad) + center.y
           )

           /**
            * El punto superiror de la linea
            */
           val lineEnd = Offset(
               x = style.radius.toPx() * cos(angleInRad) + center.x,
               y = style.radius.toPx() * sin(angleInRad) + center.y
           )

           drawLine(
               color = lineColor,
               start = lineStart,
               end = lineEnd,
               strokeWidth = 1.dp.toPx()
           )
       }

        /**
         * Dibujando las manecillas
         */


        /**
         * Otro intento de linea de segundos xd
         */

        rotate(degrees = ticks * 360f/60f) {
            drawLine(
                color = style.secondsIndicatorColor,
                start = center,
                end = Offset(center.x, style.secondsIndicatorLength.toPx()),
                strokeWidth = 1.dp.toPx()
            )
        }
   }
}