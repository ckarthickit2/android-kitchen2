package com.rapido.rapidodesignsystem.components.circle

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.utils.toPx
import kotlin.math.roundToInt

@Composable
fun DashedCircleShape(radius: Dp, color: Color, strokeWidth: Float) {
    val stroke = Stroke(
        width = strokeWidth,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

    Box {
        Canvas(modifier = Modifier.size(radius.times(2))) {
            drawCircle(
                color = color,
                style = stroke,
                radius = radius.value.roundToInt().toPx
            )
        }
    }
}

@Preview
@Composable
fun exampleDashedCircle() {
    DashedCircleShape(radius = 10.dp, color = Color.Red, strokeWidth = 2f)
}
