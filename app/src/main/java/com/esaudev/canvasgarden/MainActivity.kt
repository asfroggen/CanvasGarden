package com.esaudev.canvasgarden

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.esaudev.canvasgarden.presentation.canvas_basic.ClockScreen
import com.esaudev.canvasgarden.presentation.images.ImageExample
import com.esaudev.canvasgarden.presentation.images.ImageReveal
import com.esaudev.canvasgarden.presentation.paths.*
import com.esaudev.canvasgarden.presentation.paths.picker.GenderPicker
import com.esaudev.canvasgarden.presentation.paths.tictactoe.TicTacToeScreen
import com.esaudev.canvasgarden.ui.theme.CanvasGardenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasGardenTheme {
                //MyCanvas()
                //CircleGameScreen()
                //WeightSelectorScreen()
                //ClockScreen()
                //FirstPathExample()
                //SquareOperations()
                //PathLineAnimations()
                //ArrowPathAnimations()
                //ScaleExample()
                //ClippingExample()
                //PathStampedEffect()
                //PathWithText()
                /*GenderPicker(
                    modifier = Modifier.fillMaxSize(),
                    onGenderSelected = {

                    }
                )*/
                //TicTacToeScreen()
                //ImageExample()
                //ImageReveal()
            }
        }
    }
}