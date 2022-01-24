package com.smarttoolfactory.composespeechbubble

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.composespeechbubble.ui.theme.BackgroundColor
import com.smarttoolfactory.composespeechbubble.ui.theme.DateColor
import com.smarttoolfactory.composespeechbubble.ui.theme.SentMessageColor
import com.smarttoolfactory.speechbubble.*

@Composable
fun DemoBubble() {


    CustomColumnWithScope(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(8.dp)
    ) {


        val bubbleStateDate = rememberBubbleState(
            alignment = ArrowAlignment.None,
            backgroundColor = DateColor,
            cornerRadius = 5.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            ),
            padding = Padding(8.dp)
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Center),
            bubbleState = bubbleStateDate
        ) {
            Text(
                "ArrowNone",
                fontSize = 16.sp,
                modifier = Modifier
            )
        }

        Spacer(modifier = Modifier.height(8.dp))


        // WHATSAPP SENT MESSAGE

        val bubbleStateSent1 = rememberBubbleState(
            backgroundColor = SentMessageColor,
            alignment = ArrowAlignment.RightTop,
            arrowOffsetY = 5.dp,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            ),
            padding = Padding(8.dp)
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.End),
            bubbleState = bubbleStateSent1
        ) {
            Text(text = "Arrow RIGHT_TOP with offset 5dp")
        }

        Spacer(modifier = Modifier.height(4.dp))

        val bubbleStateSent2 = rememberBubbleState(
            backgroundColor = SentMessageColor,
            alignment = ArrowAlignment.RightBottom,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            ),
            padding = Padding(8.dp)
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.End),
            bubbleState = bubbleStateSent2
        ) {
            Text(text = "Arrow RIGHT_BOTTOM")
        }
        Spacer(modifier = Modifier.height(4.dp))


        val bubbleStateSent3 = rememberBubbleState(
            backgroundColor = SentMessageColor,
            alignment = ArrowAlignment.RightBottom,
            drawArrow = false,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            ),
            padding = Padding(8.dp)
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.End),
            bubbleState = bubbleStateSent3
        ) {
            Text(text = "Arrow RIGHT_BOTTOM drawArrow=false")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // WHATSAPP RECEIVED MESSAGE

        val bubbleStateReceived1 = rememberBubbleState(
            backgroundColor = DefaultBubbleColor,
            alignment = ArrowAlignment.LeftBottom,
            arrowOffsetY = (-8).dp,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            ),
            padding = Padding(8.dp)
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateReceived1
        ) {
            Text(text = "Arrow LEFT_BOTTOM offset=-8dp")
        }
        Spacer(modifier = Modifier.height(4.dp))


        val bubbleStateReceived2 = rememberBubbleState(
            backgroundColor = DefaultBubbleColor,
            alignment = ArrowAlignment.LeftTop,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            ),
            padding = Padding(8.dp)
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateReceived2
        ) {
            Text(text = "Arrow LEFT_TOP")
        }
        Spacer(modifier = Modifier.height(4.dp))

        val bubbleStateReceived3 = rememberBubbleState(
            backgroundColor = DefaultBubbleColor,
            alignment = ArrowAlignment.LeftTop,
            drawArrow = false,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            ),
            padding = Padding(8.dp)
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateReceived3
        ) {
            Text(text = "Arrow LEFT_TOP drawArrow=false")
        }
        Spacer(modifier = Modifier.height(4.dp))

        val bubbleStateReceived4 = rememberBubbleState(
            backgroundColor = DefaultBubbleColor,
            alignment = ArrowAlignment.LeftCenter,
            arrowShape = ArrowShape.TRIANGLE_ISOSCELES,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            ),
            padding = Padding(8.dp)
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateReceived4
        ) {
            Text(text = "Arrow LEFT_CENTER shape=ISOSCELES")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // BOTTOM ARROW BUBBLES

        val bubbleStateBottom1 = rememberBubbleState(
            backgroundColor = Color(0xfffbc02d),
            alignment = ArrowAlignment.BottomCenter,
            arrowShape = ArrowShape.TRIANGLE_ISOSCELES,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 1.dp
            ),
            padding = Padding(8.dp)
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Center),
            bubbleState = bubbleStateBottom1
        ) {
            Text(text = "Arrow BOTTOM_CENTER shape=ISOSCELES", color = Color.White)
        }
        Spacer(modifier = Modifier.height(4.dp))

        val bubbleStateBottom2 = rememberBubbleState(
            backgroundColor = Color(0xff29B6F6),
            alignment = ArrowAlignment.BottomLeft,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 3.dp
            ),
            arrowWidth = 20.dp,
            padding = Padding(8.dp)
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateBottom2
        ) {
            Text(text = "Arrow BOTTOM_LEFT", color = Color.White)
        }
        Spacer(modifier = Modifier.height(4.dp))

        val bubbleStateBottom3 = rememberBubbleState(
            backgroundColor = Color(0xffEC407A),
            alignment = ArrowAlignment.BottomRight,
            cornerRadius = 8.dp,
            shadow = BubbleShadow(
                elevation = 4.dp,
                color = Color(0xffEC407A)
            ),
            arrowWidth = 20.dp,
            padding = Padding(8.dp)
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.End),
            bubbleState = bubbleStateBottom3
        ) {
            Text(text = "Arrow BOTTOM_RIGHT", color = Color.White)
        }
        Spacer(modifier = Modifier.height(4.dp))

        // CUSTOM RADIUS ARROWS
        val bubbleStateCustomRadius1 = rememberBubbleState(
            backgroundColor = Color(0xff5c6bc0),
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
            ),
            padding = Padding(8.dp)
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateCustomRadius1
        ) {
            Text(text = "Arrow Custom radius1", color = Color.White)
        }
        Spacer(modifier = Modifier.height(4.dp))

        val bubbleStateCustomRadius2 = rememberBubbleState(
            backgroundColor = Color(0xff5c6bc0),
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
            ),
            padding = Padding(8.dp)
        )

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateCustomRadius2
        ) {
            Text(text = "Arrow Custom radius2", color = Color.White)
        }
        Spacer(modifier = Modifier.height(4.dp))

        val bubbleStateCustomRadius3 = rememberBubbleState(
            backgroundColor = Color(0xff5c6bc0),
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
            ),
            padding = Padding(8.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        BubbleLayout(
            modifier = Modifier.horizontalAlign(HorizontalAlignment.Start),
            bubbleState = bubbleStateCustomRadius3
        ) {
            Text(text = "Arrow Custom radius3", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}
