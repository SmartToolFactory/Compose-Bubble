package com.smarttoolfactory.speechbubble

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
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

        val rectContent = remember { BubbleRect() }
        val path = remember { Path() }

        Modifier
            .layout { measurable, constraints ->

                bubbleState.dp = density
                bubbleState.init()

                val placeable = measurable.measure(constraints)

                val isHorizontalRightAligned = bubbleState.isHorizontalRightAligned()
                val isHorizontalLeftAligned = bubbleState.isHorizontalLeftAligned()
                val isVerticalBottomAligned = bubbleState.isVerticalBottomAligned()

                var desiredWidth = placeable.width
                if (isHorizontalLeftAligned || isHorizontalRightAligned) {
                    desiredWidth += bubbleState.arrowWidth.toInt()
                }

                var desiredHeight: Int = placeable.height
                if (isVerticalBottomAligned) desiredHeight += bubbleState.arrowHeight.toInt()

                setContentRect(
                    bubbleState,
                    rectContent,
                    desiredWidth,
                    desiredHeight,
                )

                getBubbleClipPath(
                    path = path,
                    state = bubbleState,
                    contentRect = rectContent,
                    density = density
                )

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
                                "placeable width: ${placeable.width}, " +
                                "height: ${placeable.height}, " +
                                "rect: $rectContent"
                    )

                    placeable.place(x, y)
                }

            }
            .materialShadow(bubbleState, path)
            .drawBehind {
                println("✏️ DRAWING size: $size,")

                val left =
                    if (bubbleState.isHorizontalLeftAligned()) -bubbleState.arrowWidth else 0f

                translate(left = left) {
                    drawPath(path = path, color = bubbleState.backgroundColor)
                }
            }

    }
)

private fun setContentRect(
    bubbleState: BubbleState,
    rectContent: BubbleRect,
    desiredWidth: Int,
    desiredHeight: Int,

    ) {

    val isHorizontalRightAligned = bubbleState.isHorizontalRightAligned()
    val isHorizontalLeftAligned = bubbleState.isHorizontalLeftAligned()
    val isVerticalBottomAligned = bubbleState.isVerticalBottomAligned()


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

private fun Modifier.materialShadow(bubbleState: BubbleState, path: Path) = composed(
    inspectorInfo = {
        name = "shadow"
        value = bubbleState.shadow
    },
    factory = {

        val paint: Paint = remember(bubbleState) {
            Paint()
        }

        val frameworkPaint: NativePaint = remember(bubbleState) {
            paint.asFrameworkPaint()
        }

        bubbleState.shadow?.let { shadow: BubbleShadow ->
            drawBehind {

                val isHorizontalLeftAligned = bubbleState.isHorizontalLeftAligned()

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

                                frameworkPaint.color = transparent

                                frameworkPaint.setShadowLayer(
                                    dx,
                                    dy,
                                    radius,
                                    shadowColor
                                )

                                it.drawPath(path, paint)
                            }

                        } else {

                            val dx = shadow.offsetX.toPx() / 2
                            val dy = shadow.offsetY.toPx() / 2

                            translate(dx, dy) {
                                drawPath(color = shadow.color.copy(shadow.alpha), path = path)
                            }
                        }
                    }
                    drawPath(path = path, color = shadow.color)
                }
            }
        } ?: this
    }
)
