package com.smarttoolfactory.speechbubble

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
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
        else contentWidth - arrowWidth
        val arrowRight = arrowLeft + arrowWidth

        state.arrowTop = arrowTop
        state.arrowBottom = arrowBottom
        state.arrowLeft = arrowLeft
        state.arrowRight = arrowRight

        val rect: BubbleRect = getContentRect(
            bubbleState = state,
            width = size.width.toInt(),
            height = size.height.toInt(),
            density = density
        )

        val path = Path().apply {
            if (state.drawArrow) {
                addHorizontalArrowToPath(
                    alignment = alignment,
                    arrowShape = arrowShape,
                    arrowLeft = arrowLeft,
                    arrowRight = arrowRight,
                    arrowTop = arrowTop,
                    arrowBottom = arrowBottom,
                    arrowHeight = arrowHeight
                )
            }
        }
        addRoundedBubbleRect(state, rect, density)
        this.op(this, path, PathOperation.Union)
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
        val arrowTop = if (state.isVerticalBottomAligned()) {
            contentHeight - arrowHeight
        } else {
            0f
        }
        val arrowBottom = arrowTop + arrowHeight

        state.arrowLeft = arrowLeft
        state.arrowRight = arrowRight
        state.arrowTop = arrowTop
        state.arrowBottom = arrowBottom

        val rect: BubbleRect = getContentRect(
            bubbleState = state,
            width = size.width.toInt(),
            height = size.height.toInt(),
            density = density
        )


       val path = Path().apply {
           if (state.drawArrow) {
               addVerticalArrowToPath(
                   alignment = alignment,
                   arrowShape = arrowShape,
                   arrowLeft = arrowLeft,
                   arrowRight = arrowRight,
                   arrowBottom = arrowBottom,
                   arrowTop = arrowTop,
                   arrowWidth = arrowWidth
               )
           }
       }

        addRoundedBubbleRect(state, rect, density)

        this.op(this, path, PathOperation.Union)
    }
}
