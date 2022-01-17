package com.smarttoolfactory.composespeechbubble

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun SimpleLayoutImplementation() {

    Column(
        modifier = Modifier
            .background(Color(0xffFBE9E7))
            .fillMaxSize()
            .padding(8.dp)
    ) {

        //                        MyComposable(modifier = Modifier.background(Color.LightGray))

//                        Spacer(modifier = Modifier.height(8.dp))
//
        CanvasLayout(modifier = Modifier.background(Color.LightGray)) {
            Text("First Text")
            Text("Second Text")
        }
    }
}

@Composable
private fun CanvasLayout(modifier: Modifier = Modifier, content: @Composable () -> Unit) {

//    var widthInDp by remember { mutableStateOf(0.dp) }
//    var heightInDp by remember { mutableStateOf(0.dp) }

    var totalWidth = 0
    var totalHeight = 0

    Layout(
        content = content,
        modifier = modifier
            .drawBehind {
                println("✏️ CanvasLayout() drawBehind() widthInDp: $totalWidth, heightInDp: $totalHeight")

                drawRect(Color.Red, size = Size(totalWidth.toFloat(), totalHeight.toFloat()), style = Stroke(2f))
                drawRect(Color.Blue, size = size, style = Stroke(2f))
            }
    ) { measurables, constraints ->

        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        val maxWidth = placeables.maxOf { it.width }
        val height = placeables.sumOf { it.height }


        totalWidth = maxWidth + 50
        totalHeight = height

        var yPos = 0
        layout(maxWidth, height) {
            println("🚗 CanvasLayout() LAYOUT maxWidth: $maxWidth, height: $height placeables: ${placeables.size}")

            placeables.forEach { placeable: Placeable ->
                placeable.placeRelative(0, yPos)
                yPos += placeable.height
            }
        }
    }

    androidx.compose.foundation.Canvas(
        modifier = Modifier.size(totalWidth.dp, totalHeight.dp),
        onDraw = {
            println("🚙 CanvasLayout() CANVAS size: $size")
            drawRect(Color.Yellow, size = size, style = Stroke(2f))
        }
    )
}

@Composable
private fun MyComposable(modifier: Modifier = Modifier) {

    Row(
        modifier = Modifier

    ) {
        var totalSize = Size.Zero

        val layoutModifier = modifier
            .layout { measurable, constraints ->

                val placeable = measurable.measure(constraints)
                totalSize = Size(placeable.width + 50f, placeable.height.toFloat())

                println("MyComposable() LAYOUT placeable width: ${placeable.width}, height: ${placeable.height}")

                layout(totalSize.width.roundToInt(), placeable.height) {
                    placeable.place(0, 0)
                }

            }
            .drawBehind {
                println("MyComposable() drawBehind size: $size")
                drawRect(Color.Red, size = totalSize, style = Stroke(2f))
                drawRect(Color.Blue, size = size, style = Stroke(2f))
            }


        Column(modifier = layoutModifier) {
            Text("First Text")
            Text("Second Text")
        }

    }
}
