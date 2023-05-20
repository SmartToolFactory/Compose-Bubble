package com.smarttoolfactory.composespeechbubble

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.smarttoolfactory.speechbubble.BubbleState
import com.smarttoolfactory.speechbubble.bubble
import com.smarttoolfactory.speechbubble.drawBubble

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
            .then(
                bubbleState.padding?.let { padding ->
                    Modifier.padding(
                        padding.start,
                        padding.top,
                        padding.end,
                        padding.bottom
                    )
                } ?: Modifier
            )

    ) {
        content()
    }
}
