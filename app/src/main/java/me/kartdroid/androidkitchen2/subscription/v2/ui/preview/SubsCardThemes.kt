package me.kartdroid.androidkitchen2.subscription.v2.ui.preview

import androidx.compose.ui.graphics.Color
import com.rapido.rapidodesignsystem.theme.RapidoDefaultOrderColors
import com.rapido.rapidodesignsystem.tokens.base.RdsColors

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 28/04/24
 */

val greenTheme = RapidoDefaultOrderColors.copy(
    secondaryContainer = RdsColors.green2,
    onSecondaryContainer = RdsColors.white,
    onSurfaceVariant = RdsColors.greenDark500,
    onSurfaceDimVariant = RdsColors.green200,
    secondarySurface = RdsColors.greenLight,
)

val orangeTheme = RapidoDefaultOrderColors.copy(
    secondaryContainer = Color(0xFFD16329),
    onSecondaryContainer = RdsColors.white,
    onSurfaceVariant = Color(0xFFBF4D0C),
    onSurfaceDimVariant = Color(0xFFF4B68F),
    secondarySurface = Color(0xFFFFF6EF),
)

val redTheme = RapidoDefaultOrderColors.copy(
    secondaryContainer = Color(0xFFD03B2A),
    onSecondaryContainer = RdsColors.white,
    onSurfaceVariant = Color(0xFFAA2D1F),
    onSurfaceDimVariant = Color(0xFFF5A69D),
    secondarySurface = Color(0xFFFFF2F1),
)

val blueTheme = RapidoDefaultOrderColors.copy(
    secondaryContainer = Color(0xFF146EDC),
    onSecondaryContainer = RdsColors.white,
    onSurfaceVariant = Color(0xFF146EDC),
    onSurfaceDimVariant = Color(0xFFA0C8F9),
    secondarySurface = Color(0xFFEEF5FE),
)

val redBlueTheme = RapidoDefaultOrderColors.copy(
    secondaryContainer = Color(0xFFD03B2A),
    onSecondaryContainer = RdsColors.white,
    onSurfaceVariant = Color(0xFF146EDC),
    onSurfaceDimVariant = Color(0xFFA0C8F9),
    secondarySurface = Color(0xFFEEF5FE),
)