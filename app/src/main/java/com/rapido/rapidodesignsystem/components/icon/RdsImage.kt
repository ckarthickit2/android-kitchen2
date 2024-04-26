package com.rapido.rapidodesignsystem.components.icon

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun RdsImage(
    path: String,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
    contentDescription: String? = null,
    errorPainter: Painter? = null
) {
    AsyncImage(
        model = path,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
        error = errorPainter
    )
}

@Composable
fun RdsImage(
    painter: Painter,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
    contentDescription: String? = null,
    colorFilter: ColorFilter? = null
) {
    Image(
        painter = painter,
        modifier = modifier,
        contentDescription = contentDescription,
        contentScale = contentScale,
        colorFilter = colorFilter
    )
}
