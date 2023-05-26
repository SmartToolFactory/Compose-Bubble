@file:OptIn(ExperimentalMaterial3Api::class)

package com.smarttoolfactory.composespeechbubble.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.composespeechbubble.BubbleLayout
import com.smarttoolfactory.composespeechbubble.ui.theme.BackgroundColor
import com.smarttoolfactory.composespeechbubble.ui.theme.DateColor
import com.smarttoolfactory.composespeechbubble.ui.theme.SentMessageColor
import com.smarttoolfactory.speechbubble.*

@Preview
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
                    LeftArrowBubbleLayoutSamples(message)
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
                cornerRadius = 5.dp,
                shadow = BubbleShadow(
                    elevation = 1.dp
                )
            )

        ) {
            Text(
                modifier = Modifier
                    .background(DateColor)
                    .padding(8.dp),
                text = "BubbleLayout",
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
                alignment = ArrowAlignment.RightTop,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp)
            )
        ) {
            Text(
                modifier = Modifier
                    .background(SentMessageColor)
                    .padding(8.dp),
                text = message
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleLayout(
            bubbleState = rememberBubbleState(
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                alignment = ArrowAlignment.RightTop,
                arrowOffsetY = 6.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp)
            )
        ) {
            Text(
                modifier = Modifier
                    .background(SentMessageColor)
                    .padding(8.dp),
                text = message
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.RightCenter,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                arrowShape = ArrowShape.FullTriangle,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
            )
        ) {
            Text(
                modifier = Modifier
                    .background(SentMessageColor)
                    .padding(8.dp),
                text = message
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleLayout(
            bubbleState = rememberBubbleState(
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                alignment = ArrowAlignment.RightBottom,
                drawArrow = false,
                cornerRadius = 12.dp,
                shadow = BubbleShadow(elevation = 1.dp)
            )
        ) {
            Text(
                modifier = Modifier
                    .background(SentMessageColor)
                    .padding(8.dp),
                text = message
            )
        }
    }
}

@Composable
private fun LeftArrowBubbleLayoutSamples(message: String) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.None,
                cornerRadius = 5.dp,
                shadow = BubbleShadow(
                    elevation = 1.dp
                )
            )

        ) {

            Text(
                modifier = Modifier
                    .background(DateColor)
                    .padding(8.dp),
                text = "BubbleLayout",
                fontSize = 16.sp,
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.LeftTop,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp)
            )
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = message
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.LeftTop,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                arrowOffsetY = 6.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp)
            )
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = message
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.LeftCenter,
                arrowShape = ArrowShape.FullTriangle,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp)
            )
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = message
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.LeftBottom,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                drawArrow = false,
                cornerRadius = 12.dp,
                shadow = BubbleShadow(elevation = 1.dp)
            )
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = message
            )
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
        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.None,
                cornerRadius = 5.dp,
                shadow = BubbleShadow(
                    elevation = (.5).dp
                )
            )

        ) {
            Text(
                modifier = Modifier
                    .background(Color(0xffffeecc))
                    .padding(8.dp),
                text = "BubbleLayout with arrow at bottom",
                fontSize = 16.sp,
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.BottomCenter,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
            )
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = message
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.BottomLeft,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                arrowOffsetY = 6.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
            )
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = message
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.BottomCenter,
                arrowShape = ArrowShape.FullTriangle,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp)
            )
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = message
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.BottomLeft,
                arrowShape = ArrowShape.FullTriangle,
                arrowOffsetX = 8.dp,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp)
            )
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = message
            )
        }

        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.BottomRight,
                arrowShape = ArrowShape.FullTriangle,
                arrowOffsetX = (-8).dp,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp)
            )
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = message
            )
        }


        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.BottomLeft,
                arrowShape = ArrowShape.FullTriangle,
                arrowOffsetX = 20.dp,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp)
            )
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = message
            )
        }

        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.BottomRight,
                arrowShape = ArrowShape.FullTriangle,
                arrowOffsetX = (-20).dp,
                arrowWidth = 20.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp)
            )
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = message
            )
        }

        BubbleLayout(
            bubbleState = rememberBubbleState(
                alignment = ArrowAlignment.BottomRight,
                arrowShape = ArrowShape.FullTriangle,
                arrowWidth = 150.dp,
                arrowHeight = 20.dp,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp)
            )
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = message
            )
        }
    }
}

