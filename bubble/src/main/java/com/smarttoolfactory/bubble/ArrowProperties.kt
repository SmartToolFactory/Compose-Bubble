package com.smarttoolfactory.bubble

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class BubbleCornerRadius(
    val topLeft: Dp = 0.dp,
    val topRight: Dp = 0.dp,
    val bottomLeft: Dp = 0.dp,
    val bottomRight: Dp = 0.dp,
)


enum class ArrowShape {
    HalfTriangle,
    FullTriangle,
    Curved
}

enum class ArrowAlignment {
    None,
    LeftTop,
    LeftCenter,
    LeftBottom,
    RightTop,
    RightCenter,
    RightBottom,
    BottomLeft,
    BottomCenter,
    BottomRight,
    TopLeft,
    TopCenter,
    TopRight
}
