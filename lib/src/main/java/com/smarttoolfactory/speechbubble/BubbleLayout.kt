package com.smarttoolfactory.speechbubble

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onSizeChanged
import kotlin.math.roundToInt

@Composable
fun BubbleLayout(
    bubbleState: BubbleState,
    content: @Composable () -> Unit
) {

    val alignment = bubbleState.arrowAlignment
    val isHorizontalRightAligned = isHorizontalRightAligned(alignment)
    val isHorizontalLeftAligned = isHorizontalLeftAligned(alignment)
    val isVerticalBottomAligned = isVerticalBottomAligned(alignment)

    var totalSize = Size.Zero
    val rectContent = BubbleRect()


    val modifier = Modifier
        .layout { measurable, constraints ->

            bubbleState.dp = density
            bubbleState.init()

            val placeable = measurable.measure(constraints)

            var desiredWidth = placeable.width

            if (isHorizontalLeftAligned || isHorizontalRightAligned) {
                desiredWidth += bubbleState.arrowWidth
                    .toInt()
            }

            var desiredHeight: Int = placeable.height

            if (isVerticalBottomAligned) desiredHeight += bubbleState.arrowHeight
                .toInt()

            totalSize = Size(desiredWidth.toFloat(), desiredHeight.toFloat())

            when {
                isHorizontalLeftAligned -> {
                    rectContent.set(
                        left = bubbleState.arrowWidth,
                        top = 0f,
                        right = desiredWidth.toFloat(),
                        bottom = desiredHeight.toFloat()
                    )

                }

                isHorizontalRightAligned -> {
                    rectContent.set(
                        0f,
                        0f,
                        desiredWidth.toFloat() - bubbleState.arrowWidth,
                        desiredHeight.toFloat()
                    )

                }

                isVerticalBottomAligned -> {
                    rectContent.set(
                        0f,
                        0f,
                        desiredWidth.toFloat(),
                        desiredHeight.toFloat() - bubbleState.arrowHeight
                    )
                }

                else -> {
                    rectContent.set(
                        0f,
                        0f,
                        desiredWidth.toFloat(),
                        desiredHeight.toFloat()
                    )
                }
            }


            var x = 0
            var y = 0

            when {

                // Arrow on left side
                isHorizontalLeftAligned -> {
                    x = bubbleState.arrowWidth.roundToInt()
                    y = 0
                }

                // Arrow on right side
                isHorizontalRightAligned -> {
                    x = 0
                    y = 0
                }

                // Arrow at the bottom
                isVerticalBottomAligned -> {
                    x = 0
                    y = 0
                }
            }

            layout(desiredWidth, desiredHeight) {
                println(
                    "🤡 LAYOUT x: $x, y: $y, " +
                            "placeable width: ${placeable.width}, height: ${placeable.height}, " +
                            " totalSize: $totalSize\n" +
                            "rect: $rectContent"
                )

                placeable.place(x, y)
            }

        }
//        .drawWithContent {
//            val path = Path()
//            getBubbleClipPath(path = path, state = bubbleState, contentRect = rectContent)
//
//            println("✏️ DRAWING size: $size,")
//            translate(left = -bubbleState.arrowWidth) {
//                drawPath(path = path, color = bubbleState.backgroundColor)
//                drawRect(Color.Red, size = totalSize, style = Stroke(2f))
//            }
//            drawContent()
//            drawRect(Color.Blue, size = size, style = Stroke(2f))
//
//        }
        .drawBehind {
            val path = Path()
            getBubbleClipPath(path = path, state = bubbleState, contentRect = rectContent)

            println("✏️ DRAWING size: $size,")

            val left = if (isHorizontalLeftAligned) -bubbleState.arrowWidth else 0f

            translate(left = left) {
                drawPath(path = path, color = bubbleState.backgroundColor)
                drawRect(Color.Red, size = totalSize, style = Stroke(2f))
            }
            drawRect(Color.Blue, size = size, style = Stroke(2f))
        }
        .onSizeChanged {
            println("🚀 onSizeChanged size: $it")
        }

    Column(modifier) {
        content()
    }
}