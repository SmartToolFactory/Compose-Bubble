package com.smarttoolfactory.speechbubble

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.NativePaint
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.debugInspectorInfo
import kotlin.math.roundToInt

@Composable
fun BubbleLayout(
    bubbleState: BubbleState,
    content: @Composable () -> Unit
) {


    Column(Modifier.drawBubble(bubbleState)) {
        content()
    }
}

fun Modifier.drawBubble(bubbleState: BubbleState) = composed(

    // pass inspector information for debug
    inspectorInfo = debugInspectorInfo {
        // name should match the name of the modifier
        name = "drawBubble"
        // add name and value of each argument
        properties["bubbleState"] = bubbleState
    },
    // pass your modifier implementation that resolved per modified element

    factory = {

        var totalSize = Size.Zero

        val alignment = bubbleState.arrowAlignment
        val isHorizontalRightAligned = isHorizontalRightAligned(alignment)
        val isHorizontalLeftAligned = isHorizontalLeftAligned(alignment)
        val isVerticalBottomAligned = isVerticalBottomAligned(alignment)

        val rectContent = remember { BubbleRect() }
        val path = remember { Path() }

        val paint: Paint? = remember(bubbleState) {
            if (bubbleState.shadow != null) {
                Paint()
            } else null
        }

        val frameworkPaint: NativePaint? = remember(bubbleState) {
            if (bubbleState.shadow != null && bubbleState.shadow?.useSoftwareLayer == true) {
                paint?.asFrameworkPaint().apply {

                }
            } else null
        }

        Modifier
            .layout { measurable, constraints ->

                bubbleState.dp = density
                bubbleState.init()

                val placeable = measurable.measure(constraints)

                var desiredWidth = placeable.width
                if (isHorizontalLeftAligned || isHorizontalRightAligned) {
                    desiredWidth += bubbleState.arrowWidth.toInt()
                }

                var desiredHeight: Int = placeable.height
                if (isVerticalBottomAligned) desiredHeight += bubbleState.arrowHeight.toInt()

                totalSize = Size(desiredWidth.toFloat(), desiredHeight.toFloat())

                setContentRect(
                    bubbleState,
                    isHorizontalLeftAligned,
                    isHorizontalRightAligned,
                    isVerticalBottomAligned,
                    rectContent,
                    desiredWidth,
                    desiredHeight,
                )

                getBubbleClipPath(path = path, state = bubbleState, contentRect = rectContent)

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
            .drawBehind {


                println("✏️ DRAWING size: $size,")

                val left = if (isHorizontalLeftAligned) -bubbleState.arrowWidth else 0f

                translate(left = left) {

                    bubbleState.shadow?.let { shadow ->

                        if (shadow.useSoftwareLayer) {
                            this.drawIntoCanvas {

                                val color = shadow.color

                                // Interestingly 1.dp shadow is not equal to Modifier.shadow(1.dp)
                                // so changed 1.dp to 0.5dp to match shadows with Modifier.shadow()
                                val dx = shadow.offsetX.toPx() / 2
                                val dy = shadow.offsetY.toPx() / 2
                                val radius = shadow.shadowRadius.toPx() / 2


                                val shadowColor = color
                                    .copy(alpha = shadow.alpha)
                                    .toArgb()
                                val transparent = color
                                    .copy(alpha = 0f)
                                    .toArgb()

                                frameworkPaint?.let { nativePaint: NativePaint ->
                                    nativePaint.color = transparent

                                    nativePaint.setShadowLayer(
                                        dx,
                                        dy,
                                        radius,
                                        shadowColor
                                    )
                                }

                                paint?.let { paint ->
                                    it.drawPath(path, paint)
                                }

                            }

                        } else {

                            val dx = shadow.offsetX.toPx() / 2
                            val dy = shadow.offsetY.toPx() / 2

                            translate(dx, dy) {
                                drawPath(color = shadow.color.copy(shadow.alpha), path = path)
                            }
                        }
                    }

                    drawPath(path = path, color = bubbleState.backgroundColor)
//                    drawPath(
//                        path = path,
//                        color = Color.Red,
//                        style = Stroke(
//                            width = 2.dp.toPx(),
//                            pathEffect = PathEffect.dashPathEffect(
//                                floatArrayOf(10f, 10f)
//                            )
//                        )
//                    )
//                    drawRect(Color.Red, size = totalSize, style = Stroke(2f))
                }

//                drawRect(Color.Blue, size = size, style = Stroke(2f))
            }

    }
)

private fun setContentRect(
    bubbleState: BubbleState,
    isHorizontalLeftAligned: Boolean,
    isHorizontalRightAligned: Boolean,
    isVerticalBottomAligned: Boolean,
    rectContent: BubbleRect,
    desiredWidth: Int,
    desiredHeight: Int,

    ) {
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
}