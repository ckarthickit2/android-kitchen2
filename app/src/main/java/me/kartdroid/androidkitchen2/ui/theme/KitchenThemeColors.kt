package me.kartdroid.androidkitchen2.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * @author [Karthick Chinnathambi]()
 * @since 01/09/23
 */


data class KitchenThemeColors(
        val primary: Color,
        val primaryContainer: ArrayList<Color>,
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
)