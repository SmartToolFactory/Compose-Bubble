package com.smarttoolfactory.composespeechbubble.demo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.composespeechbubble.BubbleLayout
import com.smarttoolfactory.composespeechbubble.CustomColumnWithScope
import com.smarttoolfactory.composespeechbubble.HorizontalAlignment
import com.smarttoolfactory.composespeechbubble.ui.theme.BackgroundColor
import com.smarttoolfactory.composespeechbubble.ui.theme.DateColor
import com.smarttoolfactory.composespeechbubble.ui.theme.SentMessageColor
import com.smarttoolfactory.bubble.*

@Preview
@Composable
fun DemoBubble() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomColumnWithScope(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(BackgroundColor)
                .padding(8.dp)
        ) {

            val bubbleStateDate = rememberBubbleState(
                alignment = ArrowAlignment.None,
                cornerRadius = 5.dp
            )

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.Center),
                bubbleState = bubbleStateDate,
                backgroundColor = DateColor,
                shadow = BubbleShadow(
                    elevation = 2.dp
                )
            ) {
                Text(
                    text = "ArrowNone",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            // WHATSAPP SENT MESSAGE

            val bubbleStateSent1 = rememberBubbleState(
                alignment = ArrowAlignment.RightTop,
                arrowOffsetY = 5.dp,
                cornerRadius = 8.dp
            )

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.End),
                bubbleState = bubbleStateSent1,
                backgroundColor = SentMessageColor,
                shadow = BubbleShadow(
                    elevation = 2.dp
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Arrow RightTop with offset 5dp"
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.End),
                bubbleState = bubbleStateSent1,
                backgroundColor = SentMessageColor,
                shadow = BubbleShadow(
                    elevation = 2.dp
                ),
                borderStroke = BorderStroke(1.dp, Color.Red)
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Arrow RightTop with offset 5dp"
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            val bubbleStateSent2 = rememberBubbleState(
                alignment = ArrowAlignment.RightBottom,
                cornerRadius = 8.dp
            )

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.End),
                bubbleState = bubbleStateSent2,
                backgroundColor = SentMessageColor,
                shadow = BubbleShadow(
                    elevation = 2.dp
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Arrow RightBottom"
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.End),
                bubbleState = bubbleStateSent2,
                backgroundColor = SentMessageColor,
                shadow = BubbleShadow(
                    elevation = 2.dp
                ),
                borderStroke = BorderStroke(1.dp, Color.Red)
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Arrow RightBottom"
                )
            }
            Spacer(modifier = Modifier.height(4.dp))


            val bubbleStateSent3 = rememberBubbleState(
                alignment = ArrowAlignment.RightBottom,
                drawArrow = false,
                cornerRadius = 8.dp
            )

            BubbleLayout(
                modifier = Modifier
                    .horizontalAlign(HorizontalAlignment.End),
                bubbleState = bubbleStateSent3,
                backgroundColor = SentMessageColor,
                shadow = BubbleShadow(
                    elevation = 2.dp
                )
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Arrow RightBottom drawArrow=false"
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            BubbleLayout(
                modifier = Modifier
                    .horizontalAlign(HorizontalAlignment.End),
                bubbleState = bubbleStateSent3,
                backgroundColor = SentMessageColor,
                shadow = BubbleShadow(
                    elevation = 2.dp
                ),
                borderStroke = BorderStroke(1.dp, Color.Red)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Arrow RightBottom drawArrow=false"
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // WHATSAPP RECEIVED MESSAGE

            val bubbleStateReceived1 = rememberBubbleState(
                alignment = ArrowAlignment.LeftBottom,
                arrowOffsetY = (-8).dp,
                cornerRadius = 8.dp
            )

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
                bubbleState = bubbleStateReceived1,
                shadow = BubbleShadow(
                    elevation = 2.dp
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Arrow LeftBottom offset=-8dp"
                )
            }
            Spacer(modifier = Modifier.height(4.dp))


            val bubbleStateReceived2 = rememberBubbleState(
                alignment = ArrowAlignment.LeftTop,
                cornerRadius = 8.dp
            )

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
                bubbleState = bubbleStateReceived2,
                shadow = BubbleShadow(
                    elevation = 2.dp
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Arrow LeftTop"
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            val bubbleStateReceived3 = rememberBubbleState(
                alignment = ArrowAlignment.LeftTop,
                drawArrow = false,
                cornerRadius = 8.dp
            )

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
                bubbleState = bubbleStateReceived3,
                shadow = BubbleShadow(
                    elevation = 2.dp
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Arrow LeftTop drawArrow=false"
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            val bubbleStateReceived4 = rememberBubbleState(
                alignment = ArrowAlignment.LeftCenter,
                arrowShape = ArrowShape.FullTriangle,
                cornerRadius = 8.dp
            )

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
                bubbleState = bubbleStateReceived4,
                shadow = BubbleShadow(
                    elevation = 2.dp
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Arrow LeftCenter shape=FullTriangle"
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // BOTTOM ARROW BUBBLES

            val bubbleStateBottom1 = rememberBubbleState(
                alignment = ArrowAlignment.BottomCenter,
                arrowShape = ArrowShape.FullTriangle,
                cornerRadius = 8.dp
            )

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.Center),
                bubbleState = bubbleStateBottom1,
                backgroundColor = Color(0xfffbc02d),
                shadow = BubbleShadow(
                    elevation = 2.dp
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Arrow BottomCenter shape=FullTriangle",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            val bubbleStateBottom2 = rememberBubbleState(
                alignment = ArrowAlignment.BottomLeft,
                cornerRadius = 8.dp,
                arrowWidth = 20.dp
            )

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
                bubbleState = bubbleStateBottom2,
                backgroundColor = Color(0xff29B6F6),
                shadow = BubbleShadow(
                    elevation = 2.dp
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Arrow BottomLeft", color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            val bubbleStateBottom3 = rememberBubbleState(
                alignment = ArrowAlignment.BottomRight,
                cornerRadius = 8.dp,
                arrowWidth = 20.dp
            )

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.End),
                bubbleState = bubbleStateBottom3,
                backgroundColor = Color(0xffEC407A),
                BubbleShadow(
                    elevation = 4.dp,
                    ambientColor = Color(0xffEC407A)
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Arrow Right",
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
                )
            )

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
                bubbleState = bubbleStateCustomRadius1,
                backgroundColor = Color(0xff5c6bc0),
                shadow = BubbleShadow(
                    elevation = 1.dp,
                )
            ) {
                Text(
                    modifier = Modifier
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
                )
            )

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
                bubbleState = bubbleStateCustomRadius2,
                backgroundColor = Color(0xff5c6bc0),
                shadow = BubbleShadow(
                    elevation = 1.dp,
                )
            ) {
                Text(
                    modifier = Modifier
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
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            BubbleLayout(
                modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
                bubbleState = bubbleStateCustomRadius3,
                backgroundColor = Color(0xff5c6bc0),
                shadow = BubbleShadow(
                    elevation = 1.dp,
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Arrow Custom radius3",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
