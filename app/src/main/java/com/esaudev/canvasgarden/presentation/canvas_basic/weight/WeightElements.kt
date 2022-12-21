package com.esaudev.canvasgarden.presentation.canvas_basic.weight

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.core.graphics.withRotation
import kotlin.math.*

@Composable
fun Scale(
    modifier: Modifier = Modifier,
    style: WeightScaleStyle = WeightScaleStyle(),
    minWeight: Int = 20,
    maxWeight: Int = 250,
    initialWeight: Int = 80,
    onWeightChange: (Int) -> Unit
) {
    val radius = style.radius
    val scaleWidth = style.scaleWidth

    /**
     * Centro del sistema coordenado del Canvas
     */
    var center by remember {
        mutableStateOf(Offset.Zero)
    }

    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }

    var angle by remember {
        mutableStateOf(0f)
    }

    var dragStartedAngle by remember {
        mutableStateOf(0f)
    }

    var oldAngle by remember {
        mutableStateOf(angle)
    }

    Canvas(
        modifier = modifier
            .pointerInput(true) {
                detectDragGestures(
                    onDragStart = { offset ->
                        dragStartedAngle = -atan2(
                            y = circleCenter.x - offset.x,
                            x = circleCenter.y - offset.y
                        ) * (180/ PI).toFloat()
                    },
                    onDragEnd = {
                        oldAngle = angle
                    }
                ) { change, _ ->
                    val touchAngle = -atan2(
                        y = circleCenter.x - change.position.x,
                        x = circleCenter.y - change.position.y
                    ) * (180/ PI).toFloat()

                    val newAngle = oldAngle + (touchAngle - dragStartedAngle)
                    angle = newAngle.coerceIn(
                        minimumValue = initialWeight - maxWeight.toFloat(),
                        maximumValue = initialWeight - minWeight.toFloat()
                    )
                    onWeightChange((initialWeight - angle).roundToInt())
                }
            }
    ) {
        center = this.center
        /**
         * circleCenter es el centro del circulo exterior y se
         * encuentra en la parte de hasta abajo de la pantalla
         */
        circleCenter = Offset(
            center.x,
            scaleWidth.toPx() / 2f + radius.toPx()
        )

        val outerRadius = radius.toPx() + scaleWidth.toPx() / 2f
        val innerRadius = radius.toPx() - scaleWidth.toPx() / 2f

        /**
         * Canvas compose no soporta aun sombras, por lo tanto
         * tendremos que usar el native canvas
         */
        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                circleCenter.x,
                circleCenter.y,
                radius.toPx(),
                Paint().apply {
                    strokeWidth = scaleWidth.toPx()
                    color = Color.WHITE
                    setStyle(Paint.Style.STROKE)
                    setShadowLayer(
                        60f,
                        0f,
                        0f,
                        Color.argb(50, 0, 0, 0)
                    )
                }
            )
        }

        /**
         * Para pintar las lineas obtenemos todos los angulos desde el
         * peso minimo al maximo
         */
        for (i in minWeight ..maxWeight) {
            val angleInRad = (i - initialWeight + angle - 90) * (PI / 180f).toFloat()
            val weightLineType = when {
                i % 10 == 0 -> WeightLineType.TenStep
                i % 5 == 0 -> WeightLineType.FiveStep
                else -> WeightLineType.Normal
            }

            val lineLength = when(weightLineType) {
                WeightLineType.Normal -> style.normalLineLength.toPx()
                WeightLineType.FiveStep -> style.fiveStepLineLength.toPx()
                WeightLineType.TenStep -> style.tenStepLineLength.toPx()
            }

            val lineColor = when(weightLineType) {
                WeightLineType.Normal -> style.normalLineColor
                WeightLineType.FiveStep -> style.fiveStepLineColor
                WeightLineType.TenStep -> style.tenStepLineColor
            }

            /**
             * El punto inferior de la linea
             */
            val lineStart = Offset(
                x = (outerRadius - lineLength) * cos(angleInRad) + circleCenter.x,
                y = (outerRadius - lineLength) * sin(angleInRad) + circleCenter.y
            )

            /**
             * El punto superiror de la linea
             */
            val lineEnd = Offset(
                x = outerRadius * cos(angleInRad) + circleCenter.x,
                y = outerRadius * sin(angleInRad) + circleCenter.y
            )

            drawContext.canvas.nativeCanvas.apply {
                if (weightLineType is WeightLineType.TenStep) {
                    val textRadius = (outerRadius - lineLength - 5.dp.toPx() - style.textSize.toPx())
                    val x = textRadius * cos(angleInRad) + circleCenter.x
                    val y = textRadius * sin(angleInRad) + circleCenter.y

                    withRotation(
                        degrees = angleInRad * (180/ PI).toFloat() + 90f,
                        pivotX = x,
                        pivotY = y
                    ) {
                        drawText(
                            abs(i).toString(),
                            x,
                            y,
                            Paint().apply {
                                textSize = style.textSize.toPx()
                                textAlign = Paint.Align.CENTER
                            }
                        )
                    }
                }
            }

            drawLine(
                color = lineColor,
                start = lineStart,
                end = lineEnd,
                strokeWidth = 1.dp.toPx()
            )

            /**
             * Dibujando el triangulo central
             */
            val middleTop = Offset(
                x = circleCenter.x,
                y = circleCenter.y - innerRadius - style.scaleIndicatorLength.toPx()
            )
            val middleBot = Offset(
                x = circleCenter.x,
                y = circleCenter.y - innerRadius
            )
            val bottomLeft = Offset(
                x = circleCenter.x - 4f,
                y = circleCenter.y - innerRadius
            )
            val bottomRight = Offset(
                x = circleCenter.x + 4f,
                y = circleCenter.y - innerRadius
            )

            val indicator = Path().apply {
                moveTo(middleTop.x, middleTop.y)
                lineTo(bottomLeft.x, bottomLeft.y)
                lineTo(bottomRight.x, bottomRight.y)
                lineTo(middleTop.x, middleTop.y)
            }

            drawLine(
                start = middleTop,
                end = middleBot,
                color = style.scaleIndicatorColor,
                strokeWidth = 1.dp.toPx()
            )

            /**
             * Dibuja un triangulo no hd
             * drawPath(
            path = indicator,
            color = style.scaleIndicatorColor
            )
             */

        }

    }
}