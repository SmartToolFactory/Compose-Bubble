package com.smarttoolfactory.composespeechbubble

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.smarttoolfactory.speechbubble.BubbleShadow
import com.smarttoolfactory.speechbubble.BubbleState
import com.smarttoolfactory.speechbubble.bubble

@Composable
fun BubbleLayout(
    modifier: Modifier = Modifier,
    bubbleState: BubbleState,
    color: Color = Color.White,
    shadow: BubbleShadow? = null,
    borderStroke: BorderStroke? = null,
    content: @Composable () -> Unit
) {
    Column(
        modifier.bubble(
            bubbleState = bubbleState,
            color = color,
            shadow = shadow,
            borderStroke = borderStroke
        )
    ) {
        content()
    }
}
