package com.smarttoolfactory.bubble

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 *
 * [BubbleState] class  contains information about chat or speech **Bubble**.
 *
 * @param cornerRadius Constructs a Radius for each side of bubble rectangle
 * @param alignment Arrow alignment determines in which side of the bubble this arrow should be drawn.
 * When [ArrowAlignment.None] is selected no arrow is drawn.
 * @param arrowShape Shape of the arrow, It can be right or isosceles triangle or curved shape
 * @param arrowOffsetX Vertical offset for arrow that is positioned on top or at the bottom of the bubble.
 * Positive values move arrow right while negative values move left. Arrow position
 * is limited between left of content and  content right minus arrow width.
 * @param arrowOffsetY Vertical offset for arrow that is positioned on left or right side of the bubble.
 * Positive values move arrow bottom while negative values move up. Arrow position
 * is limited between top of content and  content bottom minus arrow height.
 * @param arrowWidth width of the arrow
 * @param arrowHeight height of the arrow
 * @param drawArrow whether we should draw arrow or only have rectangle shape bubble
 */
@Composable
fun rememberBubbleState(
    cornerRadius: Dp = 8.dp,
    alignment: ArrowAlignment = ArrowAlignment.None,
    arrowShape: ArrowShape = ArrowShape.HalfTriangle,
    arrowOffsetX: Dp = 0.dp,
    arrowOffsetY: Dp = 0.dp,
    arrowWidth: Dp = 14.dp,
    arrowHeight: Dp = 14.dp,
    drawArrow: Boolean = true
): BubbleState {

    return remember(
        keys = arrayOf(
            cornerRadius,
            alignment,
            arrowShape,
            arrowOffsetX,
            arrowOffsetY,
            arrowWidth,
            arrowHeight,
            drawArrow
        )
    ) {
        BubbleState(
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
            drawArrow = drawArrow
        )
    }
}

/**
 *
 * [BubbleState] class  contains information about chat or speech **Bubble**.
 *
 * @param cornerRadius Constructs a Radius for each side of bubble rectangle
 * @param alignment Arrow alignment determines in which side of the bubble this arrow should be drawn.
 * When [ArrowAlignment.None] is selected no arrow is drawn.
 * @param arrowShape Shape of the arrow, It can be right or isosceles triangle or curved shape
 * @param arrowOffsetX Vertical offset for arrow that is positioned on top or at the bottom of the bubble.
 * Positive values move arrow right while negative values move left. Arrow position
 * is limited between left of content and  content right minus arrow width.
 * @param arrowOffsetY Vertical offset for arrow that is positioned on left or right side of the bubble.
 * Positive values move arrow bottom while negative values move up. Arrow position
 * is limited between top of content and  content bottom minus arrow height.
 * @param arrowWidth width of the arrow
 * @param arrowHeight height of the arrow
 * @param drawArrow whether we should draw arrow or only have rectangle shape bubble
 */
@Composable
fun rememberBubbleState(
    cornerRadius: BubbleCornerRadius = BubbleCornerRadius(
        topLeft = 8.dp,
        topRight = 8.dp,
        bottomLeft = 8.dp,
        bottomRight = 8.dp
    ),
    alignment: ArrowAlignment = ArrowAlignment.None,
    arrowShape: ArrowShape = ArrowShape.HalfTriangle,
    arrowOffsetX: Dp = 0.dp,
    arrowOffsetY: Dp = 0.dp,
    arrowWidth: Dp = 14.dp,
    arrowHeight: Dp = 14.dp,
    drawArrow: Boolean = true
): BubbleState {

    return remember(
        keys = arrayOf(
            cornerRadius,
            alignment,
            arrowShape,
            arrowOffsetX,
            arrowOffsetY,
            arrowWidth,
            arrowHeight,
            drawArrow
        )
    ) {
        BubbleState(
            cornerRadius = cornerRadius,
            alignment = alignment,
            arrowShape = arrowShape,
            arrowOffsetX = arrowOffsetX,
            arrowOffsetY = arrowOffsetY,
            arrowWidth = arrowWidth,
            arrowHeight = arrowHeight,
            drawArrow = drawArrow
        )
    }
}

/**
 *
 * [BubbleState] class  contains information about chat or speech **Bubble**.
 *
 * @param cornerRadius Constructs a Radius for each side of bubble rectangle
 * @param alignment Arrow alignment determines in which side of the bubble this arrow should be drawn.
 * When [ArrowAlignment.None] is selected no arrow is drawn.
 * @param arrowShape Shape of the arrow, It can be right or isosceles triangle or curved shape
 * @param arrowOffsetX Vertical offset for arrow that is positioned on top or at the bottom of the bubble.
 * Positive values move arrow right while negative values move left. Arrow position
 * is limited between left of content and  content right minus arrow width.
 * @param arrowOffsetY Vertical offset for arrow that is positioned on left or right side of the bubble.
 * Positive values move arrow bottom while negative values move up. Arrow position
 * is limited between top of content and  content bottom minus arrow height.
 * @param arrowWidth width of the arrow
 * @param arrowHeight height of the arrow
 * @param drawArrow whether we should draw arrow or only have rectangle shape bubble
 */
