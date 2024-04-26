package com.rapido.rapidodesignsystem.theme

import com.rapido.rapidodesignsystem.colors.RapidoThemeColors
import com.rapido.rapidodesignsystem.utils.extension.toColorSafely

/**
 * @author [Jayesh Suthar](linkedin.com/in/jayeshsde)
 * @since 26/02/24.
 */
fun ThemeConfigDTO.toRapidoThemeColors(): RapidoThemeColors {
    return RapidoThemeColors(
        primary = primary.toColorSafely(),
        primaryContainer = primaryContainer.toColorSafely(),
        onPrimaryContainer = onPrimaryContainer.toColorSafely(),
        onPrimaryContainerVariant = onPrimaryContainerVariant.toColorSafely(),
        secondaryContainer = secondaryContainer.toColorSafely(),
        secondaryContainerOutline = secondaryContainerOutline.toColorSafely(),
        onSecondaryContainer = onSecondaryContainer.toColorSafely(),
        surface = surface.toColorSafely(),
        onSurface = onSurface.toColorSafely(),
        secondarySurface = secondarySurface.toColorSafely(),
        onSurfaceVariant = onSurfaceVariant.toColorSafely(),
        onSurfaceDimVariant = onSurfaceDimVariant.toColorSafely(),
        primaryDividerStyle = primaryDividerStyle
    )
}
