package com.rapido.rapidodesignsystem.colors

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
data class RapidoThemeColors(
    val primary: Color,
    val primaryContainer: List<Color>,
    val onPrimaryContainer: Color,
    val onPrimaryContainerVariant: Color,
    val secondaryContainer: Color,
    val secondaryContainerOutline: Color,
    val onSecondaryContainer: Color,
    val surface: Color,
    val secondarySurface: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
    val onSurfaceDimVariant: Color,
    val primaryDividerStyle: String,
) {
    companion object {
        val EMPTY = RapidoThemeColors(
            primary = Color.Unspecified,
            primaryContainer = emptyList(),
            onPrimaryContainer = Color.Unspecified,
            onPrimaryContainerVariant = Color.Unspecified,
            secondaryContainer = Color.Unspecified,
            secondaryContainerOutline = Color.Unspecified,
            onSecondaryContainer = Color.Unspecified,
            surface = Color.Unspecified,
            secondarySurface = Color.Unspecified,
            onSurface = Color.Unspecified,
            onSurfaceVariant = Color.Unspecified,
            onSurfaceDimVariant = Color.Unspecified,
            primaryDividerStyle = ""
        )
    }
}
