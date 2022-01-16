package com.smarttoolfactory.speechbubble

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Creates a colorable shadow instance.
 *
 * @param color Color of the shadow
 * @param alpha of the color of the shadow
 * @param useSoftwareLayer use software layer to draw shadow with blur
 * @param dX x offset of shadow blur
 * @param dY y offset of shadow blur
 * @param shadowRadius radius of shadow blur if useSoftwareLayer is set to true
 */
fun BubbleShadow(
    color: Color = ShadowColor,
    alpha: Float = .7f,
    useSoftwareLayer: Boolean = true,
    dX: Dp = 1.dp,
    dY: Dp = 1.dp,
    shadowRadius: Dp = 1.dp,
): BubbleShadow {
    return BubbleShadow(
        color,
        alpha,
        dX,
        dY,
        shadowRadius,
        useSoftwareLayer
    )
}

/**
 * Creates a colorable shadow instance.
 *
 * @param color Color of the shadow
 * @param alpha of the color of the shadow
 * @param useSoftwareLayer use software layer to draw shadow with blur
 * @param elevation elevation of the badge with shadow. Sets dx, dy,
 * and shadowRadius if software layer is used
 */
fun BubbleShadow(
    color: Color = ShadowColor,
    alpha: Float = .7f,
    useSoftwareLayer: Boolean = true,
    elevation: Dp = 1.dp
): BubbleShadow {
    return BubbleShadow(
        color,
        alpha,
        elevation,
        elevation,
        elevation,
        useSoftwareLayer
    )
}

class BubbleShadow internal constructor(
    val color: Color = ShadowColor,
    val alpha: Float = .7f,
    val shadowRadius: Dp = 1.dp,
    val offsetY: Dp = 1.dp,
    val offsetX: Dp = 1.dp,
    val useSoftwareLayer: Boolean = true
)
