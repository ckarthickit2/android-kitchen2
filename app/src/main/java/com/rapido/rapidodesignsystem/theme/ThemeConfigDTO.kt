package com.rapido.rapidodesignsystem.theme

import kotlinx.serialization.Serializable

/**
 * @author [Jayesh Suthar](linkedin.com/in/jayeshsde)
 * @since 26/02/24.
 */
@Serializable
class ThemeConfigDTO(
    val primary: String = "",
    val primaryContainer: List<String> = emptyList(),
    val onPrimaryContainer: String = "",
    val onPrimaryContainerVariant: String = "",
    val secondaryContainer: String = "",
    val secondaryContainerOutline: String = "",
    val onSecondaryContainer: String = "",
    val surface: String = "",
    val secondarySurface: String = "",
    val onSurface: String = "",
    val onSurfaceVariant: String = "",
    val onSurfaceDimVariant: String = "",
    val primaryDividerStyle: String = ""
)
