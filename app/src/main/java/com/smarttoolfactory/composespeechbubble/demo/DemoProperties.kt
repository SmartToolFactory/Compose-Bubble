package com.smarttoolfactory.composespeechbubble.demo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.composespeechbubble.BubbleLayout
import com.smarttoolfactory.composespeechbubble.ui.theme.BackgroundColor
import com.smarttoolfactory.composespeechbubble.ui.theme.SentMessageColor
import com.smarttoolfactory.bubble.ArrowAlignment
import com.smarttoolfactory.bubble.ArrowShape
import com.smarttoolfactory.bubble.BubbleCornerRadius
import com.smarttoolfactory.bubble.BubbleShadow
import com.smarttoolfactory.bubble.rememberBubbleState
import kotlin.math.roundToInt

@Preview
@Composable
fun DemoPropertiesBubble() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(8.dp)
    ) {

        val alignmentMap = remember {
            linkedMapOf<Int, ArrowAlignment>().apply {
                ArrowAlignment.entries.forEach { alignment ->
                    this[alignment.ordinal] = alignment
                }
            }
        }

        val arrowShapeMap = remember {
            linkedMapOf<Int, ArrowShape>().apply {
                ArrowShape.entries.forEach { shape ->
                    this[shape.ordinal] = shape
                }
            }
        }

        var arrowAlignment by remember {
            mutableStateOf(ArrowAlignment.TopCenter)
        }

        var arrowShape by remember {
            mutableStateOf(ArrowShape.FullTriangle)
        }

        var arrowOffsetX by remember {
            mutableFloatStateOf(0f)
        }

        var arrowOffsetY by remember {
            mutableFloatStateOf(0f)
        }

        var arrowWidth by remember {
            mutableFloatStateOf(30f)
        }

        var arrowHeight by remember {
            mutableFloatStateOf(30f)
        }

        var drawArrow by remember {
            mutableStateOf(true)
        }

        var elevation by remember {
            mutableFloatStateOf(2f)
        }

        var borderStrokeWidth by remember {
            mutableFloatStateOf(0f)
        }

        var cornerRadiusTopLeft by remember {
            mutableFloatStateOf(8f)
        }

        var cornerRadiusTopRight by remember {
            mutableFloatStateOf(8f)
        }

        var cornerRadiusBottomLeft by remember {
            mutableFloatStateOf(8f)
        }
        var cornerRadiusBottomRight by remember {
            mutableFloatStateOf(8f)
        }

        val bubbleState = rememberBubbleState(
            cornerRadius = BubbleCornerRadius(
                topLeft = cornerRadiusTopLeft.dp,
                topRight = cornerRadiusTopRight.dp,
                bottomLeft = cornerRadiusBottomLeft.dp,
                bottomRight = cornerRadiusBottomRight.dp
            ),
            alignment = arrowAlignment,
            arrowShape = arrowShape,
            arrowWidth = arrowWidth.dp,
            arrowHeight = arrowHeight.dp,
            arrowOffsetX = arrowOffsetX.dp,
            arrowOffsetY = arrowOffsetY.dp,
            drawArrow = drawArrow
        )

        Spacer(modifier = Modifier.height(20.dp))

        BubbleLayout(
            modifier = Modifier.drawWithContent {
                drawContent()
                if (bubbleState.arrowTip.isSpecified) {
                    drawCircle(
                        color = Color.Cyan,
                        radius = 20f,
                        center = bubbleState.arrowTip
                    )
                }
            },
            bubbleState = bubbleState,
            shadow = BubbleShadow(
                elevation = elevation.dp
            ),
            borderStroke = if (borderStrokeWidth > 0f)
                BorderStroke(borderStrokeWidth.dp, Color.Red) else null,
            backgroundColor = SentMessageColor
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Multiple line text to test\nproperties",
                fontSize = 20f.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            var alignmentFloat by remember {
                mutableFloatStateOf(1f)
            }

            var shapeFloat by remember {
                mutableFloatStateOf(0f)
            }

            ArrowAlignment.entries.forEach { alignment ->
                alignment.ordinal
            }

            Text(text = "arrowAlignment: $arrowAlignment")

            Slider(
                value = alignmentFloat,
                onValueChange = {
                    alignmentFloat = it
                    arrowAlignment =
                        alignmentMap[alignmentFloat.roundToInt()] ?: ArrowAlignment.TopLeft

                },
                steps = 11,
                valueRange = 0f..12f
            )

            Text(text = "arrowShape: $arrowShape")

            Slider(
                value = shapeFloat,
                onValueChange = {
                    shapeFloat = it
                    arrowShape = arrowShapeMap[shapeFloat.roundToInt()] ?: ArrowShape.HalfTriangle

                },
                steps = 1,
                valueRange = 0f..2f
            )

            TextAndSlider(
                text = "arrowOffsetX: ${arrowOffsetX.toInt()}",
                value = arrowOffsetX,
                onValueChange = {
                    arrowOffsetX = it
                },
                valueRange = -150f..150f
            )

            TextAndSlider(
                text = "arrowOffsetY: ${arrowOffsetY.toInt()}",
                value = arrowOffsetY,
                onValueChange = {
                    arrowOffsetY = it
                },
                valueRange = -150f..150f
            )

            TextAndSlider(
                text = "arrowWidth: ${arrowWidth.toInt()}",
                value = arrowWidth,
                onValueChange = {
                    arrowWidth = it
                },
                valueRange = 0f..100f
            )

            TextAndSlider(
                text = "arrowHeight: ${arrowHeight.toInt()}",
                value = arrowHeight,
                onValueChange = {
                    arrowHeight = it
                },
                valueRange = 0f..100f
            )

            TextAndSlider(
                text = "elevation: ${elevation.toInt()}",
                value = elevation,
                onValueChange = {
                    elevation = it
                },
                valueRange = 0f..20f
            )

            TextAndSlider(
                text = "borderStrokeWidth: ${borderStrokeWidth.toInt()}",
                value = borderStrokeWidth,
                onValueChange = {
                    borderStrokeWidth = it
                },
                valueRange = 0f..20f
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Draw arrow: $drawArrow")
                Checkbox(checked = drawArrow,
                    onCheckedChange = {
                        drawArrow = it
                    }
                )
            }

            TextAndSlider(
                text = "cornerRadiusTopLeft: ${cornerRadiusTopLeft.toInt()}",
                value = cornerRadiusTopLeft,
                onValueChange = {
                    cornerRadiusTopLeft = it
                },
                valueRange = 0f..20f
            )

            TextAndSlider(
                text = "cornerRadiusTopRight: ${cornerRadiusTopRight.toInt()}",
                value = cornerRadiusTopRight,
                onValueChange = {
                    cornerRadiusTopRight = it
                },
                valueRange = 0f..20f
            )
            TextAndSlider(
                text = "cornerRadiusBottomLeft: ${cornerRadiusBottomLeft.toInt()}",
                value = cornerRadiusBottomLeft,
                onValueChange = {
                    cornerRadiusBottomLeft = it
                },
                valueRange = 0f..20f
            )
            TextAndSlider(
                text = "cornerRadiusBottomRight: ${cornerRadiusBottomRight.toInt()}",
                value = cornerRadiusBottomRight,
                onValueChange = {
                    cornerRadiusBottomRight = it
                },
                valueRange = 0f..20f
            )
        }
    }
}

@Composable
internal fun TextAndSlider(
    text: String,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    onValueChange: (Float) -> Unit,
) {
    Column {
        Text(text = text)
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange
        )
    }
}
