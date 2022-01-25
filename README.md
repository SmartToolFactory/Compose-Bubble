### Jetpack Compose Speech Chat Bubble

Chat/Speech bubble width different arrow, background, shadow properties to create chat bubbles
like whatsapp, telegram or other messaging apps have or arrows with arrow at bottom to create
info bubble.

`Modifier.drawBubble` or Modifier.drawBubbleWithShape` modifiers draws bubble behind
composable content using `BubbleState` with remember to store state from previous composition or use `BubbleColumn` if
you don't want to wrap with a Column using neither of these modifiers.

There are 4 demos to test **bubbles**.
* `DemoFullChat` is small chatting sample which displays arrow on first message from sender or user
* `DemoDynamicSize` is for changing bubbles dynamically to observe changes in real time. You can add your custom bubbles to test dynamic changes.
* `DemoBubbles` displays some sample bubbles
* `DemoSimpleLayout` is for demonstrating `Constraints.offset(x,y)` and `Contrainsts.constrainWidth` on `Layout` and `Placeable`

| Full Chat      | Dynamic Size   | Bubble Samples|
| ----------|-----------| -----------|
| <img src="./screenshots/demo_chat.gif"/> | <img src="./screenshots/demo_dynamic.gif"/> | <img src="./screenshots/demo_bubbles.png"/> |


## BubbleState
```
class BubbleState internal constructor(
    var backgroundColor: Color = DefaultBubbleColor,
    var cornerRadius: BubbleCornerRadius = BubbleCornerRadius(
        topLeft = 8.dp,
        topRight = 8.dp,
        bottomLeft = 8.dp,
        bottomRight = 8.dp,
    ),
    var alignment: ArrowAlignment = ArrowAlignment.NONE,
    var arrowShape: ArrowShape = ArrowShape.TRIANGLE_RIGHT,
    var arrowOffsetX: Dp = 0.dp,
    var arrowOffsetY: Dp = 0.dp,
    var arrowWidth: Dp = 14.dp,
    var arrowHeight: Dp = 14.dp,
    var arrowRadius: Dp = 0.dp,
    var drawArrow: Boolean = true,
    var shadow: BubbleShadow? = null,
    var padding: BubblePadding? = null
) 
```

to create a BubbleState to modify bubble to be drawn use one of the overloads of remember functions
```
fun rememberBubbleState(
backgroundColor: Color = DefaultBubbleColor,
cornerRadius: Dp = 8.dp,
alignment: ArrowAlignment = ArrowAlignment.NONE,
arrowShape: ArrowShape = ArrowShape.TRIANGLE_RIGHT,
arrowOffsetX: Dp = 0.dp,
arrowOffsetY: Dp = 0.dp,
arrowWidth: Dp = 14.dp,
arrowHeight: Dp = 14.dp,
arrowRadius: Dp = 0.dp,
drawArrow: Boolean = true,
shadow: BubbleShadow? = null,
padding: BubblePadding? = null
)
```
### Properties
* **backgroundColor**: color of Bubble
* **cornerRadius**: Constructs a Radius for each side of bubble rectangle
* **alignment**: Arrow alignment determines in which side of the bubble this arrow should be drawn. When ArrowAlignment.NONE is selected no arrow is drawn.
* **arrowShape**: Shape of the arrow, It can be right or isosceles triangle or curved shape
* **arrowOffsetX**: Vertical offset for arrow that is positioned on top or at the bottom of the bubble. Positive values move arrow right while negative values move left. Arrow position is limited between left of content and  content right minus arrow width.
* **arrowOffsetY**: Vertical offset for arrow that is positioned on left or right side of the bubble. Positive values move arrow bottom while negative values move up. Arrow position  is limited between top of content and  content bottom minus arrow height.
* **arrowWidth**: width of the arrow
* **arrowHeight**: height of the arrow
* **arrowRadius**: radius of the arrow curves the tip of the arrow
* **drawArrow**: whether we should draw arrow or only have rectangle shape bubble
* **shadow**: of the arrow contains elevation, dx, dy, radius and color to draw shadow below bubble
* **padding**: padding between bubble and it's content. Use this instead of **Modifier.padding()**

### Usage

Create a `Column`, `Row` or `Box` and set `Modifier.dawBubble` or `Modifier.dawBubbleWithShape` with a `BubbleState`
```
@Composable
private fun BubbleLayout(
    modifier: Modifier = Modifier,
    bubbleState: BubbleState,
    content: @Composable () -> Unit
) {
    Column(
        modifier.drawBubble(bubbleState)
    ) {
        content()
    }
}

@Composable
private fun BubbleLayoutWithShape(
    modifier: Modifier = Modifier,
    bubbleState: BubbleState,
    content: @Composable () -> Unit
) {

    Column(
        modifier
            .drawBubbleWithShape(bubbleState)
    ) {
        content()
    }
}
```
And create a `Composable` as
```
val bubbleState = rememberBubbleState(
    backgroundColor = SentMessageColor,
    alignment = ArrowAlignment.RIGHT_BOTTOM,
    cornerRadius = 8.dp,
    shadow = BubbleShadow(
        elevation = 1.dp
    ),
    padding = Padding(8.dp)
)

BubbleLayout(
    bubbleState = bubbleState
) {
    Text(text = "Arrow RIGHT_BOTTOM")
}
```

### Shadow
`Modifier.dawBubble` uses shadow software layer to draw colorful shadows with radius and transparency.
Customized and set calculations almost similar with default Android api Modifier.shadow()
but if you are not happy with shadows you can use `Modifier.dawBubbleWithShape` to
create a **shape** from bubble **path** and use that shape to create shadow with `Modifier.shadow`