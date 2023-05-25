package com.smarttoolfactory.speechbubble

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.LayoutDirection


fun Modifier.bubble(bubbleState: BubbleState) = composed(
    // pass inspector information for debug
    inspectorInfo = debugInspectorInfo {
        // name should match the name of the modifier
        name = "drawBubble"
        // add name and value of each argument
        properties["bubbleState"] = bubbleState
    },

    factory = {

        val density = LocalDensity.current
        val shape = remember {
            createHorizontalBubbleShape(bubbleState, density.density)
        }

        Modifier
            .background(
                color = bubbleState.backgroundColor,
                shape = shape
            )
            .layout { measurable, constraints ->
                measureBubbleResult(
                    bubbleState, measurable, constraints
                )
            }
    }
)

fun createHorizontalBubbleShape(
    state: BubbleState,
    density: Float
): GenericShape {

    val rect = BubbleRect()

    return GenericShape { size: Size, layoutDirection: LayoutDirection ->

        val alignment: ArrowAlignment = state.alignment

        val contentWidth: Float = size.width
        val contentHeight: Float = size.height
        val contentTop: Float = 0f

        val arrowWidth: Float = state.arrowWidth.value * density
        val arrowHeight: Float = (state.arrowHeight.value * density).coerceAtMost(contentHeight)

        // This is offset from top/bottom or center for arrows on left or right.
        // Maximum offset + arrow height cannot be bigger
        // than bottom of bubble or smaller than top of bubble.
        val arrowTop: Float = calculateArrowTopPosition(
            state,
            arrowHeight,
            contentTop,
            contentHeight,
            density
        )

        // Updated top value after comparing with Bubble height
        state.arrowTop = arrowTop
        val arrowBottom = arrowTop + arrowHeight
        state.arrowBottom = arrowBottom

        val arrowShape: ArrowShape = state.arrowShape


        if (state.drawArrow) {
            addHorizontalArrowToPath(
                alignment =    alignment,
                arrowShape =    arrowShape,
                contentWidth = contentWidth,
                arrowTop=     arrowTop,
                arrowBottom=   arrowBottom,
                arrowWidth =arrowWidth,
                arrowHeight=   arrowHeight

            )
        }

        setContentRect(
            bubbleState = state,
            rectContent = rect,
            width = size.width.toInt(),
            height = size.height.toInt(),
            density = density
        )

        addRoundedBubbleRect(state, rect, density)
    }
}
