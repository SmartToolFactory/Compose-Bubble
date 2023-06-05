package com.smarttoolfactory.speechbubble

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.*
import kotlin.math.roundToInt

fun Modifier.bubble(
    bubbleState: BubbleState,
    color: Color = Color.Transparent,
    shadow: BubbleShadow? = null,
    borderStroke: BorderStroke? = null
) = composed(
    // pass inspector information for debug
    inspectorInfo = debugInspectorInfo {
        // name should match the name of the modifier
        name = "drawBubble"
        // add name and value of each argument
        properties["bubbleState"] = bubbleState
        properties["color"] = color
        properties["shadow"] = shadow
        properties["borderStroke"] = borderStroke
    },

    factory = {

        val density = LocalDensity.current
        val shape = remember(
            key1 = bubbleState
        ) {
            createBubbleShape(bubbleState, density.density)
        }

        Modifier
            .then(
                if (shadow != null) {
                    Modifier.shadow(
                        elevation = shadow.elevation,
                        ambientColor = shadow.ambientColor,
                        spotColor = shadow.spotColor,
                        shape = shape
                    )
                } else {
                    Modifier
                }
            )
            .then(
                if (borderStroke != null) {
                    Modifier.border(border = borderStroke, shape = shape)
                } else {
                    Modifier
                }
            )
            .clip(shape)
            .background(color, shape)
            .layout { measurable, constraints ->
                measureBubbleResult(
                    bubbleState, measurable, constraints
                )
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
): MeasureResult {

    val arrowWidth = (bubbleState.arrowWidth.value * density).roundToInt()
    val arrowHeight = (bubbleState.arrowHeight.value * density).roundToInt()

    // Check arrow position
    val isHorizontalLeftAligned = bubbleState.isHorizontalLeftAligned()
    val isVerticalTopAligned = bubbleState.isVerticalTopAligned()
    val isHorizontallyPositioned = bubbleState.isArrowHorizontallyPositioned()
    val isVerticallyPositioned = bubbleState.isArrowVerticallyPositioned()

    // Offset to limit max width when arrow is horizontally placed
    // if we don't remove arrowWidth bubble will overflow from it's parent as much as arrow
    // width is. So we measure our placeable as content + arrow width
    val offsetX: Int = if (isHorizontallyPositioned) {
        arrowWidth
    } else 0

    // Offset to limit max height when arrow is vertically placed

    val offsetY: Int = if (isVerticallyPositioned) {
        arrowHeight
    } else 0

    val placeable = measurable.measure(constraints.offset(-offsetX, -offsetY))

    val desiredWidth = constraints.constrainWidth(placeable.width + offsetX)
    val desiredHeight: Int = constraints.constrainHeight(placeable.height + offsetY)

    val alignment = bubbleState.alignment
    val arrowShape = bubbleState.arrowShape

    val arrowMaxWidth = arrowWidth.coerceAtMost(desiredWidth).toFloat()
    val arrowMaxHeight = arrowHeight.coerceAtMost(desiredHeight).toFloat()

    bubbleState.arrowRect = getArrowRect(
        bubbleState,
        arrowMaxWidth,
        arrowMaxHeight,
        density,
        desiredWidth.toFloat(),
        desiredHeight.toFloat()
    )

    val arrowRect = bubbleState.arrowRect
    val arrowLeft = arrowRect.left
    val arrowRight = arrowRect.right
    val arrowTop = arrowRect.top
    val arrowBottom = arrowRect.bottom

    bubbleState.arrowTip = getArrowTip(
        arrowAlignment = alignment,
        arrowShape = arrowShape,
        arrowLeft = arrowLeft,
        arrowRight = arrowRight,
        arrowTop = arrowTop,
        arrowBottom = arrowBottom,
        arrowWidth = arrowMaxWidth,
        arrowHeight = arrowMaxHeight
    )

    // Position of content(Text or Column/Row/Box for instance) in Bubble
    // These positions effect placeable area for our content
    // if xPos is greater than 0 it's required to translate background path(bubble) to match total
    // area since left of  xPos is not usable(reserved for arrowWidth) otherwise
    val xPos = if (isHorizontalLeftAligned) arrowWidth else 0
    val yPos = if (isVerticalTopAligned) arrowHeight else 0

    return layout(desiredWidth, desiredHeight) {
        placeable.placeRelative(xPos, yPos)
    }
}

