package com.smarttoolfactory.composespeechbubble.tooltip

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.geometry.isUnspecified
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.composespeechbubble.BubbleLayout
import com.smarttoolfactory.composespeechbubble.ui.theme.Purple40
import com.smarttoolfactory.speechbubble.ArrowAlignment
import com.smarttoolfactory.speechbubble.ArrowShape
import com.smarttoolfactory.speechbubble.BubbleCornerRadius
import com.smarttoolfactory.speechbubble.BubbleShadow
import com.smarttoolfactory.speechbubble.BubbleState
import com.smarttoolfactory.speechbubble.getArrowRect
import com.smarttoolfactory.speechbubble.getArrowTip


class TooltipState(
    show: Boolean,
    cornerRadius: BubbleCornerRadius,
    alignment: ArrowAlignment,
    arrowShape: ArrowShape,
    arrowOffsetX: Dp = 0.dp,
    arrowOffsetY: Dp = 0.dp,
    arrowWidth: Dp = 14.dp,
    arrowHeight: Dp = 14.dp,
    drawArrow: Boolean = true
) : BubbleState(
    cornerRadius,
    alignment,
    arrowShape,
    arrowOffsetX,
    arrowOffsetY,
    arrowWidth,
    arrowHeight,
    drawArrow
) {
    internal var show by mutableStateOf(show)

    fun show() {
        show = true
    }

    fun dismiss() {
        show = false
    }

}

@Composable
fun ToolTipLayout(
    modifier: Modifier = Modifier,
    tooltipState: TooltipState,
    content: @Composable () -> Unit = {},
    tooltip: @Composable () -> Unit = {}
) {

    val density = LocalDensity.current.density

    val tooltipContent = @Composable {
        BubbleLayout(
            bubbleState = tooltipState,
            shadow = BubbleShadow(
                elevation = 2.dp
            ),

            backgroundColor = Purple40
        ) {
            tooltip()
        }
    }

    Layout(
        modifier = modifier,
        contents = listOf(content, tooltipContent),
        measurePolicy = { measurables: List<List<Measurable>>, constraints: Constraints ->

            val contentPlaceable = measurables[0].first().measure(
                constraints = constraints.copy(
                    minWidth = 0,
                    minHeight = 0
                )
            )

            val toolTipPlaceable = measurables[1].first().measure(
                constraints = constraints.copy(
                    minWidth = 0,
                    minHeight = 0
                )
            )

            val maxContentWidth = contentPlaceable.width
            val maxContentHeight = contentPlaceable.height

            val toolTipWidth = toolTipPlaceable.width
            val toolTipHeight = toolTipPlaceable.height

            val hasFixedWidth = constraints.hasFixedWidth
            val hasFixedHeight = constraints.hasFixedHeight

            val width = if (hasFixedWidth) constraints.maxWidth else maxContentWidth
            val height = if (hasFixedHeight) constraints.maxHeight else maxContentHeight

            val arrowWidth: Float =
                (tooltipState.arrowWidth.value * density).coerceAtMost(toolTipWidth.toFloat())
            val arrowHeight: Float =
                (tooltipState.arrowHeight.value * density).coerceAtMost(toolTipHeight.toFloat())

            val alignment = tooltipState.alignment
            val arrowShape = tooltipState.arrowShape

            tooltipState.arrowRect = getArrowRect(
                tooltipState, arrowWidth, arrowHeight, density,
                toolTipWidth.toFloat(), toolTipHeight.toFloat()
            )

            val arrowRect = tooltipState.arrowRect
            val arrowLeft = arrowRect.left
            val arrowRight = arrowRect.right
            val arrowTop = arrowRect.top
            val arrowBottom = arrowRect.bottom

            tooltipState.arrowTip = getArrowTip(
                arrowAlignment = alignment,
                arrowShape = arrowShape,
                arrowLeft = arrowLeft,
                arrowRight = arrowRight,
                arrowTop = arrowTop,
                arrowBottom = arrowBottom,
                arrowWidth = arrowWidth,
                arrowHeight = arrowHeight
            )

            val arrowAlignment = tooltipState.alignment
            val toolTipOffset = tooltipState.arrowTip

            val toolTipPosition = getTooltipPosition(
                arrowAlignment = arrowAlignment,
                offset = toolTipOffset,
                contentWidth = width,
                contentHeight = height,
                toolTipWidth = toolTipWidth,
                toolTipHeight = toolTipHeight
            )

            println("🍏Layout toolTipPosition: $toolTipPosition")

            layout(width, height) {
                contentPlaceable.placeRelative(0, 0)
                if (tooltipState.show && toolTipOffset.isSpecified) {
                    toolTipPlaceable.placeRelative(
                        toolTipPosition.x.toInt(),
                        toolTipPosition.y.toInt()
                    )
                }
            }
        }
    )
}

private fun getTooltipPosition(
    arrowAlignment: ArrowAlignment,
    offset: Offset,
    contentWidth: Int,
    contentHeight: Int,
    toolTipWidth: Int,
    toolTipHeight: Int
): Offset {

    if (offset.isUnspecified) return Offset.Unspecified

    return when (arrowAlignment) {
        ArrowAlignment.LeftTop,
        ArrowAlignment.LeftCenter,
        ArrowAlignment.LeftBottom -> {
            Offset(
                contentWidth.toFloat(),
                contentHeight / 2 - offset.y
            )
        }

        ArrowAlignment.RightTop,
        ArrowAlignment.RightCenter,
        ArrowAlignment.RightBottom -> {
            Offset(
                (-toolTipWidth).toFloat(),
                contentHeight / 2 - offset.y
            )
        }

        ArrowAlignment.TopLeft,
        ArrowAlignment.TopCenter,
        ArrowAlignment.TopRight -> {
            Offset(
                contentWidth / 2 - offset.x,
                contentWidth.toFloat()
            )
        }

        ArrowAlignment.BottomLeft,
        ArrowAlignment.BottomCenter,
        ArrowAlignment.BottomRight -> {
            Offset(
                contentWidth / 2 - offset.x,
                (-toolTipHeight).toFloat()
            )
        }

        else -> Offset(
            contentWidth.toFloat(),
            contentHeight / 2 - offset.y
        )
    }

}