@Stable
open class BubbleState(
    val cornerRadius: BubbleCornerRadius = BubbleCornerRadius(
        topLeft = 8.dp,
        topRight = 8.dp,
        bottomLeft = 8.dp,
        bottomRight = 8.dp,
    ),
    val alignment: ArrowAlignment = ArrowAlignment.None,
    val arrowShape: ArrowShape = ArrowShape.HalfTriangle,
    val arrowOffsetX: Dp = 0.dp,
    val arrowOffsetY: Dp = 0.dp,
    val arrowWidth: Dp = 14.dp,
    val arrowHeight: Dp = 14.dp,
    val drawArrow: Boolean = true
) {

    var arrowRect:BubbleRect = BubbleRect.Zero

    /**
     * Top position of arrow. This is read-only for implementation. It's calculated when arrow
     * positions are calculated or adjusted based on width/height of bubble,
     * offsetX/y, arrow width/height.
     */
    val arrowTop: Float
        get() = arrowRect.top

    /**
     * Bottom position of arrow.  This is read-only for implementation. It's calculated when arrow
     * positions are calculated or adjusted based on width/height of bubble,
     * offsetX/y, arrow width/height.
     */

    val arrowBottom: Float
        get() = arrowRect.bottom


    /**
     * Right position of arrow.  This is read-only for implementation. It's calculated when arrow
     * positions are calculated or adjusted based on width/height of bubble,
     * offsetX/y, arrow width/height.
     */
    val arrowLeft: Float
        get() = arrowRect.left

    /**
     * Right position of arrow.  This is read-only for implementation. It's calculated when arrow
     * positions are calculated or adjusted based on width/height of bubble,
     * offsetX/y, arrow width/height.
     */
    val arrowRight: Float
        get() = arrowRect.right


    var arrowTip by mutableStateOf(
        Offset.Unspecified
    )

    /**
     * Arrow is on left side of the bubble
     */
    fun isHorizontalLeftAligned(): Boolean =
        (alignment == ArrowAlignment.LeftTop
                || alignment == ArrowAlignment.LeftBottom
                || alignment == ArrowAlignment.LeftCenter)


    /**
     * Arrow is on right side of the bubble
     */
    fun isHorizontalRightAligned(): Boolean =
        (alignment == ArrowAlignment.RightTop
                || alignment == ArrowAlignment.RightBottom
                || alignment == ArrowAlignment.RightCenter)


    /**
     * Arrow is on top left or right side of the bubble
     */
    fun isHorizontalTopAligned(): Boolean =
        (alignment == ArrowAlignment.LeftTop || alignment == ArrowAlignment.RightTop)


    /**
     * Arrow is on top left or right side of the bubble
     */
    fun isHorizontalBottomAligned(): Boolean =
        (alignment == ArrowAlignment.LeftBottom || alignment == ArrowAlignment.RightBottom)

    /**
     * Check if arrow is horizontally positioned either on left or right side
     */
    fun isArrowHorizontallyPositioned(): Boolean =
        isHorizontalLeftAligned()
                || isHorizontalRightAligned()


    /**
     * Arrow is at the bottom of the bubble
     */
    fun isVerticalBottomAligned(): Boolean =
        alignment == ArrowAlignment.BottomLeft ||
                alignment == ArrowAlignment.BottomRight ||
                alignment == ArrowAlignment.BottomCenter

    /**
     * Arrow is at the yop of the bubble
     */
    fun isVerticalTopAligned(): Boolean =
        alignment == ArrowAlignment.TopLeft ||
                alignment == ArrowAlignment.TopRight ||
                alignment == ArrowAlignment.TopCenter

    /**
     * Arrow is on left side of the bubble
     */
    fun isVerticalLeftAligned(): Boolean =
        (alignment == ArrowAlignment.BottomLeft) || (alignment == ArrowAlignment.TopLeft)


    /**
     * Arrow is on right side of the bubble
     */
    fun isVerticalRightAligned(): Boolean =
        (alignment == ArrowAlignment.BottomRight) || (alignment == ArrowAlignment.TopRight)


    /**
     * Check if arrow is vertically positioned either on top or at the bottom of bubble
     */
    fun isArrowVerticallyPositioned(): Boolean = isVerticalBottomAligned() || isVerticalTopAligned()
}

