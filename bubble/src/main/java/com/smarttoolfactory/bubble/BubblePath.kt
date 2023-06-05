package com.smarttoolfactory.bubble


import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Path
import kotlin.math.min

internal fun Path.addRoundedBubbleRect(
    state: BubbleState,
    contentRect: BubbleRect,
    density: Float
) {

    val alignment = state.alignment

    val cornerRadius: BubbleCornerRadius = state.cornerRadius

    val width = contentRect.width
    val height = contentRect.height
    val left = contentRect.left
    val right = contentRect.right
    val top = contentRect.top
    val bottom = contentRect.bottom

    val maxRadius = width.coerceAtMost(height) / 2f

    val drawArrow = state.drawArrow

    var topLeftCornerRadius = cornerRadius.topLeft.value * density
        .coerceAtMost(maxRadius)
    var topRightCornerRadius = cornerRadius.topRight.value * density
        .coerceAtMost(maxRadius)
    var bottomLeftCornerRadius = cornerRadius.bottomLeft.value * density
        .coerceAtMost(maxRadius)
    var bottomRightCornerRadius = cornerRadius.bottomRight.value * density
        .coerceAtMost(maxRadius)

    val arrowTop = state.arrowTop
    val arrowBottom = state.arrowBottom
    val arrowLeft = state.arrowLeft
    val arrowRight = state.arrowRight

    if (drawArrow) {
        when (alignment) {
            // Arrow on left side of the bubble
            ArrowAlignment.LeftTop, ArrowAlignment.LeftCenter, ArrowAlignment.LeftBottom -> {
                topLeftCornerRadius = min(arrowTop, topLeftCornerRadius)
                bottomLeftCornerRadius =
                    min(bottomLeftCornerRadius, (height - arrowBottom))
            }

            // Arrow on right side of the bubble
            ArrowAlignment.RightTop, ArrowAlignment.RightCenter, ArrowAlignment.RightBottom -> {
                topRightCornerRadius = min(arrowTop, topRightCornerRadius)
                bottomRightCornerRadius =
                    min(bottomRightCornerRadius, (height - arrowBottom))
            }

            // Arrow at the bottom of bubble
            ArrowAlignment.BottomLeft, ArrowAlignment.BottomCenter, ArrowAlignment.BottomRight -> {

                bottomLeftCornerRadius = min(arrowLeft, bottomLeftCornerRadius)
                bottomRightCornerRadius =
                    min(bottomRightCornerRadius, (width - arrowRight))
            }

            // Arrow at the top of bubble
            ArrowAlignment.TopLeft, ArrowAlignment.TopCenter, ArrowAlignment.TopRight -> {
                topLeftCornerRadius = min(arrowLeft, topLeftCornerRadius)
                topRightCornerRadius = min(topRightCornerRadius, (width - arrowRight))
            }

            else -> Unit
        }
    }

    addRoundRect(
        RoundRect(
            rect = Rect(left, top, right, bottom),
            topLeft = CornerRadius(
                topLeftCornerRadius,
                topLeftCornerRadius
            ),
            topRight = CornerRadius(
                topRightCornerRadius,
                topRightCornerRadius
            ),
            bottomRight = CornerRadius(
                bottomRightCornerRadius,
                bottomRightCornerRadius
            ),
            bottomLeft = CornerRadius(
                bottomLeftCornerRadius,
                bottomLeftCornerRadius
            )
        )
    )
}
