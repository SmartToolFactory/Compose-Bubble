package com.smarttoolfactory.speechbubble

import androidx.compose.ui.graphics.Path

internal fun Path.addHorizontalArrowToPath(
    alignment: ArrowAlignment,
    arrowShape: ArrowShape,
    contentWidth: Float,
    arrowTop: Float,
    arrowBottom: Float,
    arrowWidth: Float,
    arrowHeight: Float
) {
    when (alignment) {

        ArrowAlignment.LeftTop -> {
            val contentLeft = arrowWidth

            // move to top of arrow at the top of left corner
            moveTo(contentLeft, arrowTop)

            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    // Draw horizontal line to left
                    lineTo(0f, arrowTop)
                    lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(0f, arrowTop + arrowHeight / 2f)
                    lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.LeftBottom -> {
            val contentLeft = arrowWidth

            // move to top of arrow at the bottom left corner
            moveTo(contentLeft, arrowTop)

            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    // Draw horizontal line to left
                    lineTo(0f, arrowBottom)
                    lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.FullTriangle -> {
                    // Draw horizontal line to left
                    lineTo(0f, arrowTop + arrowHeight / 2f)
                    lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.LeftCenter -> {
            val contentLeft = arrowWidth

            // move to top of arrow at the top of left corner
            moveTo(contentLeft, arrowTop)

            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    // Draw horizontal line to left
                    lineTo(0f, arrowTop)
                    lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(0f, arrowTop + arrowHeight / 2f)
                    lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.RightTop -> {
            val contentRight = contentWidth - arrowWidth

            // move to top right corner of the content
            moveTo(contentRight, arrowTop)

            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    lineTo(contentRight + arrowWidth, arrowTop)
                    lineTo(contentRight, arrowBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(contentRight + arrowWidth, arrowTop + arrowHeight / 2f)
                    lineTo(contentRight, arrowBottom)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.RightBottom -> {
            val contentRight = contentWidth - arrowWidth

            // move to bottom right corner of the content
            moveTo(contentRight, arrowTop)

            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    lineTo(contentRight + arrowWidth, arrowBottom)
                    lineTo(contentRight, arrowBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(contentRight + arrowWidth, arrowTop + arrowHeight / 2f)
                    lineTo(contentRight, arrowBottom)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.RightCenter -> {
            val contentRight = contentWidth - arrowWidth

            // move to top right corner of the content
            moveTo(contentRight, arrowTop)

            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    lineTo(contentRight + arrowWidth, arrowTop)
                    lineTo(contentRight, arrowBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(contentRight + arrowWidth, arrowTop + arrowHeight / 2f)
                    lineTo(contentRight, arrowBottom)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        else -> Unit
    }
}

/**
 * Calculate top position of the arrow on either left or right side
 */
internal fun calculateArrowTopPosition(
    state: BubbleState,
    arrowHeight: Float,
    contentHeight: Float,
    density: Float,
): Float {

    val arrowOffsetY = state.arrowOffsetY.value * density

    var arrowTop = when {
        state.isHorizontalTopAligned() -> {
            arrowOffsetY
        }

        state.isHorizontalBottomAligned() -> {
            contentHeight + arrowOffsetY - arrowHeight
        }

        else -> {
            (contentHeight - arrowHeight) / 2f + arrowOffsetY
        }
    }

    if (arrowTop < 0) arrowTop = 0f

    if (arrowTop + arrowHeight > contentHeight) arrowTop = contentHeight - arrowHeight

    return arrowTop
}

internal fun Path.addVerticalArrowToPath(
    alignment: ArrowAlignment,
    arrowShape: ArrowShape,
    contentHeight: Float,
    arrowLeft: Float,
    arrowRight: Float,
    arrowBottom: Float,
    arrowWidth: Float,
    arrowHeight: Float
) {

    when (alignment) {
        ArrowAlignment.BottomLeft -> {

            val contentBottom = contentHeight - arrowHeight

            moveTo(arrowRight, contentBottom)

            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    lineTo(arrowLeft, arrowBottom)
                    lineTo(arrowRight, contentBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowLeft + arrowWidth / 2f, arrowBottom)
                    lineTo(arrowRight, contentBottom)
                }

                ArrowShape.Curved -> {

                }
            }

        }

        ArrowAlignment.BottomRight -> {
            val contentBottom = contentHeight - arrowHeight

            moveTo(arrowLeft, contentBottom)

            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    lineTo(arrowRight, arrowBottom)
                    lineTo(arrowRight, contentBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowLeft + arrowWidth / 2f, arrowBottom)
                    lineTo(arrowRight, contentBottom)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.BottomCenter -> {
            val contentBottom = contentHeight - arrowHeight

            moveTo(arrowLeft, contentBottom)

            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    // Draw horizontal line to left
                    lineTo(arrowLeft, arrowBottom)
                    lineTo(arrowRight, contentBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowLeft + arrowWidth / 2f, arrowBottom)
                    lineTo(arrowRight, contentBottom)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.TopLeft -> {
            val contentTop = arrowWidth
            moveTo(arrowLeft, contentTop)

            when (arrowShape) {
                ArrowShape.HalfTriangle -> {
                    lineTo(0f, arrowRight)
                    lineTo(contentTop, arrowRight)
                }

                ArrowShape.FullTriangle -> {

                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.TopCenter -> {
            val contentTop = arrowWidth

            when (arrowShape) {

                ArrowShape.HalfTriangle -> {

                }

                ArrowShape.FullTriangle -> {

                }

                ArrowShape.Curved -> {

                }
            }

        }

        ArrowAlignment.TopRight -> {
            val contentTop = arrowWidth

            when (arrowShape) {

                ArrowShape.HalfTriangle -> {

                }

                ArrowShape.FullTriangle -> {

                }

                ArrowShape.Curved -> {

                }
            }

        }

        else -> Unit
    }
}

internal fun calculateArrowLeftPosition(
    state: BubbleState,
    arrowWidth: Float,
    contentWidth: Float,
    density: Float
): Float {

    val arrowOffsetX: Float = state.arrowOffsetX.value * density

    var arrowLeft = when {
        state.isVerticalLeftAligned() -> {
            arrowOffsetX
        }

        state.isVerticalRightAligned() -> {
            contentWidth + arrowOffsetX - arrowWidth
        }

        else -> {
            (contentWidth - arrowWidth) / 2f + arrowOffsetX
        }
    }

    if (arrowLeft < 0) arrowLeft = 0f

    if (arrowLeft + arrowWidth > contentWidth) arrowLeft = contentWidth - arrowWidth

    return arrowLeft
}
