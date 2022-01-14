package com.smarttoolfactory.speechbubble

data class BubbleRect(
    var left: Float = 0f,
    var top: Float = 0f,
    var right: Float = 0f,
    var bottom: Float = 0f
) {

    fun set(left: Float, top: Float, right: Float, bottom: Float) {
        this.left = left
        this.top = top
        this.right = right
        this.bottom = bottom
    }

    val height: Float
        get() {
            return right - left
        }

    val width: Float
        get() {
            return bottom - top
        }

    override fun toString(): String {
        return "left: $left, top: $top, right: $right, bottom: $bottom, " +
                "width: $width, height: $height"
    }
}