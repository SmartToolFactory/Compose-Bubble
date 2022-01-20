package com.smarttoolfactory.speechbubble

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class BubbleCornerRadius(
    var topLeft: Dp = 0.dp,
    var topRight: Dp = 0.dp,
    var bottomLeft: Dp = 0.dp,
    var bottomRight: Dp = 0.dp,
)


enum class ArrowShape {
    TRIANGLE_RIGHT,
    TRIANGLE_ISOSCELES,
    CURVED
}


enum class ArrowAlignment {
    NONE,
    LEFT_TOP,
    LEFT_CENTER,
    LEFT_BOTTOM,
    RIGHT_TOP,
    RIGHT_CENTER,
    RIGHT_BOTTOM,
    BOTTOM_LEFT,
    BOTTOM_CENTER,
    BOTTOM_RIGHT,
    TOP_LEFT,
    TOP_CENTER,
    TOP_RIGHT
}
