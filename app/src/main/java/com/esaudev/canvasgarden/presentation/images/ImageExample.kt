package com.esaudev.canvasgarden.presentation.images

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.esaudev.canvasgarden.R

@Composable
fun ImageExample() {
    val cristianoImage = ImageBitmap.imageResource(id = R.drawable.cristiano)
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawImage(
            image = cristianoImage,
            dstSize = IntSize((400 * cristianoImage.width.toFloat() / cristianoImage.height).toInt(),400),
            dstOffset = IntOffset(100, 100)
        )

        drawCircle(
            color = Color.Red,
            radius = 200f,
            center = Offset(300f, 300f),
            blendMode = BlendMode.Color
        )
    }
}