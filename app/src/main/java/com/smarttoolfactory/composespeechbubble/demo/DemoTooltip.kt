package com.smarttoolfactory.composespeechbubble.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.composespeechbubble.tooltip.ToolTipLayout
import com.smarttoolfactory.composespeechbubble.tooltip.TooltipState
import com.smarttoolfactory.bubble.ArrowAlignment
import com.smarttoolfactory.bubble.ArrowShape
import com.smarttoolfactory.bubble.BubbleCornerRadius
import kotlin.math.roundToInt

@Preview
@Composable
fun DemoToolTip() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val alignmentMap = remember {
            linkedMapOf<Int, ArrowAlignment>().apply {
                ArrowAlignment.values().forEach { alignment ->
                    this[alignment.ordinal] = alignment
                }
            }
        }

        val arrowShapeMap = remember {
            linkedMapOf<Int, ArrowShape>().apply {
                ArrowShape.values().forEach { shape ->
                    this[shape.ordinal] = shape
                }
            }
        }

        var arrowAlignment by remember {
            mutableStateOf(ArrowAlignment.LeftCenter)
        }

        var arrowShape by remember {
            mutableStateOf(ArrowShape.FullTriangle)
        }

        var arrowOffsetX by remember {
            mutableStateOf(0f)
        }

        var arrowOffsetY by remember {
            mutableStateOf(0f)
        }

        var arrowWidth by remember {
            mutableStateOf(25f)
        }

        var arrowHeight by remember {
            mutableStateOf(25f)
        }

        var drawArrow by remember {
            mutableStateOf(true)
        }

        var elevation by remember {
            mutableStateOf(2f)
        }

        var borderStrokeWidth by remember {
            mutableStateOf(0f)
        }

        var cornerRadiusTopLeft by remember {
            mutableStateOf(8f)
        }

        var cornerRadiusTopRight by remember {
            mutableStateOf(8f)
        }

        var cornerRadiusBottomLeft by remember {
            mutableStateOf(8f)
        }
        var cornerRadiusBottomRight by remember {
            mutableStateOf(8f)
        }

        var show by remember {
            mutableStateOf(false)
        }

        val tooltipState = remember(
            keys = arrayOf(
                arrowAlignment,
                arrowShape,
                arrowOffsetX,
                arrowOffsetY,
                arrowWidth,
                arrowHeight,
                drawArrow
            )
        ) {
            TooltipState(
                show = show,
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
                drawArrow = true
            )
        }

        Spacer(modifier = Modifier.height(90.dp))

        ToolTipLayout(
            tooltipState = tooltipState,
            content = {

                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(48.dp)
                        .clickable {
                            show = show.not()
                            if (show) {
                                tooltipState.show()
                            } else {
                                tooltipState.dismiss()
                            }
                        }
                        .background(Color.Red, CircleShape),
                    tint = Color.White,
                    imageVector = Icons.Default.Info, contentDescription = null
                )

            },
            tooltip = {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Text(
                        text = "Tooltip\n" +
                                "Message", fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(90.dp))

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            var alignmentFloat by remember {
                mutableStateOf(1f)
            }

            var shapeFloat by remember {
                mutableStateOf(1f)
            }

            ArrowAlignment.values().forEach { alignment ->
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
