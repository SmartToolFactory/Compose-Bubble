package com.smarttoolfactory.composespeechbubble

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.smarttoolfactory.speechbubble.BubbleState
import com.smarttoolfactory.speechbubble.bubble

@Composable
fun BubbleLayout(
    modifier: Modifier = Modifier,
    bubbleState: BubbleState,
    content: @Composable () -> Unit
) {
    Column(
        modifier
//            .drawBubble(bubbleState)
            .bubble(bubbleState)
    ) {
        content()
    }
}
