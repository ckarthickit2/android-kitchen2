package com.rapido.rapidodesignsystem.components.progress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors

@Composable
fun SegmentedProgressBar(
    modifier: Modifier = Modifier,
    totalCount: Int,
    progressedCount: Int,
    progressedSegmentColor: Color,
    remainingSegmentColor: Color,
    gapWidth: Dp,
    cornerRadius: CornerRadius
) {
    Canvas(
        modifier = modifier,
        onDraw = {
            val totalGapWidth = gapWidth.toPx() * (totalCount - 1)
            val segmentWidth = (this.size.width - totalGapWidth) / totalCount

            repeat(totalCount) { index ->
                val startX = index * (segmentWidth + gapWidth.toPx())
                drawRoundRect(
                    color = if (index < progressedCount) progressedSegmentColor else remainingSegmentColor,
                    topLeft = Offset(x = startX, y = 0f),
                    size = Size(width = segmentWidth, height = this.size.height),
                    cornerRadius = cornerRadius
                )
            }
        }
    )
}

@Composable
@Preview
fun SegmentedProgressBarPreview() {
    RapidoTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = RdsColors.white)
                .padding(16.dp)
        ) {
            SegmentedProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp),
                totalCount = 10,
                progressedCount = 3,
                progressedSegmentColor = RdsColors.blueBase,
                remainingSegmentColor = RdsColors.blueLight1,
                gapWidth = 4.dp,
                cornerRadius = with(LocalDensity.current) { CornerRadius(4.dp.toPx(), 4.dp.toPx()) }
            )
        }
    }
}
