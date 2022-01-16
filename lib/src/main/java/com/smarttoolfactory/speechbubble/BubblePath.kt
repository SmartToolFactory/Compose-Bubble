package com.smarttoolfactory.speechbubble


import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
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
    contentRect: BubbleRect,
    density: Float,
) {

    path.reset()

    if (state.drawArrow) {
        if (state.isArrowHorizontalPosition()) {
            createHorizontalArrowPath(
                path = path,
                contentRect = contentRect,
                state = state,
                density = density
            )
        } else if (state.isArrowVerticalPosition()) {
            createVerticalArrowPath(
                path = path,
                contentRect = contentRect,
                state = state,
                density = density
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

    val alignment = state.alignment

    val cornerRadius: BubbleCornerRadius = state.cornerRadius

    val maxRadius = contentRect.height / 2f

    val drawArrow = state.drawArrow

    var topLeftInPx = cornerRadius.topLeft.value
        .coerceAtMost(maxRadius)
    var topRightInPx = cornerRadius.topRight.value
        .coerceAtMost(maxRadius)
    var bottomLeftInPx = cornerRadius.bottomLeft.value
        .coerceAtMost(maxRadius)
    var bottomRightInPx = cornerRadius.bottomRight.value
        .coerceAtMost(maxRadius)

    val arrowOffsetY = state.arrowOffsetY
    val arrowTop = state.arrowTop
    val arrowBottom = state.arrowBottom

    if (drawArrow) {
        when (alignment) {
            // Arrow on left side of the bubble
            ArrowAlignment.LEFT_TOP, ArrowAlignment.LEFT_CENTER, ArrowAlignment.LEFT_BOTTOM -> {
                topLeftInPx = min(arrowTop, topLeftInPx)
                bottomLeftInPx = min(bottomLeftInPx, (contentRect.height - arrowBottom))
            }

            // Arrow on right side of the bubble
            ArrowAlignment.RIGHT_TOP, ArrowAlignment.RIGHT_CENTER, ArrowAlignment.RIGHT_BOTTOM -> {
                topRightInPx = min(arrowTop, topRightInPx)
                bottomRightInPx = min(bottomRightInPx, (contentRect.height - arrowBottom))
            }

            // Arrow at the bottom of bubble
            ArrowAlignment.BOTTOM_LEFT -> {
                bottomLeftInPx =
                    if (arrowOffsetY < maxRadius) 0f
                    else bottomLeftInPx
            }
            ArrowAlignment.BOTTOM_RIGHT -> {
                bottomRightInPx =
                    if (arrowOffsetY < maxRadius) 0f
                    else bottomRightInPx
            }
            else -> Unit
        }
    }

    path.addRoundRect(
        RoundRect(
            rect = Rect(contentRect.left, contentRect.top, contentRect.right, contentRect.bottom),
            topLeft = CornerRadius(
                topLeftInPx,
                topLeftInPx
            ),
            topRight = CornerRadius(
                topRightInPx,
                topRightInPx
            ),
            bottomRight = CornerRadius(
                bottomRightInPx,
                bottomRightInPx
            ),
            bottomLeft = CornerRadius(
                bottomLeftInPx,
                bottomLeftInPx
            )
        )
    )
}