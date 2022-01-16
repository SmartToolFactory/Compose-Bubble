package com.smarttoolfactory.speechbubble

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 *
 * [BubbleState] class  contains information about chat or speech **Bubble**.
 *
 * @param backgroundColor color of Bubble
 * @param cornerRadius Constructs a Radius for each side of bubble rectangle
 * @param alignment Arrow alignment determines in which side of the bubble this arrow should be drawn.
 * When [ArrowAlignment.NONE] is selected no arrow is drawn.
 * @param arrowShape Shape of the arrow, It can be right or isosceles triangle or curved shape
 * @param arrowOffsetX Vertical offset for arrow that is positioned on top or at the bottom of the bubble.
 * Positive values move arrow right while negative values move left. Arrow position
 * is limited between left of content and  content right minus arrow width.
 * @param arrowOffsetY Vertical offset for arrow that is positioned on left or right side of the bubble.
 * Positive values move arrow bottom while negative values move up. Arrow position
 * is limited between top of content and  content bottom minus arrow height.
 * @param arrowWidth width of the arrow
 * @param arrowHeight height of the arrow
 * @param arrowRadius radius of the arrow curves the tip of the arrow
 * @param drawArrow whether we should draw arrow or only have rectangle shape bubble
 * @param shadow of the arrow contains elevation, dx, dy, radius and color to draw shadow below bubble
 */
@Composable
fun rememberBubbleState(
    backgroundColor: Color = DefaultBubbleColor,
    cornerRadius: Dp = 5.dp,
    alignment: ArrowAlignment = ArrowAlignment.NONE,
    arrowShape: ArrowShape = ArrowShape.TRIANGLE_RIGHT,
    arrowOffsetX: Float = 0f,
    arrowOffsetY: Float = 0f,
    arrowWidth: Float = 14.0f,
    arrowHeight: Float = 14.0f,
    arrowRadius: Float = 0.0f,
    drawArrow: Boolean = true,
    shadow: BubbleShadow? = null,
): BubbleState {

    val density = LocalDensity.current.density

    return remember {
        BubbleState(
            backgroundColor = backgroundColor,
            cornerRadius = BubbleCornerRadius(
                topLeft = cornerRadius,
                topRight = cornerRadius,
                bottomLeft = cornerRadius,
                bottomRight = cornerRadius,
            ),
            alignment = alignment,
            arrowShape = arrowShape,
            arrowOffsetX = arrowOffsetX,
            arrowOffsetY = arrowOffsetY,
            arrowWidth = arrowWidth,
            arrowHeight = arrowHeight,
            arrowRadius = arrowRadius,
            drawArrow = drawArrow,
            shadow = shadow
        )
    }
}

/**
 *
 * [BubbleState] class  contains information about chat or speech **Bubble**.
 *
 * @param backgroundColor color of Bubble
 * @param cornerRadius Constructs a Radius for each side of bubble rectangle
 * @param alignment Arrow alignment determines in which side of the bubble this arrow should be drawn.
 * When [ArrowAlignment.NONE] is selected no arrow is drawn.
 * @param arrowShape Shape of the arrow, It can be right or isosceles triangle or curved shape
 * @param arrowOffsetX Vertical offset for arrow that is positioned on top or at the bottom of the bubble.
 * Positive values move arrow right while negative values move left. Arrow position
 * is limited between left of content and  content right minus arrow width.
 * @param arrowOffsetY Vertical offset for arrow that is positioned on left or right side of the bubble.
 * Positive values move arrow bottom while negative values move up. Arrow position
 * is limited between top of content and  content bottom minus arrow height.
 * @param arrowWidth width of the arrow
 * @param arrowHeight height of the arrow
 * @param arrowRadius radius of the arrow curves the tip of the arrow
 * @param drawArrow whether we should draw arrow or only have rectangle shape bubble
 * @param shadow of the arrow contains elevation, dx, dy, radius and color to draw shadow below bubble
 */
@Composable
fun rememberBubbleState(
    backgroundColor: Color = DefaultBubbleColor,
    cornerRadius: BubbleCornerRadius = BubbleCornerRadius(
        topLeft = 5.dp,
        topRight = 5.dp,
        bottomLeft = 5.dp,
        bottomRight = 5.dp
    ),
    alignment: ArrowAlignment = ArrowAlignment.NONE,
    arrowShape: ArrowShape = ArrowShape.TRIANGLE_RIGHT,
    arrowOffsetX: Float = 0f,
    arrowOffsetY: Float = 0f,
    arrowWidth: Float = 14.0f,
    arrowHeight: Float = 14.0f,
    arrowRadius: Float = 0.0f,
    drawArrow: Boolean = true,
    shadow: BubbleShadow? = null,
): BubbleState {

    val density = LocalDensity.current.density

    return remember {
        BubbleState(
            backgroundColor = backgroundColor,
            cornerRadius = cornerRadius,
            alignment = alignment,
            arrowShape = arrowShape,
            arrowOffsetX = arrowOffsetX,
            arrowOffsetY = arrowOffsetY,
            arrowWidth = arrowWidth,
            arrowHeight = arrowHeight,
            arrowRadius = arrowRadius,
            drawArrow = drawArrow,
            shadow = shadow
        )
    }
}

/**
 *
 * [BubbleState] class  contains information about chat or speech **Bubble**.
 *
 * @param backgroundColor color of Bubble
 * @param cornerRadius Constructs a Radius for each side of bubble rectangle
 * @param alignment Arrow alignment determines in which side of the bubble this arrow should be drawn.
 * When [ArrowAlignment.NONE] is selected no arrow is drawn.
 * @param arrowShape Shape of the arrow, It can be right or isosceles triangle or curved shape
 * @param arrowOffsetX Vertical offset for arrow that is positioned on top or at the bottom of the bubble.
 * Positive values move arrow right while negative values move left. Arrow position
 * is limited between left of content and  content right minus arrow width.
 * @param arrowOffsetY Vertical offset for arrow that is positioned on left or right side of the bubble.
 * Positive values move arrow bottom while negative values move up. Arrow position
 * is limited between top of content and  content bottom minus arrow height.
 * @param arrowWidth width of the arrow
 * @param arrowHeight height of the arrow
 * @param arrowRadius radius of the arrow curves the tip of the arrow
 * @param drawArrow whether we should draw arrow or only have rectangle shape bubble
 * @param shadow of the arrow contains elevation, dx, dy, radius and color to draw shadow below bubble
 */
class BubbleState internal constructor(
    var backgroundColor: Color = DefaultBubbleColor,
    var cornerRadius: BubbleCornerRadius = BubbleCornerRadius(
        topLeft = 5.dp,
        topRight = 5.dp,
        bottomLeft = 5.dp,
        bottomRight = 5.dp,
    ),
    var alignment: ArrowAlignment = ArrowAlignment.NONE,
    var arrowShape: ArrowShape = ArrowShape.TRIANGLE_RIGHT,
    var arrowOffsetX: Float = 0f,
    var arrowOffsetY: Float = 0f,
    var arrowWidth: Float = 14.0f,
    var arrowHeight: Float = 14.0f,
    var arrowRadius: Float = 0.0f,
    var drawArrow: Boolean = true,
    var shadow: BubbleShadow? = null,
) {

    /**
     * Scale to set initial values as dp
     */
    internal var dp: Float = 1f

    /**
     * Top position of arrow
     */
    var arrowTop: Float = 0f

    /**
     * Bottom position of arrow
     */
    var arrowBottom: Float = 0f

    fun init() {

        cornerRadius.apply {
            topLeft *= dp
            topRight *= dp
            bottomLeft *= dp
            bottomRight *= dp
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

