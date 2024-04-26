package com.rapido.rapidodesignsystem.components.line

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDashedDivider(modifier: Modifier, thickness: Dp, dashWidth: Dp, color: Color) {
    Box(
        modifier
            .height(thickness)
            .background(color, shape = DashedShape(dashWidth = dashWidth))
    )
}

@Composable
fun VerticalDashedDivider(modifier: Modifier, width: Dp, dashWidth: Dp, color: Color) {
    Box(
        modifier
            .width(width)
            .background(color, shape = DashedShape(dashWidth = dashWidth, false))
    )
}

@Preview
@Composable
fun PreviewLines() {
    HorizontalDashedDivider(modifier = Modifier.width(100.dp), 1.dp, 20.dp, Color.Black)
    VerticalDashedDivider(modifier = Modifier.height(100.dp), 1.dp, 20.dp, Color.Black)
}
