package com.smarttoolfactory.speechbubble

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.LayoutDirection

fun createHorizontalBubbleShape(
    state: BubbleState,
    density: Float
): GenericShape {

    return GenericShape { size: Size, layoutDirection: LayoutDirection ->

        val arrowShape: ArrowShape = state.arrowShape
        val alignment: ArrowAlignment = state.alignment

        val contentWidth: Float = size.width
        val contentHeight: Float = size.height

        val arrowWidth: Float = state.arrowWidth.value * density
        val arrowHeight: Float = (state.arrowHeight.value * density).coerceAtMost(contentHeight)

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
        else contentWidth
        val arrowRight = arrowLeft + arrowWidth

        state.arrowTop = arrowTop
        state.arrowBottom = arrowBottom
        state.arrowLeft = arrowLeft
        state.arrowRight = arrowRight

        if (state.drawArrow) {
            addHorizontalArrowToPath(
                alignment = alignment,
                arrowShape = arrowShape,
                contentWidth = contentWidth,
                arrowTop = arrowTop,
                arrowBottom = arrowBottom,
                arrowWidth = arrowWidth,
                arrowHeight = arrowHeight
            )
        }

        val rect: BubbleRect = getContentRect(
            bubbleState = state,
            width = size.width.toInt(),
            height = size.height.toInt(),
            density = density
        )

        addRoundedBubbleRect(state, rect, density)
    }
}


fun createVerticalBubbleShape(
    state: BubbleState,
    density: Float
): GenericShape {

    return GenericShape { size: Size, layoutDirection: LayoutDirection ->

        val arrowShape = state.arrowShape
        val alignment: ArrowAlignment = state.alignment

        val contentWidth: Float = size.width
        val contentHeight: Float = size.height

        val arrowWidth = (state.arrowWidth.value * density).coerceAtMost(contentWidth)
        val arrowHeight = state.arrowHeight.value * density

        val arrowLeft = calculateArrowLeftPosition(
            state,
            arrowWidth,
            contentWidth,
            density
        )

        val arrowRight = arrowLeft + arrowWidth
        val arrowTop = contentHeight - arrowHeight
        val arrowBottom = contentHeight

        state.arrowLeft = arrowLeft
        state.arrowRight = arrowRight
        state.arrowBottom = arrowBottom
        state.arrowTop = arrowTop

        if (state.drawArrow) {
            addVerticalArrowToPath(
                alignment = alignment,
                arrowShape = arrowShape,
                arrowLeft = arrowLeft,
                arrowRight = arrowRight,
                arrowBottom = arrowBottom,
                arrowWidth = arrowWidth,
                contentBottom = contentHeight - arrowHeight,
            )
        }

        val rect: BubbleRect = getContentRect(
            bubbleState = state,
            width = size.width.toInt(),
            height = size.height.toInt(),
            density = density
        )

        addRoundedBubbleRect(state, rect, density)
    }
}
