package com.smarttoolfactory.bubble

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path

fun getArrowRect(
    state: BubbleState,
    arrowWidth: Float,
    arrowHeight: Float,
    density: Float,
    contentWidth: Float,
    contentHeight: Float
): BubbleRect {
    val isHorizontalArrow = state.isArrowHorizontallyPositioned()

    return if (isHorizontalArrow) {
        // This is offset from top/bottom or center for arrows on left or right.
        // Maximum offset + arrow height cannot be bigger
        // than bottom of bubble or smaller than top of bubble.
        val arrowTop: Float = calculateArrowTopPosition(
            state,
            arrowHeight,
            contentHeight,
            density
        )

        val arrowBottom = arrowTop + arrowHeight
        val arrowLeft = if (state.isHorizontalLeftAligned()) 0f
        else contentWidth - arrowWidth
        val arrowRight = arrowLeft + arrowWidth

        BubbleRect(
            left = arrowLeft,
            top = arrowTop,
            right = arrowRight,
            bottom = arrowBottom
        )
    } else {
        val arrowLeft = calculateArrowLeftPosition(
            state,
            arrowWidth,
            contentWidth,
            density
        )

        val arrowRight = arrowLeft + arrowWidth
        val arrowTop = if (state.isVerticalBottomAligned()) {
            contentHeight - arrowHeight
        } else {
            0f
        }
        val arrowBottom = arrowTop + arrowHeight

        BubbleRect(
            left = arrowLeft,
            top = arrowTop,
            right = arrowRight,
            bottom = arrowBottom
        )
    }
}

/**
 * Retrieve rectangle for measuring for space to be used content other than arrow itself.
 *
 * @param bubbleState state that contains bubble properties

 * @param width is the total width reserved for content and arrow if available in horizontal position
 * @param height is the total height reserved for content and arrow if available in vertical position
 */
internal fun getContentRect(
    bubbleState: BubbleState,
    width: Int,
    height: Int,
    density: Float
): BubbleRect {

    val isHorizontalRightAligned = bubbleState.isHorizontalRightAligned()
    val isHorizontalLeftAligned = bubbleState.isHorizontalLeftAligned()
    val isVerticalBottomAligned = bubbleState.isVerticalBottomAligned()
    val isVerticalTopAligned = bubbleState.isVerticalTopAligned()

    val arrowWidth = bubbleState.arrowWidth.value * density
    val arrowHeight = bubbleState.arrowHeight.value * density

    return when {
        isHorizontalLeftAligned -> {
            BubbleRect(
                left = arrowWidth,
                top = 0f,
                right = width.toFloat(),
                bottom = height.toFloat()
            )

        }

        isHorizontalRightAligned -> {
            BubbleRect(
                left = 0f,
                top = 0f,
                right = width.toFloat() - arrowWidth,
                bottom = height.toFloat()
            )

        }

        isVerticalBottomAligned -> {
            BubbleRect(
                left = 0f,
                top = 0f,
                right = width.toFloat(),
                bottom = height.toFloat() - arrowHeight
            )
        }

        isVerticalTopAligned -> {
            BubbleRect(
                left = 0f,
                top = arrowHeight,
                right = width.toFloat(),
                bottom = height.toFloat()
            )
        }

        else -> {
            BubbleRect(
                left = 0f,
                top = 0f,
                right = width.toFloat(),
                bottom = height.toFloat()
            )
        }
    }
}

internal fun Path.addRoundedRect(
    radiusTopLeft: Float,
    radiusTopRight: Float,
    radiusBottomRight: Float,
    radiusBottomLeft: Float,
    topLeft: Offset,
    size: Size
) {
    val topLeftRadius = radiusTopLeft * 2
    val topRightRadius = radiusTopRight * 2
    val bottomRightRadius = radiusBottomRight * 2
    val bottomLeftRadius = radiusBottomLeft * 2

    val width = size.width
    val height = size.height

    // Top left arc
    arcTo(
        rect = Rect(
            left = topLeft.x,
            top = topLeft.y,
            right = topLeft.x + topLeftRadius,
            bottom = topLeft.y + topLeftRadius
        ),
        startAngleDegrees = 180.0f,
        sweepAngleDegrees = 90.0f,
        forceMoveTo = false
    )

    lineTo(x = topLeft.x + width - topRightRadius, y = topLeft.y)

    // Top right arc
    arcTo(
        rect = Rect(
            left = topLeft.x + width - topRightRadius,
            top = topLeft.y,
            right = topLeft.x + width,
            bottom = topLeft.y + topRightRadius
        ),
        startAngleDegrees = -90.0f,
        sweepAngleDegrees = 90.0f,
        forceMoveTo = false
    )

    lineTo(x = topLeft.x + width, y = topLeft.y + height - bottomRightRadius)

    // Bottom right arc
    arcTo(
        rect = Rect(
            left = topLeft.x + width - bottomRightRadius,
            top = topLeft.y + height - bottomRightRadius,
            right = topLeft.x + width,
            bottom = topLeft.y + height
        ),
        startAngleDegrees = 0f,
        sweepAngleDegrees = 90.0f,
        forceMoveTo = false
    )

    lineTo(x = topLeft.x + bottomLeftRadius, y = topLeft.y + height)

    // Bottom left arc
    arcTo(
        rect = Rect(
            left = topLeft.x,
            top = topLeft.y + height - bottomLeftRadius,
            right = topLeft.x + bottomLeftRadius,
            bottom = topLeft.y + height
        ),
        startAngleDegrees = 90.0f,
        sweepAngleDegrees = 90.0f,
        forceMoveTo = false
    )

    lineTo(x = topLeft.x, y = topLeft.y + topLeftRadius)
    close()
}
