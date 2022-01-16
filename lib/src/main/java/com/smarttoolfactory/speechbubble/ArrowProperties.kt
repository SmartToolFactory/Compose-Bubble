package com.smarttoolfactory.speechbubble

class BubbleCornerRadius(
    var topLeft: Float = 0f,
    var topRight: Float = 0f,
    var bottomLeft: Float = 0f,
    var bottomRight: Float = 0f,
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
    BOTTOM_RIGHT
}
