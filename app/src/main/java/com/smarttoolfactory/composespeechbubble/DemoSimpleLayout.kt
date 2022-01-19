package com.smarttoolfactory.composespeechbubble

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.speechbubble.*
import kotlin.math.roundToInt

@Composable
fun DemoSimpleLayout() {

    Column(
        modifier = Modifier
            .background(Color(0xffFBE9E7))
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Row(modifier = Modifier.fillMaxSize()) {

            val bubbleStateShadow1 = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.LEFT_TOP,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(
                    elevation = 1.dp,
                )
            )

            val bubbleStateShadow2 = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.LEFT_TOP,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(
                    elevation = 1.dp
                )
            )

            val bubbleStateShadow3 = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.BOTTOM_LEFT,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(
                    elevation = 1.dp,
                )
            )

            BubbleColumn(
                modifier = Modifier.background(Color.Yellow),
                bubbleState = bubbleStateShadow1
            ) {
                Text(text = "Composable1")
                Text(text = "Composable12")
            }

            Spacer(modifier = Modifier.width(10.dp))
            BubbleColumn(
                modifier = Modifier.background(Color.Green).padding(10.dp),
                bubbleState = bubbleStateShadow2
            ) {
                Text(text = "Composable1")
                Text(text = "Composable12")
            }
            Spacer(modifier = Modifier.width(8.dp))
            BubbleColumn(
//                modifier = Modifier.background(Color.Red),
                bubbleState = bubbleStateShadow3
            ) {
                Text(text = "Custom", Modifier.background(Color.Magenta))
            }
        }
    }
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
