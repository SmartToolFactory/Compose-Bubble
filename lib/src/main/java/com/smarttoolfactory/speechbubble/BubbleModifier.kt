package com.smarttoolfactory.speechbubble

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

fun Modifier.drawBubble(bubbleState: BubbleState) = composed(

    // pass inspector information for debug
    inspectorInfo = debugInspectorInfo {
        // name should match the name of the modifier
        name = "drawBubble"
        // add name and value of each argument
        properties["bubbleState"] = bubbleState
    },

    factory = {

        val rectContent = remember { BubbleRect() }
        val path = remember { Path() }

        Modifier
            .layout { measurable, constraints ->
                println("Modifier.drawBubble() LAYOUT align:${bubbleState.alignment}")
                measureBubbleResult(bubbleState, measurable, constraints, rectContent, path)
            }
            .materialShadow(bubbleState, path)
            .drawBehind {
                println(
                    "✏️ Modifier.drawBubble() DRAWING align:${bubbleState.alignment}," +
                            " size: $size, path: $path, rectContent: $rectContent"
                )

                val left = if (bubbleState.isHorizontalLeftAligned())
                    -bubbleState.arrowWidth.toPx() else 0f

                translate(left = left) {
                    drawPath(path = path, color = bubbleState.backgroundColor)
                }
            }
            .then(
                bubbleState.padding?.let { padding ->
                    this.padding(
                        padding.start,
                        padding.top,
                        padding.end,
                        padding.bottom
                    )
                } ?: this
            )
    }
)

fun Modifier.drawBubbleWithShape(bubbleState: BubbleState) = composed(

    // pass inspector information for debug
    inspectorInfo = debugInspectorInfo {
        // name should match the name of the modifier
        name = "drawBubble"
        // add name and value of each argument
        properties["bubbleState"] = bubbleState
    },

    factory = {

        val rectContent = remember { BubbleRect() }
        val path = remember { Path() }
        var shapeUpdated by remember { mutableStateOf(false) }

        var shape by remember {
            mutableStateOf(
                GenericShape { size: Size, layoutDirection: LayoutDirection ->

                }
            )
        }

        Modifier
            // Measure layout and set content rectangle and arrow if available
            .layout { measurable, constraints ->
                println(
                    "🍏 drawBubbleWithShape() LAYOUT  align:${bubbleState.alignment}, " +
                            "shape: $shape, path: $path, rect: $rectContent"
                )
                val result =
                    measureBubbleResult(bubbleState, measurable, constraints, rectContent, path)
                if (!shapeUpdated) {
                    shape = GenericShape { size: Size, layoutDirection: LayoutDirection ->

                        val left = if (bubbleState.isHorizontalLeftAligned())
                            -bubbleState.arrowWidth.toPx() else 0f
                        addPath(path, Offset(left, 0f))
                    }
                    shapeUpdated = true
                }

                result
            }
            // Draw shadow
            .then(
                if (bubbleState.shadow != null) {
                    this.shadow(bubbleState.shadow?.offsetX ?: 1.dp, shape)
                } else this
            )
            .background(bubbleState.backgroundColor, shape)
            // Add padding
            .then(
                bubbleState.padding?.let { padding ->
                    this.padding(
                        padding.start,
                        padding.top,
                        padding.end,
                        padding.bottom
                    )
                } ?: this
            )
    }
)

/**
 * Measure layout to create a bubble with rounded rectangle with arrow is [bubbleState]
 * has parameter to draw arrow.
 */
internal fun MeasureScope.measureBubbleResult(
    bubbleState: BubbleState,
    measurable: Measurable,
    constraints: Constraints,
    rectContent: BubbleRect,
    path: Path
): MeasureResult {

    val arrowWidth = bubbleState.arrowWidth.value * density
    val arrowHeight = bubbleState.arrowHeight.value * density

    val placeable = measurable.measure(constraints)

    val isHorizontalRightAligned = bubbleState.isHorizontalRightAligned()
    val isHorizontalLeftAligned = bubbleState.isHorizontalLeftAligned()
    val isVerticalBottomAligned = bubbleState.isVerticalBottomAligned()

    var desiredWidth = placeable.width
    if (isHorizontalLeftAligned || isHorizontalRightAligned) {
        desiredWidth += arrowWidth.toInt()
    }

    println(
        "🚌 measureBubbleResult() align:${bubbleState.alignment}," +
                " desiredWidth: $desiredWidth, placeableWidth: ${placeable.width}"
    )


    var desiredHeight: Int = placeable.height
    if (isVerticalBottomAligned) desiredHeight += arrowHeight.toInt()

    setContentRect(
        bubbleState,
        rectContent,
        desiredWidth,
        desiredHeight,
        density = density
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
            x = arrowWidth.roundToInt()
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

    return layout(desiredWidth, desiredHeight) {
        println(
            "🤡 measureBubbleResult() LAYOUT align: ${bubbleState.alignment}\n" +
                    "x: $x, y: $y, " +
                    "placeable width: ${placeable.width}, " +
                    "height: ${placeable.height}, " +
                    "rect: $rectContent"
        )

        placeable.place(x, y)
    }
}
