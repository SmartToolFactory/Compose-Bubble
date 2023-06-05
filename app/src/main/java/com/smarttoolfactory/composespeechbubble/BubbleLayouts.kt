package com.smarttoolfactory.composespeechbubble

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.smarttoolfactory.bubble.BubbleShadow
import com.smarttoolfactory.bubble.BubbleState
import com.smarttoolfactory.bubble.bubble

@Composable
fun BubbleLayout(
    modifier: Modifier = Modifier,
    bubbleState: BubbleState,
    backgroundColor: Color = Color.White,
    shadow: BubbleShadow? = null,
    borderStroke: BorderStroke? = null,
    content: @Composable () -> Unit
) {
    Column(
        modifier.bubble(
            bubbleState = bubbleState,
            color = backgroundColor,
            shadow = shadow,
            borderStroke = borderStroke
        )
    ) {
        content()
    }
}
