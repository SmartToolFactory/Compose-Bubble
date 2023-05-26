package com.smarttoolfactory.speechbubble

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.Dp


@Immutable
data class BubbleShadow(
    val elevation: Dp,
    val ambientColor: Color = DefaultShadowColor,
    val spotColor: Color = DefaultShadowColor,
)
