package me.kartdroid.androidkitchen2.components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateList
import androidx.compose.runtime.toMutableStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.kartdroid.androidkitchen2.components.preview.CardData
import me.kartdroid.androidkitchen2.components.preview.CardStackPreviewProvider
import kotlin.math.abs
import kotlin.math.roundToInt

// Direction enum to define swipe directions
enum class SwipeDirection {
    LEFT, RIGHT, UP, DOWN, NONE
}

// Function to track the result of a swipe
data class SwipeResult<T>(
    val direction: SwipeDirection,
    val item: T
)

/**
 * A Stack of Cards View where the rotation angle of each card is configurable
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 20/03/25.
 */

@Composable
fun <T> SwipeAbleCardStack(
    modifier: Modifier = Modifier,
    items: PersistentList<T>,
    visibleCardCount: Int = 4,
    keyConfig: (T) -> Any,
    thresholdConfig: (Float, Float) -> Float = { _, _ -> 0.1f },
    rotationsConfig: (Int, T) -> Float = { _, _ -> 0f },
    scaleConfig: (Int,T) -> Float = { index, _ -> 1f - (index * 0.02f)},
    zIndexConfig: (Int, Int, T) -> Float = { size, index, T -> (size - index).toFloat() },
    onSwipe: (SwipeResult<T>) -> Unit = {},
    cardContent: @Composable (T, Int) -> Unit
) {
    if (items.isEmpty()) return

    // Remember the card indices
    val sequencedItems = remember { items.toMutableStateList() }

    // Size of the CardStack container
    var containerSize by remember { mutableStateOf(IntSize.Zero) }

    // Coroutine scope for animations
    val coroutineScope = rememberCoroutineScope()

    // Track drag state
    val topCardDragOffset = remember { Animatable(Offset.Zero, Offset.VectorConverter) }

    // Calculate rotation based on drag offset
    val dragRotation by remember {
        derivedStateOf {
            if (containerSize.width > 0) {
                topCardDragOffset.value.x * 15f / containerSize.width
            } else {
                0f
            }
        }
    }

    // Calculate if we've crossed the threshold for swipe
    val threshold by remember {
        derivedStateOf {
            if (containerSize.width > 0 && containerSize.height > 0) {
                thresholdConfig(containerSize.width.toFloat(), containerSize.height.toFloat())
            } else {
                0.0f
            }
        }
    }

    // Determine swipe direction
    val swipeDirection by remember {
        derivedStateOf {
            val offsetX = topCardDragOffset.value.x
            val offsetY = topCardDragOffset.value.y

            val xRatio = abs(offsetX) / (containerSize.width * threshold)
            val yRatio = abs(offsetY) / (containerSize.height * threshold)
            if (xRatio >= 1) {
                if (offsetX > 0) SwipeDirection.RIGHT else SwipeDirection.LEFT
            } else if (yRatio >= 1) {
                if (offsetY > 0) SwipeDirection.DOWN else SwipeDirection.UP
            } else {
                SwipeDirection.NONE
            }
        }
    }

    // Function to handle swipe
    val executeSwipe = { direction: SwipeDirection, item: T ->
        if (direction != SwipeDirection.NONE) {
            // val targetIndex = currentIndex
            onSwipe(SwipeResult(direction, item = item))

            // Update current index after swipe
            sequencedItems.add(sequencedItems.removeFirst())
            true
        } else false
    }
    var isDragging by remember {
        mutableStateOf(false)
    }
    var shouldAnimateRotation by remember {
        mutableStateOf(true)
    }
    // Function to handle card settling
    val settle = { item: T ->
        coroutineScope.launch {
            val settlePoint = if (swipeDirection == SwipeDirection.NONE) {
                // Return to center if not past threshold
                Offset.Zero
            } else {
                // Continue swipe if past threshold
                val xMultiplier = when (swipeDirection) {
                    SwipeDirection.LEFT -> -2f
                    SwipeDirection.RIGHT -> 2f
                    else -> 0f
                }
                val yMultiplier = when (swipeDirection) {
                    SwipeDirection.UP -> -2f
                    SwipeDirection.DOWN -> 2f
                    else -> 0f
                }

                Offset(
                    x = xMultiplier * containerSize.width.toFloat(),
                    y = yMultiplier * containerSize.height.toFloat()
                )
            }

            topCardDragOffset.animateTo(
                targetValue = settlePoint,
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
            ) {

                // If we've reached the target and it's not center, execute the swipe
                if (settlePoint != Offset.Zero && value.x.roundToInt() == settlePoint.x.roundToInt() &&
                    value.y.roundToInt() == settlePoint.y.roundToInt()
                ) {
                    if (executeSwipe(swipeDirection, item)) {
                        // Reset offset after successful swipe
                        isDragging = false
                        coroutineScope.launch {
                            topCardDragOffset.snapTo(Offset.Zero)
                        }
                    }else {
                        isDragging = false
                    }
                }
            }
        }
    }

    val currentRotations: SnapshotStateMap<Any, Float> = remember {
        items.mapIndexed { index, item -> keyConfig(item) to 0f/*if(index == 0) -4f else 0f*/ }
            .toMutableStateMap()
    }
    val currentScales: SnapshotStateMap<Any, Float> = remember {
        items.mapIndexed { index, item ->
            keyConfig(item) to scaleConfig(index, item)
        }.toMutableStateMap()
    }
    val currentZIndex: SnapshotStateMap<Any, Float> = remember {
        items.mapIndexed { index, item ->
            keyConfig(item) to zIndexConfig(items.size, index, item)
        }.toMutableStateMap()
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged {
                containerSize = it
            }
    ) {
        LaunchedEffect(Unit) {
            delay(500)
            sequencedItems.forEachIndexed { index, item ->
                currentRotations[keyConfig(item)] = rotationsConfig(index, item)
            }
            delay(500)
            shouldAnimateRotation = false
        }
        // Display up to visibleCardCount cards
        sequencedItems.forEachIndexed { index, item ->
            // Only show if it's within the visible range
            // reverseIndex >= currentIndex && reverseIndex < currentIndex + visibleCardCount

            if (index < visibleCardCount) {

                // Apply modifiers only to the top card
                val isTopCard = index == 0
                key(keyConfig(item)) {

                    // Get the default rotation for this card
                    val defaultRotation by animateFloatAsState(
                        currentRotations.getValue(keyConfig(item)),
                        animationSpec = tween(
                            if (shouldAnimateRotation || isTopCard) 500 else 200,
                            delayMillis = if (isTopCard) 100 else 0
                        ),
                        //animationSpec = tween(200 , delayMillis = 0),
                        label = "rotation"
                    )
                    // Scale and opacity effect for cards behind the top card
                    val scale by animateFloatAsState(
                        currentScales.getValue(keyConfig(item)),
                        animationSpec = tween(200 , delayMillis = 0),
                        //animationSpec = tween(if(shouldAnimateRotation || isTopCard) 500 else 0, delayMillis = if(isTopCard)100 else 0),
                        label = "scale")

                    LaunchedEffect(Unit) {
                        if(isTopCard) {
                            Log.d("KC_DEBUG", "item=${keyConfig(item)} currentRotation =${ currentRotations[keyConfig(item)]}, newRotation=${rotationsConfig(index, item)}")
                        }
                        //delay(300)
                        currentRotations[keyConfig(item)] = rotationsConfig(index, item)
                        currentScales[keyConfig(item)] = scaleConfig(index, item)
                        currentZIndex[keyConfig(item)] = zIndexConfig(sequencedItems.size, index, item)
                    }


                    val cardModifier = if (isTopCard) {
                        Modifier
                            .graphicsLayer {
                                // Top card has default rotation plus drag rotation
                                transformOrigin = TransformOrigin(0.5f, 1f)
                                val actualDragRotation = if (isDragging) dragRotation else 0f
                                rotationZ =
                                    defaultRotation + actualDragRotation
                            }
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                            }
                            .offset {
                                if (isDragging) {
                                    IntOffset(
                                        topCardDragOffset.value.x.roundToInt(),
                                        topCardDragOffset.value.y.roundToInt()
                                    )
                                } else {
                                    IntOffset.Zero
                                }
                            }
                            .pointerInput(keyConfig(item)) {
                                detectDragGestures(
                                    onDragStart = { _ ->
                                        // Optional: handle drag start
                                        isDragging = true
                                    },
                                    onDragEnd = {
                                        settle(item)
                                    },
                                    onDragCancel = {
                                        settle(item)
                                    },
                                    onDrag = { change, dragAmount ->
                                        coroutineScope.launch {
                                            topCardDragOffset.snapTo(
                                                topCardDragOffset.value + dragAmount
                                            )
                                        }
                                        change.consume()
                                    }
                                )
                            }
                    } else {
                        //currentRotations[(keyConfig(item))] ?: 0f,


                        Modifier
                            .graphicsLayer {
                                // Apply default rotation to background cards
                                transformOrigin = TransformOrigin(0.5f, 1f)
                                rotationZ = defaultRotation
                            }
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                            }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .zIndex((items.size - index).toFloat())
                            .then(cardModifier),
                        contentAlignment = Alignment.Center
                    ) {
                        cardContent(item, index)
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 520)
@Composable
fun CardStackPreview(@PreviewParameter(CardStackPreviewProvider::class) data: List<CardData>) {
    val cardItems: PersistentList<CardData> = remember {
        data.toPersistentList()
    }

    // Generate default rotations for each card
    val defaultRotations = remember {
        List(cardItems.size) { index ->
            val multiplicationFactor = 4f
            when (index) {
                0 -> 0f
                1 -> -.5f * multiplicationFactor
                2 -> .7f * multiplicationFactor
                3 -> 1.2f * multiplicationFactor
                4 -> -1.2f * multiplicationFactor
                else -> 0f
            }
        }
    }

    SwipeAbleCardStack(
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .wrapContentSize(),
        items = cardItems,
        visibleCardCount = 5,
        keyConfig = { item -> item.title },
        thresholdConfig = { _, _ -> 0.2f },
        rotationsConfig = { index, _ -> defaultRotations[index] },
        onSwipe = { result ->
            Log.d("CardStack","Swiped ${result.direction} on card ${result.item}")
        }
    ) { item, _ ->
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.6f),
            shape = RoundedCornerShape(24.dp),
            elevation = 8.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(item.gradient),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
