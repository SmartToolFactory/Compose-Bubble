package com.smarttoolfactory.bubble

import androidx.compose.ui.graphics.Path

internal fun Path.addHorizontalArrowToPath(
    alignment: ArrowAlignment,
    arrowShape: ArrowShape,
    arrowLeft: Float,
    arrowRight: Float,
    arrowTop: Float,
    arrowBottom: Float,
    arrowHeight: Float
) {
    when (alignment) {

        ArrowAlignment.LeftTop -> {

            moveTo(arrowRight, arrowTop)
            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    // Draw horizontal line to left
                    lineTo(arrowLeft, arrowTop)
                    lineTo(arrowRight, arrowBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowLeft, arrowBottom - arrowHeight / 2)
                    lineTo(arrowRight, arrowBottom)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.LeftCenter -> {
            moveTo(arrowRight, arrowBottom)
            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    // Draw horizontal line to left
                    lineTo(arrowLeft, arrowTop)
                    lineTo(arrowRight, arrowTop)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowLeft, arrowBottom - arrowHeight / 2)
                    lineTo(arrowRight, arrowTop)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.LeftBottom -> {
            moveTo(arrowRight, arrowBottom)
            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    // Draw horizontal line to left
                    lineTo(arrowLeft, arrowBottom)
                    lineTo(arrowRight, arrowTop)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowLeft, arrowBottom - arrowHeight / 2)
                    lineTo(arrowRight, arrowTop)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.RightTop -> {
            moveTo(arrowLeft, arrowTop)
            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    lineTo(arrowRight, arrowTop)
                    lineTo(arrowLeft, arrowBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowRight, arrowBottom - arrowHeight / 2)
                    lineTo(arrowLeft, arrowBottom)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.RightCenter -> {
            moveTo(arrowLeft, arrowTop)
            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    lineTo(arrowRight, arrowTop)
                    lineTo(arrowLeft, arrowBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowRight, arrowBottom - arrowHeight / 2)
                    lineTo(arrowLeft, arrowBottom)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.RightBottom -> {
            moveTo(arrowLeft, arrowTop)
            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    lineTo(arrowRight, arrowBottom)
                    lineTo(arrowLeft, arrowBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowRight, arrowBottom - arrowHeight / 2)
                    lineTo(arrowLeft, arrowBottom)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        else -> Unit
    }

    close()
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
    arrowLeft: Float,
    arrowRight: Float,
    arrowBottom: Float,
    arrowTop: Float,
    arrowWidth: Float
) {

    when (alignment) {
        ArrowAlignment.BottomLeft -> {
            moveTo(arrowRight, arrowTop)

            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    lineTo(arrowLeft, arrowBottom)
                    lineTo(arrowLeft, arrowTop)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowRight - arrowWidth / 2, arrowBottom)
                    lineTo(arrowLeft, arrowTop)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.BottomCenter -> {
            moveTo(arrowRight, arrowTop)

            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    lineTo(arrowLeft, arrowBottom)
                    lineTo(arrowLeft, arrowTop)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowRight - arrowWidth / 2, arrowBottom)
                    lineTo(arrowLeft, arrowTop)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.BottomRight -> {
            moveTo(arrowRight, arrowTop)

            when (arrowShape) {

                ArrowShape.HalfTriangle -> {
                    lineTo(arrowRight, arrowBottom)
                    lineTo(arrowLeft, arrowTop)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowRight - arrowWidth / 2, arrowBottom)
                    lineTo(arrowLeft, arrowTop)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.TopLeft -> {
            moveTo(arrowLeft, arrowBottom)

            when (arrowShape) {
                ArrowShape.HalfTriangle -> {
                    lineTo(arrowLeft, arrowTop)
                    lineTo(arrowRight, arrowBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowLeft + arrowWidth / 2, arrowTop)
                    lineTo(arrowRight, arrowBottom)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.TopCenter -> {
            moveTo(arrowLeft, arrowBottom)

            when (arrowShape) {
                ArrowShape.HalfTriangle -> {
                    lineTo(arrowLeft, arrowTop)
                    lineTo(arrowRight, arrowBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowLeft + arrowWidth / 2, arrowTop)
                    lineTo(arrowRight, arrowBottom)
                }

                ArrowShape.Curved -> {

                }
            }
        }

        ArrowAlignment.TopRight -> {
            moveTo(arrowRight, arrowBottom)

            when (arrowShape) {
                ArrowShape.HalfTriangle -> {
                    lineTo(arrowRight, arrowTop)
                    lineTo(arrowLeft, arrowBottom)
                }

                ArrowShape.FullTriangle -> {
                    lineTo(arrowLeft + arrowWidth / 2, arrowTop)
                    lineTo(arrowLeft, arrowBottom)
                }

                ArrowShape.Curved -> {

                }
            }

        }

        else -> Unit
    }

    close()
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
