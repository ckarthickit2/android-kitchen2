package me.kartdroid.androidkitchen2.components.preview

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.toPersistentList
import me.kartdroid.androidkitchen2.components.CardData

class CardStackPreviewProvider : PreviewParameterProvider<List<CardData>> {
    override val values: Sequence<List<CardData>> = sequenceOf(
        List(10) { index ->
            CardData(
                title = "New Card $index",
                gradient = getRandomGradient()
            )
        }.toPersistentList(),
        List(1) { index ->
            CardData(
                title = "New Card $index",
                gradient = getRandomGradient()
            )
        }.toPersistentList(),
        List(2) { index ->
            CardData(
                title = "New Card $index",
                gradient = getRandomGradient()
            )
        }.toPersistentList(),
        List(3) { index ->
            CardData(
                title = "New Card $index",
                gradient = getRandomGradient()
            )
        }.toPersistentList(),
        List(4) { index ->
            CardData(
                title = "New Card $index",
                gradient = getRandomGradient()
            )
        }.toPersistentList(),
        List(5) { index ->
            CardData(
                title = "New Card $index",
                gradient = getRandomGradient()
            )
        }.toPersistentList(),
    )
}

private fun getRandomGradient(): Brush {
    val color1 = Color(
        red = (Math.random() * 0.7f + 0.3f).toFloat(),
        green = (Math.random() * 0.7f + 0.3f).toFloat(),
        blue = (Math.random() * 0.7f + 0.3f).toFloat()
    )

    val color2 = Color(
        red = (Math.random() * 0.7f + 0.3f).toFloat(),
        green = (Math.random() * 0.7f + 0.3f).toFloat(),
        blue = (Math.random() * 0.7f + 0.3f).toFloat()
    )

    return Brush.linearGradient(listOf(color1, color2))
}