package com.smarttoolfactory.speechbubble

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Constraints
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
                measureBubbleResult(bubbleState, measurable, constraints, rectContent, path)
            }
            .materialShadow(bubbleState, path)
            .drawBehind {
                println("✏️ DRAWING size: $size,")

                val left = if (bubbleState.isHorizontalLeftAligned())
                    -bubbleState.arrowWidth.toPx() else 0f

                translate(left = left) {
                    drawPath(path = path, color = bubbleState.backgroundColor)
                }
            }
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
            "🤡 LAYOUT x: $x, y: $y, " +
                    "placeable width: ${placeable.width}, " +
                    "height: ${placeable.height}, " +
                    "rect: $rectContent"
        )

        placeable.place(x, y)
    }
}
