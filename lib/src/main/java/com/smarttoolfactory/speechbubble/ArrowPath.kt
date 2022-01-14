package com.smarttoolfactory.speechbubble


import androidx.compose.ui.graphics.Path

/**
 * Create path for arrow either aligned left or right side of the bubble
 */
fun createHorizontalArrowPath(
    path: Path,
    contentRect: BubbleRect,
    state: BubbleState
) {
    val alignment = state.arrowAlignment
    if (alignment == NONE) return

    val contentHeight = contentRect.height
    val contentLeft = contentRect.left
    val contentRight = contentRect.right
    val contentTop = contentRect.top

    val arrowWidth = state.arrowWidth

    val cornerRadius = state.cornerRadiusBundle

    val radiusSumOnArrowSide = when {
        isHorizontalLeftAligned(alignment) -> {
            cornerRadius.topLeft + cornerRadius.bottomLeft
        }
        else -> {
            cornerRadius.topRight + cornerRadius.bottomRight
        }
    }

    // Height of the arrow is limited to height of the bubble
//    val arrowHeight =
//        if (modifier.arrowHeight + radiusSumOnArrowSide > contentHeight)
//            contentHeight - radiusSumOnArrowSide else modifier.arrowHeight

    val arrowHeight = state.arrowHeight.coerceAtMost(contentHeight)
    state.arrowHeight = arrowHeight

    // This is offset from top/bottom or center for arrows on left or right.
    // Maximum offset + arrow height cannot be bigger
    // than bottom of bubble or smaller than top of bubble.
    val arrowTop = calculateArrowTopPosition(state, arrowHeight, contentTop, contentHeight)
    state.arrowTop = arrowTop

    val arrowBottom = arrowTop + arrowHeight
    state.arrowBottom = arrowBottom

    val arrowShape = state.arrowShape

    when (alignment) {

        LEFT_TOP -> {
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

        LEFT_BOTTOM -> {

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

        LEFT_CENTER -> {

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

        RIGHT_TOP -> {

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

        RIGHT_BOTTOM -> {

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

        RIGHT_CENTER -> {

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
    contentHeight: Float
): Float {

    val alignment = state.arrowAlignment

    var arrowTop = when {
        isHorizontalTopAligned(alignment) -> {
            contentTop + state.arrowOffsetY
        }
        isHorizontalBottomAligned(alignment) -> {
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
fun createVerticalArrowPath(path: Path, contentRect: BubbleRect, state: BubbleState) {

    val alignment = state.arrowAlignment

    if (alignment == NONE) return

    val contentHeight = contentRect.height
    val contentWidth = contentRect.width

    val contentLeft = contentRect.left
    val contentRight = contentRect.right
    val contentTop = contentRect.top
    val contentBottom = contentRect.bottom

    val cornerRadius = state.cornerRadiusBundle

    // TODO This is for bottom arrow, we take only bottom corners to have space to draw arrow
    val radiusSumOnArrowSide = cornerRadius.bottomLeft + cornerRadius.bottomRight

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
        BOTTOM_LEFT -> {
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

        BOTTOM_RIGHT -> {
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

        BOTTOM_CENTER -> {
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

    val alignment = state.arrowAlignment

    var arrowLeft = when {
        isVerticalLeftAligned(alignment) -> {
            contentLeft + state.arrowOffsetX
        }
        isVerticalRightAligned(alignment) -> {
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

/**
 * Arrow is on left side of the bubble
 */
internal fun isHorizontalLeftAligned(alignment: Int): Boolean {
    return (alignment == LEFT_TOP
            || alignment == LEFT_BOTTOM
            || alignment == LEFT_CENTER)
}

/**
 * Arrow is on right side of the bubble
 */
internal fun isHorizontalRightAligned(alignment: Int): Boolean {
    return (alignment == RIGHT_TOP
            || alignment == RIGHT_BOTTOM
            || alignment == RIGHT_CENTER)
}

/**
 * Arrow is on top left or right side of the bubble
 */
private fun isHorizontalTopAligned(alignment: Int): Boolean {
    return (alignment == LEFT_TOP || alignment == RIGHT_TOP)
}

/**
 * Arrow is on top left or right side of the bubble
 */
private fun isHorizontalBottomAligned(alignment: Int): Boolean {
    return (alignment == LEFT_BOTTOM || alignment == RIGHT_BOTTOM)
}


/**
 * Check if arrow is horizontally positioned either on left or right side
 */
internal fun isArrowHorizontalPosition(alignment: Int): Boolean {
    return isHorizontalLeftAligned(alignment)
            || isHorizontalRightAligned(alignment)
}

internal fun isVerticalBottomAligned(alignment: Int): Boolean {
    return alignment == BOTTOM_LEFT || alignment == BOTTOM_RIGHT || alignment == BOTTOM_CENTER
}

/**
 * Arrow is on left side of the bubble
 */
internal fun isVerticalLeftAligned(alignment: Int): Boolean {
    return (alignment == BOTTOM_LEFT)
}

/**
 * Arrow is on right side of the bubble
 */
internal fun isVerticalRightAligned(alignment: Int): Boolean {
    return (alignment == BOTTOM_RIGHT)
}

/**
 * Check if arrow is vertically positioned either on top or at the bottom of bubble
 */
internal fun isArrowVerticalPosition(alignment: Int): Boolean {
    return isVerticalBottomAligned(alignment)
}
