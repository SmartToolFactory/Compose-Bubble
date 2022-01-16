package com.smarttoolfactory.speechbubble

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color


@Composable
fun rememberBubbleState(): BubbleState {
    return remember {
        BubbleState()
    }
}

class BubbleState internal constructor(){

    /**
     * Scale to set initial values as dp
     */
    internal var dp: Float = 1f


    /**
     * Background of Bubble
     */
    var backgroundColor: Color = DefaultBubbleColor

    /**
     * Corner radius of bubble layout for y axis
     */
    var cornerRadius = 8f
        set(value) {
            cornerRadiusBundle.topLeft = cornerRadius
            cornerRadiusBundle.topRight = cornerRadius
            cornerRadiusBundle.bottomRight = cornerRadius
            cornerRadiusBundle.bottomLeft = cornerRadius
            field = value
        }

    /**
     * Custom corner radius for each side of the rectangle, if this is not null parameters
     * of this data class is used to draw rounded rectangle.
     */
    var cornerRadiusBundle = BubbleCornerRadius(
        topLeft = cornerRadius,
        topRight = cornerRadius,
        bottomLeft = cornerRadius,
        bottomRight = cornerRadius
    )

    /**
     * Arrow alignment determines in which side of the bubble this arrow should be drawn.
     * When [ArrowAlignment.NONE] is selected no arrow is drawn
     */
    var alignment: ArrowAlignment = ArrowAlignment.NONE


    /**
     * Top position of arrow
     */
    var arrowTop: Float = 0f

    /**
     * Bottom position of arrow
     */
    var arrowBottom = 0f

    var arrowWidth: Float = 14.0f
    var arrowHeight: Float = 14.0f
    var arrowRadius: Float = 0.0f

    var arrowShape: ArrowShape = ArrowShape.TRIANGLE_RIGHT

    /**
     * Vertical offset for arrow that is positioned on left or right side of the bubble.
     *
     * Positive values move arrow bottom while negative values move up. Arrow position
     * is limited between top of content and  content bottom minus arrow height.
     */
    var arrowOffsetY: Float = 0f


    /**
     * Vertical offset for arrow that is positioned on top or at the bottom of the bubble.
     *
     * Positive values move arrow right while negative values move left. Arrow position
     * is limited between left of content and  content right minus arrow width.
     */
    var arrowOffsetX: Float = 0f


    /**
     * If set to true an arrow is drawn depending on it's alignment, horizontal and vertical
     * offset.
     */
    var drawArrow = true


    var shadow: BubbleShadow? = null


    fun init() {

        cornerRadius *= dp

        cornerRadiusBundle.apply {
            topLeft = cornerRadius
            topRight = cornerRadius
            bottomLeft = cornerRadius
            bottomRight = cornerRadius
        }

        arrowWidth *= dp
        arrowHeight *= dp
        arrowRadius *= dp
        arrowOffsetY *= dp
        arrowOffsetX *= dp
    }

    /**
     * Arrow is on left side of the bubble
     */
    fun isHorizontalLeftAligned(): Boolean =
        (alignment == ArrowAlignment.LEFT_TOP
                || alignment == ArrowAlignment.LEFT_BOTTOM
                || alignment == ArrowAlignment.LEFT_CENTER)


    /**
     * Arrow is on right side of the bubble
     */
    fun isHorizontalRightAligned(): Boolean =
        (alignment == ArrowAlignment.RIGHT_TOP
                || alignment == ArrowAlignment.RIGHT_BOTTOM
                || alignment == ArrowAlignment.RIGHT_CENTER)


    /**
     * Arrow is on top left or right side of the bubble
     */
    fun isHorizontalTopAligned(): Boolean =
        (alignment == ArrowAlignment.LEFT_TOP || alignment == ArrowAlignment.RIGHT_TOP)


    /**
     * Arrow is on top left or right side of the bubble
     */
    fun isHorizontalBottomAligned(): Boolean =
        (alignment == ArrowAlignment.LEFT_BOTTOM || alignment == ArrowAlignment.RIGHT_BOTTOM)

    /**
     * Check if arrow is horizontally positioned either on left or right side
     */
    fun isArrowHorizontalPosition(): Boolean =
        isHorizontalLeftAligned()
                || isHorizontalRightAligned()


    fun isVerticalBottomAligned(): Boolean =
        alignment == ArrowAlignment.BOTTOM_LEFT ||
                alignment == ArrowAlignment.BOTTOM_RIGHT ||
                alignment == ArrowAlignment.BOTTOM_CENTER


    /**
     * Arrow is on left side of the bubble
     */
    fun isVerticalLeftAligned(): Boolean = (alignment == ArrowAlignment.BOTTOM_LEFT)


    /**
     * Arrow is on right side of the bubble
     */
    fun isVerticalRightAligned(): Boolean = (alignment == ArrowAlignment.BOTTOM_RIGHT)


    /**
     * Check if arrow is vertically positioned either on top or at the bottom of bubble
     */
    fun isArrowVerticalPosition(): Boolean = isVerticalBottomAligned()


}

