package com.smarttoolfactory.speechbubble


import androidx.compose.ui.graphics.Path

/**
 * Create path for arrow either aligned left or right side of the bubble
 */
fun createHorizontalArrowPath(
    path: Path,
    contentRect: BubbleRect,
    state: BubbleState,
    density: Float,
) {
    val alignment = state.alignment
    if (alignment == ArrowAlignment.NONE) return

    val contentHeight = contentRect.height
    val contentLeft = contentRect.left
    val contentRight = contentRect.right
    val contentTop = contentRect.top

    val arrowWidth = state.arrowWidth
    val arrowHeight = state.arrowHeight.coerceAtMost(contentHeight)

    state.arrowHeight = arrowHeight

    // This is offset from top/bottom or center for arrows on left or right.
    // Maximum offset + arrow height cannot be bigger
    // than bottom of bubble or smaller than top of bubble.
    val arrowTop = calculateArrowTopPosition(
        state,
        arrowHeight,
        contentTop,
        contentHeight,
        density
    )
    state.arrowTop = arrowTop

    val arrowBottom = arrowTop + arrowHeight
    state.arrowBottom = arrowBottom
    val arrowShape = state.arrowShape

    when (alignment) {

        ArrowAlignment.LEFT_TOP -> {
            // move to top of arrow at the top of left corner
            path.moveTo(contentLeft, arrowTop)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    // Draw horizontal line to left
                    path.lineTo(0f, arrowTop)
                    path.lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(0f, arrowTop + arrowHeight / 2f)
                    path.lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        ArrowAlignment.LEFT_BOTTOM -> {

            // move to top of arrow at the bottom left corner
            path.moveTo(contentLeft, arrowTop)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    // Draw horizontal line to left
                    path.lineTo(0f, arrowBottom)
                    path.lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    // Draw horizontal line to left
                    path.lineTo(0f, arrowTop + arrowHeight / 2f)
                    path.lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        ArrowAlignment.LEFT_CENTER -> {

            // move to top of arrow at the top of left corner
            path.moveTo(contentLeft, arrowTop)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    // Draw horizontal line to left
                    path.lineTo(0f, arrowTop)
                    path.lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(0f, arrowTop + arrowHeight / 2f)
                    path.lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        ArrowAlignment.RIGHT_TOP -> {

            // move to top right corner of the content
            path.moveTo(contentRight, arrowTop)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    path.lineTo(contentRight + arrowWidth, arrowTop)
                    path.lineTo(contentRight, arrowBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(contentRight + arrowWidth, arrowTop + arrowHeight / 2f)
                    path.lineTo(contentRight, arrowBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        ArrowAlignment.RIGHT_BOTTOM -> {

            // move to bottom right corner of the content
            path.moveTo(contentRight, arrowTop)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    path.lineTo(contentRight + arrowWidth, arrowBottom)
                    path.lineTo(contentRight, arrowBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(contentRight + arrowWidth, arrowTop + arrowHeight / 2f)
                    path.lineTo(contentRight, arrowBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        ArrowAlignment.RIGHT_CENTER -> {

            // move to top right corner of the content
            path.moveTo(contentRight, arrowTop)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    path.lineTo(contentRight + arrowWidth, arrowTop)
                    path.lineTo(contentRight, arrowBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(contentRight + arrowWidth, arrowTop + arrowHeight / 2f)
                    path.lineTo(contentRight, arrowBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        else -> Unit
    }
    path.close()
}


/**
 * Calculate top position of the arrow on either left or right side
 */
private fun calculateArrowTopPosition(
    state: BubbleState,
    arrowHeight: Float,
    contentTop: Float,
    contentHeight: Float,
    density: Float,
): Float {
    var arrowTop = when {
        state.isHorizontalTopAligned() -> {
            contentTop + state.arrowOffsetY
        }
        state.isHorizontalBottomAligned() -> {
            contentHeight + state.arrowOffsetY - arrowHeight
        }
        else -> {
            (contentHeight - arrowHeight) / 2f + state.arrowOffsetY
        }
    }

    if (arrowTop < 0) arrowTop = 0f

    if (arrowTop + arrowHeight > contentHeight) arrowTop = contentHeight - arrowHeight

    return arrowTop
}

/**
 * Create path for arrow that is at the bottom of the bubble
 */
fun createVerticalArrowPath(
    path: Path,
    contentRect: BubbleRect,
    state: BubbleState,
    density: Float
) {

    val alignment = state.alignment

    if (alignment == ArrowAlignment.NONE) return

    val contentHeight = contentRect.height
    val contentWidth = contentRect.width

    val contentLeft = contentRect.left
    val contentRight = contentRect.right
    val contentTop = contentRect.top
    val contentBottom = contentRect.bottom

    val cornerRadius = state.cornerRadius

    // TODO This is for bottom arrow, we take only bottom corners to have space to draw arrow
    val radiusSumOnArrowSide: Float =
        (cornerRadius.bottomLeft + cornerRadius.bottomRight).value * density

    // Width of the arrow is limited to height of the bubble minus sum of corner radius
    // of top and bottom in respective side

    val arrowWidth =
        if (state.arrowWidth + radiusSumOnArrowSide > contentWidth)
            contentWidth - radiusSumOnArrowSide else state.arrowWidth

    val arrowHeight = state.arrowHeight

    val arrowLeft = calculateArrowLeftPosition(state, arrowWidth, contentLeft, contentWidth)
    val arrowRight = arrowLeft + arrowWidth
    val arrowBottom = contentBottom + arrowHeight

    val arrowShape = state.arrowShape

    when (alignment) {
        ArrowAlignment.BOTTOM_LEFT -> {
            path.moveTo(arrowLeft, contentBottom)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    path.lineTo(arrowLeft, arrowBottom)
                    path.lineTo(arrowRight, contentBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(arrowLeft + arrowWidth / 2f, arrowBottom)
                    path.lineTo(arrowRight, contentBottom)
                }

                ArrowShape.CURVED -> {

                }
            }

        }

        ArrowAlignment.BOTTOM_RIGHT -> {
            path.moveTo(arrowLeft, contentBottom)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    path.lineTo(arrowRight, arrowBottom)
                    path.lineTo(arrowRight, contentBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(arrowLeft + arrowWidth / 2f, arrowBottom)
                    path.lineTo(arrowRight, contentBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        ArrowAlignment.BOTTOM_CENTER -> {
            path.moveTo(arrowLeft, contentBottom)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    // Draw horizontal line to left
                    path.lineTo(arrowLeft, arrowBottom)
                    path.lineTo(arrowRight, contentBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(arrowLeft + arrowWidth / 2f, arrowBottom)
                    path.lineTo(arrowRight, contentBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        else -> Unit
    }
    path.close()
}

private fun calculateArrowLeftPosition(
    state: BubbleState,
    arrowWidth: Float,
    contentLeft: Float,
    contentWidth: Float
): Float {
    var arrowLeft = when {
        state.isVerticalLeftAligned() -> {
            contentLeft + state.arrowOffsetX
        }
        state.isVerticalRightAligned() -> {
            contentWidth + state.arrowOffsetX - arrowWidth
        }
        else -> {
            (contentWidth - arrowWidth) / 2f + state.arrowOffsetX
        }
    }

    if (arrowLeft < 0) arrowLeft = 0f

    if (arrowLeft + arrowWidth > contentWidth) arrowLeft = contentWidth - arrowWidth

    return arrowLeft
}
