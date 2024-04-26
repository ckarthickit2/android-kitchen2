package com.rapido.rapidodesignsystem.components.line

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

data class DashedShape(
    val dashWidth: Dp,
    val horizontal: Boolean = true
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(
        Path().apply {
            val stepPx = with(density) { dashWidth.toPx() }
            val stepsCount =
                if (horizontal)
                    (size.width / stepPx).roundToInt()
                else
                    (size.height / stepPx).roundToInt()
            val actualStep =
                if (horizontal)
                    size.width / stepsCount
                else
                    size.height / stepsCount
            val dotSize =
                if (horizontal)
                    Size(width = actualStep / 2, height = size.height)
                else
                    Size(width = size.width, height = actualStep / 2)
            for (i in 0 until stepsCount) {
                addRect(
                    Rect(
                        offset =
                        if (horizontal)
                            Offset(x = i * actualStep, y = 0f)
                        else
                            Offset(x = 0f, y = i * actualStep),
                        size = dotSize
                    )
                )
            }
            close()
        }
    )
}

@Preview
@Composable
fun ExampleDashedLinePreview() {
    VerticalDashedDivider(modifier = Modifier.height(100.dp), 1.dp, 50.dp, Color.Black)
}
