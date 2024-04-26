package com.rapido.rapidodesignsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp

@Composable
fun ReducedHeightComposable(reducedHeight: Dp, content: @Composable () -> Unit) {
    Layout(
        content = { content() },
        measurePolicy = { measurables, constraints ->
            val placeable = measurables.first().measure(constraints)
            val desiredHeight = placeable.height - reducedHeight.roundToPx()
            layout(placeable.width, desiredHeight) {
                placeable.place(0, 0)
            }
        }
    )
}
