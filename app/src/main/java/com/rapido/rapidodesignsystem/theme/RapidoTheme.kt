package com.rapido.rapidodesignsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.rapido.rapidodesignsystem.colors.RapidoThemeColors

object RapidoTheme {
    val colors: RapidoThemeColors
        @Composable
        @ReadOnlyComposable
        get() = RapidoLocalColors.current
}

val RapidoLocalColors = staticCompositionLocalOf { RapidoDefaultOrderColors }
