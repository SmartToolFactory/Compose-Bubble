package com.smarttoolfactory.composespeechbubble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import com.smarttoolfactory.composespeechbubble.ui.ChatAppbar
import com.smarttoolfactory.composespeechbubble.ui.theme.ComposeSpeechBubbleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSpeechBubbleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Scaffold(
                        topBar = {
                            ChatAppbar("Chat/Speech Bubbles")
                        }
                    ) {
                        DemoSimpleLayout()
//                        DemoBubble()
//                        DemoDynamicSize()
                    }
                }
            }
        }
    }
}
