package com.smarttoolfactory.speechbubble


import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.*
import kotlin.math.roundToInt

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

    println(
        "🚌 measureBubbleResult() align:${bubbleState.alignment}, arrowWidth: $arrowWidth, " +
                "placeableWidth: ${placeable.width}, desiredWidth: $desiredWidth\n" +
                "constraints: $constraints"
    )


    // Position of content(Text or Column/Row/Box for instance) in Bubble
    // These positions effect placeable area for our content
    // if xPos is greater than 0 it's required to translate background path(bubble) to match total
    // area since left of  xPos is not usable(reserved for arrowWidth) otherwise
    val xPos = if (isHorizontalLeftAligned) arrowWidth else 0
    val yPos = if (isVerticalTopAligned) arrowHeight else 0


    return layout(desiredWidth, desiredHeight) {
//        println(
//            "🤡 measureBubbleResult() LAYOUT align: ${bubbleState.alignment}\n" +
//                    "x: $xPos, y: $yPos, " +
//                    "placeable width: ${placeable.width}, " +
//                    "height: ${placeable.height}, " +
//                    "rect: $rectContent"
//        )

        placeable.placeRelative(xPos, yPos)
    }
}

