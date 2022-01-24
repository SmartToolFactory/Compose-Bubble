package com.smarttoolfactory.composespeechbubble.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.composespeechbubble.BubbleLayout
import com.smarttoolfactory.composespeechbubble.ui.theme.BackgroundColor
import com.smarttoolfactory.composespeechbubble.ui.theme.DateColor
import com.smarttoolfactory.composespeechbubble.ui.theme.SentMessageColor
import com.smarttoolfactory.speechbubble.*

@Composable
fun DemoDynamicSize() {

    var message by remember { mutableStateOf("Very Long Message") }

    Column(
        modifier = Modifier
            .background(BackgroundColor)
            .padding(8.dp)

    ) {

        LazyColumn(
            modifier = Modifier.weight(1f),
            content = {

                item {
                    RightArrowBubbleLayoutSamples(message)
                }

                item {
                    LeftArrowBubbleColumnSamples(message)
                }

                item {
                    BottomArrowBubbleLayoutSamples(message)
                }
            })

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            value = message,
            label = { Text("Main") },
            placeholder = { Text("Set text to change main width") },
            onValueChange = { newValue: String ->
                message = newValue
            }
        )

    }
}

@Composable
private fun RightArrowBubbleLayoutSamples(message: String) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.None,
                backgroundColor = DateColor,
                cornerRadius = 5.dp,
                shadow = BubbleShadow(
                    elevation = 1.dp
                ),
                padding = Padding(8.dp)
            )

        ) {
            Text(
                "BubbleLayout",
                fontSize = 16.sp,
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End
    ) {

        BubbleLayout(
            bubbleState = rememberBubbleState(
                backgroundColor = SentMessageColor,
                alignment = ArrowAlignment.RightTop,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) { Text(text = message) }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleLayout(
            bubbleState = rememberBubbleState(
                backgroundColor = SentMessageColor,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                alignment = ArrowAlignment.RightTop,
                arrowOffsetY = 6.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) { Text(text = message) }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleLayout(
            bubbleState = rememberBubbleState(
                backgroundColor = SentMessageColor,
                alignment = ArrowAlignment.RightCenter,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                arrowShape = ArrowShape.TRIANGLE_ISOSCELES,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) { Text(text = message) }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleLayout(
            bubbleState = rememberBubbleState(
                backgroundColor = SentMessageColor,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                alignment = ArrowAlignment.RightBottom,
                drawArrow = false,
                cornerRadius = 12.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) {
            Text(text = message)
        }
    }
}

@Composable
private fun LeftArrowBubbleColumnSamples(message: String) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        BubbleColumn(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.None,
                backgroundColor = DateColor,
                cornerRadius = 5.dp,
                shadow = BubbleShadow(
                    elevation = 1.dp
                ),
                padding = Padding(8.dp)
            )

        ) {
            Text(
                "BubbleColumn",
                fontSize = 16.sp,
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        BubbleColumn(
            bubbleState = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.LeftTop,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) { Text(text = message) }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleColumn(
            bubbleState = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.LeftTop,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                arrowOffsetY = 6.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) { Text(text = message) }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleColumn(
            bubbleState = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.LeftCenter,
                arrowShape = ArrowShape.TRIANGLE_ISOSCELES,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) { Text(text = message) }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleColumn(
            bubbleState = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.LeftBottom,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                drawArrow = false,
                cornerRadius = 12.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) {
            Text(text = message)
        }
    }
}

@Composable
private fun BottomArrowBubbleLayoutSamples(message: String) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        BubbleColumn(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.None,
                backgroundColor = Color(0xffffeecc),
                cornerRadius = 5.dp,
                shadow = BubbleShadow(
                    elevation = (.5).dp
                ),
                padding = Padding(8.dp)
            )

        ) {
            Text(
                "BubbleColumn with arrow at bottom",
                fontSize = 16.sp,
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BubbleColumn(
            bubbleState = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.BottomCenter,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) { Text(text = message) }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleColumn(
            bubbleState = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.BottomLeft,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                arrowOffsetY = 6.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) { Text(text = message) }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleColumn(
            bubbleState = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.BottomCenter,
                arrowShape = ArrowShape.TRIANGLE_ISOSCELES,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) { Text(text = message) }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleColumn(
            bubbleState = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.BottomLeft,
                arrowShape = ArrowShape.TRIANGLE_ISOSCELES,
                arrowOffsetX = 8.dp,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) { Text(text = message) }

        BubbleColumn(
            bubbleState = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.BottomRight,
                arrowShape = ArrowShape.TRIANGLE_ISOSCELES,
                arrowOffsetX = (-8).dp,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) { Text(text = message) }


        BubbleColumn(
            bubbleState = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.BottomLeft,
                arrowShape = ArrowShape.TRIANGLE_ISOSCELES,
                arrowOffsetX = 20.dp,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) { Text(text = message) }

        BubbleColumn(
            bubbleState = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.BottomRight,
                arrowShape = ArrowShape.TRIANGLE_ISOSCELES,
                arrowOffsetX = (-20).dp,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) { Text(text = message) }

        BubbleColumn(
            bubbleState = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.BottomRight,
                arrowShape = ArrowShape.TRIANGLE_ISOSCELES,
                arrowWidth = 150.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                padding = Padding(8.dp)
            )
        ) { Text(text = message) }
    }
}

