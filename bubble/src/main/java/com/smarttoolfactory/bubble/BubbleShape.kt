package com.smarttoolfactory.bubble

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.unit.LayoutDirection

fun createBubbleShape(
    state: BubbleState,
    density: Float
): GenericShape {

    return GenericShape { size: Size, layoutDirection: LayoutDirection ->

        val isHorizontalArrow = state.isArrowHorizontallyPositioned()

        val arrowShape: ArrowShape = state.arrowShape
        val alignment: ArrowAlignment = state.alignment

        val contentWidth: Float = size.width
        val contentHeight: Float = size.height

        val arrowWidth: Float = (state.arrowWidth.value * density).coerceAtMost(contentWidth)
        val arrowHeight: Float = (state.arrowHeight.value * density).coerceAtMost(contentHeight)

        state.arrowRect = getArrowRect(
            state,
            arrowWidth,
            arrowHeight,
            density,
            contentWidth,
            contentHeight
        )

        val arrowRect = state.arrowRect
        val arrowLeft = arrowRect.left
        val arrowRight = arrowRect.right
        val arrowTop = arrowRect.top
        val arrowBottom = arrowRect.bottom

        state.arrowTip = getArrowTip(
            arrowAlignment = alignment,
            arrowShape = arrowShape,
            arrowLeft = arrowLeft,
            arrowRight = arrowRight,
            arrowTop = arrowTop,
            arrowBottom = arrowBottom,
            arrowWidth = arrowWidth,
            arrowHeight = arrowHeight
        )

        val path = Path().apply {
            if (state.drawArrow) {
                if (isHorizontalArrow) {
                    addHorizontalArrowToPath(
                        alignment = alignment,
                        arrowShape = arrowShape,
                        arrowLeft = arrowLeft,
                        arrowRight = arrowRight,
                        arrowTop = arrowTop,
                        arrowBottom = arrowBottom,
                        arrowHeight = arrowHeight
                    )
                } else {
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
        }

        val contentRect: BubbleRect = getContentRect(
            bubbleState = state,
            width = size.width.toInt(),
            height = size.height.toInt(),
            density = density
        )

        addRoundedBubbleRect(state, contentRect, density)
        this.op(this, path, PathOperation.Union)
    }
}

fun getArrowTip(
    arrowAlignment: ArrowAlignment,
    arrowShape: ArrowShape,
    arrowLeft: Float,
    arrowTop: Float,
    arrowRight: Float,
    arrowBottom: Float,
    arrowWidth: Float,
    arrowHeight: Float
): Offset {

    return when (arrowAlignment) {
        ArrowAlignment.LeftTop,
        ArrowAlignment.LeftCenter -> {
            if (arrowShape == ArrowShape.FullTriangle) {
                Offset(arrowLeft, arrowTop + arrowHeight / 2)
            } else {
                Offset(arrowLeft, arrowTop)
            }
        }

        ArrowAlignment.LeftBottom -> {
            if (arrowShape == ArrowShape.FullTriangle) {
                Offset(arrowLeft, arrowTop + arrowHeight / 2)
            } else {
                Offset(arrowLeft, arrowBottom)
            }
        }

        ArrowAlignment.RightTop,
        ArrowAlignment.RightCenter -> {
            if (arrowShape == ArrowShape.FullTriangle) {
                Offset(arrowRight, arrowTop + arrowHeight / 2)
            } else {
                Offset(arrowRight, arrowTop)
            }
        }

        ArrowAlignment.RightBottom -> {
            if (arrowShape == ArrowShape.FullTriangle) {
                Offset(arrowRight, arrowTop + arrowHeight / 2)
            } else {
                Offset(arrowRight, arrowBottom)
            }
        }

        ArrowAlignment.BottomLeft,
        ArrowAlignment.BottomCenter -> {
            if (arrowShape == ArrowShape.FullTriangle) {
                Offset(arrowLeft + arrowWidth / 2, arrowBottom)
            } else {
                Offset(arrowLeft, arrowBottom)
            }
        }

        ArrowAlignment.BottomRight -> {
            if (arrowShape == ArrowShape.FullTriangle) {
                Offset(arrowLeft + arrowWidth / 2, arrowBottom)
            } else {
                Offset(arrowRight, arrowBottom)
            }
        }

        ArrowAlignment.TopLeft,
        ArrowAlignment.TopCenter -> {
            if (arrowShape == ArrowShape.FullTriangle) {
                Offset(arrowLeft + arrowWidth / 2, arrowTop)
            } else {
                Offset(arrowLeft, arrowTop)
            }
        }

        ArrowAlignment.TopRight -> {
            if (arrowShape == ArrowShape.FullTriangle) {
                Offset(arrowLeft + arrowWidth / 2, arrowTop)
            } else {
                Offset(arrowRight, arrowTop)
            }
        }

        else -> Offset.Zero
    }

}
