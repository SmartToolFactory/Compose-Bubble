package com.smarttoolfactory.composespeechbubble.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.composespeechbubble.BubbleLayout
import com.smarttoolfactory.composespeechbubble.CustomColumnScope.Companion.horizontalAlign
import com.smarttoolfactory.composespeechbubble.CustomColumnWithScope
import com.smarttoolfactory.composespeechbubble.HorizontalAlignment
import com.smarttoolfactory.composespeechbubble.ui.theme.BackgroundColor
import com.smarttoolfactory.composespeechbubble.ui.theme.DateColor
import com.smarttoolfactory.composespeechbubble.ui.theme.SentMessageColor
import com.smarttoolfactory.speechbubble.*

@Preview
@Composable
private fun BubbleTopArrowTest() {
    Column(
        modifier = Modifier
            .border(2.dp, Color.Green)
    ) {

        Spacer(modifier = Modifier.height(20.dp))
        val bubbleStateDate = rememberBubbleState(
            alignment = ArrowAlignment.TopCenter,
            cornerRadius = 5.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            )
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Center),
            bubbleState = bubbleStateDate
        ) {
            Text(
                text = "Arrow Top",
                fontSize = 16.sp,
                modifier = Modifier
                    .background(SentMessageColor)
                    .padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun DemoBubble() {

    SideEffect {
        println("🔥DemoBubble")
    }

    CustomColumnWithScope(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(8.dp)
    ) {


        val bubbleStateDate = rememberBubbleState(
            alignment = ArrowAlignment.None,
            cornerRadius = 5.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            )
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Center),
            bubbleState = bubbleStateDate
        ) {
            Text(
                text = "ArrowNone",
                fontSize = 16.sp,
                modifier = Modifier
                    .background(DateColor)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))


        // WHATSAPP SENT MESSAGE

        val bubbleStateSent1 = rememberBubbleState(
            alignment = ArrowAlignment.RightTop,
            arrowOffsetY = 5.dp,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            )
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.End),
            bubbleState = bubbleStateSent1
        ) {
            Text(
                modifier = Modifier
                    .background(SentMessageColor)
                    .padding(8.dp),
                text = "Arrow RIGHT_TOP with offset 5dp"
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        val bubbleStateSent2 = rememberBubbleState(
            alignment = ArrowAlignment.RightBottom,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            )
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.End),
            bubbleState = bubbleStateSent2
        ) {
            Text(
                modifier = Modifier
                    .background(SentMessageColor)
                    .padding(8.dp),
                text = "Arrow RIGHT_BOTTOM"
            )
        }
        Spacer(modifier = Modifier.height(4.dp))


        val bubbleStateSent3 = rememberBubbleState(
            alignment = ArrowAlignment.RightBottom,
            drawArrow = false,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            )
        )

        BubbleLayout(
            modifier = Modifier
                .background(SentMessageColor)
                .horizontalAlign(HorizontalAlignment.End),
            bubbleState = bubbleStateSent3
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Arrow RIGHT_BOTTOM drawArrow=false"
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        // WHATSAPP RECEIVED MESSAGE

        val bubbleStateReceived1 = rememberBubbleState(
            alignment = ArrowAlignment.LeftBottom,
            arrowOffsetY = (-8).dp,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            )
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateReceived1
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = "Arrow LEFT_BOTTOM offset=-8dp"
            )
        }
        Spacer(modifier = Modifier.height(4.dp))


        val bubbleStateReceived2 = rememberBubbleState(
            alignment = ArrowAlignment.LeftTop,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            )
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateReceived2
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = "Arrow LEFT_TOP"
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        val bubbleStateReceived3 = rememberBubbleState(
            alignment = ArrowAlignment.LeftTop,
            drawArrow = false,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            )
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateReceived3
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = "Arrow LEFT_TOP drawArrow=false"
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        val bubbleStateReceived4 = rememberBubbleState(
            alignment = ArrowAlignment.LeftCenter,
            arrowShape = ArrowShape.FullTriangle,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            )
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateReceived4
        ) {
            Text(
                modifier = Modifier
                    .background(DefaultBubbleColor)
                    .padding(8.dp),
                text = "Arrow LEFT_CENTER shape=ISOSCELES"
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        // BOTTOM ARROW BUBBLES

        val bubbleStateBottom1 = rememberBubbleState(
            alignment = ArrowAlignment.BottomCenter,
            arrowShape = ArrowShape.FullTriangle,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            )
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Center),
            bubbleState = bubbleStateBottom1
        ) {
            Text(
                modifier = Modifier
                    .background(Color(0xfffbc02d))
                    .padding(8.dp),
                text = "Arrow BOTTOM_CENTER shape=ISOSCELES",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        val bubbleStateBottom2 = rememberBubbleState(
            alignment = ArrowAlignment.BottomLeft,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 3.dp
            ),
            arrowWidth = 20.dp
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateBottom2
        ) {
            Text(
                modifier = Modifier
                    .background(Color(0xff29B6F6))
                    .padding(8.dp),
                text = "Arrow BOTTOM_LEFT", color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        val bubbleStateBottom3 = rememberBubbleState(
            alignment = ArrowAlignment.BottomRight,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 4.dp,
                ambientColor = Color(0xffEC407A)
            ),
            arrowWidth = 20.dp
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.End),
            bubbleState = bubbleStateBottom3
        ) {
            Text(
                modifier = Modifier
                    .background(Color(0xffEC407A))
                    .padding(8.dp),
                text = "Arrow BOTTOM_RIGHT",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        // CUSTOM RADIUS ARROWS
        val bubbleStateCustomRadius1 = rememberBubbleState(
            alignment = ArrowAlignment.LeftTop,
            drawArrow = false,
            cornerRadius = BubbleCornerRadius(
                topLeft = 24.dp,
                topRight = 16.dp,
                bottomLeft = 2.dp,
                bottomRight = 16.dp
            ),
            shadow = BubbleShadow(
                elevation = 1.dp,
            )
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateCustomRadius1
        ) {
            Text(
                modifier = Modifier
                    .background( Color(0xff5c6bc0))
                    .padding(8.dp),
                text = "Arrow Custom radius1",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        val bubbleStateCustomRadius2 = rememberBubbleState(
            alignment = ArrowAlignment.LeftTop,
            drawArrow = false,
            cornerRadius = BubbleCornerRadius(
                topLeft = 2.dp,
                topRight = 16.dp,
                bottomLeft = 2.dp,
                bottomRight = 16.dp
            ),
            shadow = BubbleShadow(
                elevation = 1.dp,
            )
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateCustomRadius2
        ) {
            Text(
                modifier = Modifier
                    .background(Color(0xff5c6bc0))
                    .padding(8.dp),
                text = "Arrow Custom radius2",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        val bubbleStateCustomRadius3 = rememberBubbleState(
            alignment = ArrowAlignment.LeftTop,
            drawArrow = false,
            cornerRadius = BubbleCornerRadius(
                topLeft = 2.dp,
                topRight = 16.dp,
                bottomLeft = 8.dp,
                bottomRight = 16.dp
            ),
            shadow = BubbleShadow(
                elevation = 1.dp,
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateCustomRadius3
        ) {
            Text(
                modifier = Modifier
                    .background(Color(0xff5c6bc0))
                    .padding(8.dp),
                text = "Arrow Custom radius3",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}
