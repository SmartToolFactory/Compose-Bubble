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



const val NONE = 0
const val LEFT_TOP = 1
const val LEFT_CENTER = 2
const val LEFT_BOTTOM = 3
const val RIGHT_TOP = 4
const val RIGHT_CENTER = 5
const val RIGHT_BOTTOM = 6
const val BOTTOM_LEFT = 7
const val BOTTOM_CENTER = 8
const val BOTTOM_RIGHT = 9