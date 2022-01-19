package com.smarttoolfactory.speechbubble

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import kotlin.math.roundToInt


/**
 * Bubble layout that acts as a [Column]
 * @param modifier belong to whole layout. After this modifier [drawBubble] functions is called
 *  then [Modifier.then] called to chain [bubbleModifier].
 * @param bubbleModifier this [Modifier] is used after drawing bubble
 * @param bubbleState contains information about chat or speech **Bubble**.
 * @param content combination of composables. Children composables are laid out as [Column] does.
 */
@Composable
fun BubbleColumn(
    modifier: Modifier = Modifier,
    bubbleModifier: Modifier = Modifier,
    bubbleState: BubbleState,
    content: @Composable () -> Unit
) {
    val contentRect = remember { BubbleRect() }
    val rect = remember { BubbleRect() }
    val path = remember { Path() }

    val newModifier = modifier
        .materialShadow(bubbleState, path)
        .drawBehind {
//            println("📝️ BubbleColumn() DRAWING align:${bubbleState.alignment}, size: $size, path: $path, rectContent: $contentRect")

            drawPath(path = path, color = bubbleState.backgroundColor)
//
            drawRect(
                color = Color.Red,
                topLeft = Offset(rect.left, rect.top),
                size = Size(rect.width, rect.height),
                style = Stroke(2f)
            )

            drawRect(
                color = Color.Blue,
                topLeft = Offset(contentRect.left, contentRect.top),
                size = Size(contentRect.width, contentRect.height),
                style = Stroke(2f)
            )
        }
        .then(bubbleModifier)

    Layout(
        content = content,
        modifier = newModifier

    ) { measurables, constraints ->

//        println("BubbleColumn() LAYOUT align:${bubbleState.alignment}, rect: $rect")
        measureBubbleColumnResult(
            bubbleState = bubbleState,
            measurables = measurables,
            constraints = constraints,
            rectContent = contentRect,
            rect = rect,
            path = path
        )
    }
}

private fun MeasureScope.measureBubbleColumnResult(
    bubbleState: BubbleState,
    measurables: List<Measurable>,
    constraints: Constraints,
    rectContent: BubbleRect,
    rect: BubbleRect,
    path: Path
): MeasureResult {

    val arrowWidth = (bubbleState.arrowWidth.value * density).roundToInt()
    val arrowHeight = (bubbleState.arrowHeight.value * density).roundToInt()

    val paddingStart = ((bubbleState.padding?.start ?: 0.dp).value * density).roundToInt()
    val paddingTop = ((bubbleState.padding?.top ?: 0.dp).value * density).roundToInt()
    val paddingEnd = ((bubbleState.padding?.end ?: 0.dp).value * density).roundToInt()
    val paddingBottom = ((bubbleState.padding?.bottom ?: 0.dp).value * density).roundToInt()

    val isHorizontalRightAligned = bubbleState.isHorizontalRightAligned()
    val isHorizontalLeftAligned = bubbleState.isHorizontalLeftAligned()
    val isVerticalBottomAligned = bubbleState.isVerticalBottomAligned()

    // Offset to limit max width when arrow is horizontally placed
    // if we don't remove arrowWidth bubble will overflow from it's parent as much as arrow
    // width is. So we measure our placeable as content + arrow width + horizontal padding
    val offsetX: Int =
        (paddingStart + paddingEnd) + if (bubbleState.isArrowHorizontallyPositioned()) {
            arrowWidth
        } else 0

    // Offset to limit max height when arrow is vertically placed

    val offsetY: Int =
        (paddingTop + paddingBottom) + if (bubbleState.isArrowVerticallyPositioned()) {
            arrowHeight
        } else 0

    val placeables = measurables.map { measurable: Measurable ->
        measurable.measure(constraints.offset(-offsetX, -offsetY))
    }

    val desiredWidth: Int =
        (placeables.maxOf { it.width } + offsetX)
    val desiredHeight: Int =
        (placeables.sumOf { it.height } + offsetY)

    rect.set(0f, 0f, desiredWidth.toFloat(), desiredHeight.toFloat())

    println(
        "🚛 measureBubbleColumnResult() align:${bubbleState.alignment}, " +
                "placeableWidth: ${placeables.maxOf { it.width }}, " +
                "desiredWidth: $desiredWidth, limitedWidth: ${
                    constraints.constrainWidth(
                        placeables.maxOf { it.width })
                }\n" +
                "constraints: $constraints"
    )

    setContentRect(
        bubbleState,
        rectContent,
        desiredWidth,
        desiredHeight,
        density = density
    )

    getBubbleClipPath(
        path = path,
        state = bubbleState,
        contentRect = rectContent,
        density = density
    )

    var x = 0
    var y = 0

    when {
        // Arrow on left side
        isHorizontalLeftAligned -> {
            x = arrowWidth + paddingStart
            y = paddingTop
        }

        // Arrow on right side
        isHorizontalRightAligned -> {
            x = paddingStart
            y = paddingTop
        }

        // Arrow at the bottom
        isVerticalBottomAligned -> {
            x = paddingStart
            y = paddingTop
        }
    }

    return layout(desiredWidth, desiredHeight) {

        var yPos = 0
        placeables.forEach { placeable: Placeable ->
//            println(
//                "🎃 measureBubbleColumnResult() LAYOUT align: ${bubbleState.alignment}\n" +
//                        "x: $x, y: $y, yPos: $yPos, " +
//                        "placeable width: ${placeable.width}, " +
//                        "height: ${placeable.height}, " +
//                        "rect: $rectContent"
//            )

            placeable.place(x, y + yPos)
            yPos += placeable.height
        }
    }
}