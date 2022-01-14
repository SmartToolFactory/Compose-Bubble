package com.smarttoolfactory.speechbubble

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
class BubbleShadow internal constructor(
    val color: Color = Color(0x55000000),
    val alpha: Float = 0.7f,
    val shadowRadius: Dp = 1.dp,
    val offsetY: Dp = 1.dp,
    val offsetX: Dp = 1.dp,
    val useSoftwareLayer: Boolean = true
)