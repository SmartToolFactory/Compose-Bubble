package com.smarttoolfactory.bubble

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
data class BubbleRect(
    val left: Float = 0f,
    val top: Float = 0f,
    val right: Float = 0f,
    val bottom: Float = 0f
) {

    val height: Float
        get() {
            return bottom - top
        }

    val width: Float
        get() {
            return right - left
        }

    override fun toString(): String {
        return "left: $left, top: $top, right: $right, bottom: $bottom, " +
                "width: $width, height: $height"
    }

    companion object {
        @Stable
        val Zero = BubbleRect(0f, 0f, 0f, 0f)
    }
}