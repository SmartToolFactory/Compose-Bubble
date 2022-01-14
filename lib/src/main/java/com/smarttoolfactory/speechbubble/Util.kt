package com.smarttoolfactory.speechbubble


import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Path
import kotlin.math.min


/**
 * Function that returns bubble path.
 *
 * @param state sum of properties of this layout which includes arrow alignment, position,etc.
 * @param contentRect rectangle of content area
 *
 */
fun getBubbleClipPath(
    path: Path,
    state: BubbleState,
    contentRect: BubbleRect
) {

    path.reset()

    if (state.withArrow) {
        if (isArrowHorizontalPosition(state.arrowAlignment)) {
            createHorizontalArrowPath(
                path = path,
                contentRect = contentRect,
                state = state
            )
        } else if (isArrowVerticalPosition(state.arrowAlignment)) {
            createVerticalArrowPath(
                path = path,
                contentRect = contentRect,
                state = state
            )
        }
    }

    getRoundedRectPath(state, path, contentRect)
}

private fun getRoundedRectPath(
    state: BubbleState,
    path: Path,
    contentRect: BubbleRect
) {

    val alignment = state.arrowAlignment

    val cornerRadius = state.cornerRadiusBundle

    val maxRadius = contentRect.height / 2f

    cornerRadius.apply {
        topLeft = min(topLeft, maxRadius)
        topRight = min(topRight, maxRadius)
        bottomRight = min(bottomRight, maxRadius)
        bottomLeft = min(bottomLeft, maxRadius)
    }

    val isWithArrow = state.withArrow


    if (isWithArrow) {
        when (alignment) {
            // Arrow on left side of the bubble
            LEFT_TOP, LEFT_CENTER, LEFT_BOTTOM -> {
                cornerRadius.topLeft = min(
                    state.arrowTop,
                    cornerRadius.topLeft
                )

                cornerRadius.bottomLeft =
                    min(cornerRadius.bottomLeft, (contentRect.height - state.arrowBottom))
            }

            // Arrow on right side of the bubble
            RIGHT_TOP, RIGHT_CENTER, RIGHT_BOTTOM -> {
                cornerRadius.topRight = min(
                    state.arrowTop,
                    cornerRadius.topRight
                )

                cornerRadius.bottomRight =
                    min(cornerRadius.bottomRight, (contentRect.height - state.arrowBottom))
            }

            // Arrow at the bottom of bubble
            BOTTOM_LEFT -> {
                cornerRadius.bottomLeft =
                    if (state.arrowOffsetY < maxRadius) 0f
                    else cornerRadius.bottomLeft
            }
            BOTTOM_RIGHT -> {
                cornerRadius.bottomRight =
                    if (state.arrowOffsetY < maxRadius) 0f
                    else cornerRadius.bottomRight
            }
            else -> Unit
        }
    }

    val radii = floatArrayOf(
        cornerRadius.topLeft,
        cornerRadius.topLeft,
        cornerRadius.topRight,
        cornerRadius.topRight,
        cornerRadius.bottomRight,
        cornerRadius.bottomRight,
        cornerRadius.bottomLeft,
        cornerRadius.bottomLeft
    )

    path.addRoundRect(
        RoundRect(
            rect = Rect(contentRect.left, contentRect.top, contentRect.right, contentRect.bottom),
            topLeft = androidx.compose.ui.geometry.CornerRadius(
                cornerRadius.topLeft,
                cornerRadius.topLeft
            ),
            topRight = androidx.compose.ui.geometry.CornerRadius(
                cornerRadius.topRight,
                cornerRadius.topRight
            ),
            bottomRight = androidx.compose.ui.geometry.CornerRadius(
                cornerRadius.bottomRight,
                cornerRadius.bottomRight
            ),
            bottomLeft = androidx.compose.ui.geometry.CornerRadius(
                cornerRadius.bottomLeft,
                cornerRadius.bottomLeft
            )
        )
    )
}