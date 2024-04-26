package com.rapido.rapidodesignsystem.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.rapido.rapidodesignsystem.colors.RapidoThemeColors
import com.rapido.rapidodesignsystem.style.DividerStyle
import com.rapido.rapidodesignsystem.tokens.alias.RdsFoundation
import com.rapido.rapidodesignsystem.tokens.base.RdsColors

val LightColorScheme = lightColors(
    primary = RdsColors.yellow400,
    onPrimary = RdsColors.black,
    secondary = RdsColors.black,
    secondaryVariant = RdsColors.blueBase,
    onSecondary = RdsColors.white,
    background = RdsColors.gray98,
    onBackground = RdsFoundation.Content.primary,
    surface = RdsColors.white,
    onSurface = RdsColors.black,
    error = RdsFoundation.Color.negative,
    onError = RdsColors.white
)

// TODO: yet to defin dark colors
val DarkColorScheme = darkColors(
    primary = RdsColors.yellow400,
    onPrimary = RdsColors.yellow700,
    secondary = RdsColors.blue600,
    onSecondary = RdsColors.white,
    background = RdsColors.white,
    onBackground = RdsFoundation.Content.primary,
    surface = RdsColors.white,
    onSurface = RdsColors.black,
    error = RdsFoundation.Color.negative,
    onError = RdsColors.white
)

val RapidoDefaultOrderColors = RapidoThemeColors(
    primary = RdsColors.yellow400,
    primaryContainer = arrayListOf(Color.White, Color.White),
    onPrimaryContainer = RdsColors.dark1,
    onPrimaryContainerVariant = RdsColors.greenBase,
    secondaryContainer = RdsColors.contentPrimary,
    secondaryContainerOutline = RdsColors.gray_400,
    onSecondaryContainer = RdsColors.transparent,
    surface = RdsColors.white,
    secondarySurface = RdsColors.white,
    onSurface = RdsColors.dark1,
    onSurfaceVariant = RdsColors.dark3,
    onSurfaceDimVariant = RdsColors.dark2,
    primaryDividerStyle = DividerStyle.SOLID
)
