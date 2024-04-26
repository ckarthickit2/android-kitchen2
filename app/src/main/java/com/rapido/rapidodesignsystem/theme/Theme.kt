package com.rapido.rapidodesignsystem.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.fragment.app.DialogFragment
import com.rapido.rapidodesignsystem.utils.extension.getActivity

// TODO: brand colors are not yet added in the foundation, added some dummy values for now

@Composable
fun RapidoTheme(
    overrideWindowInsetsWithLightColors: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode && overrideWindowInsetsWithLightColors) {
        SideEffect {
            val window = view.context.getActivity()?.window
            val insets = window?.let { _window ->
                _window.statusBarColor = Color.Transparent.toArgb()
                _window.navigationBarColor = Color.Transparent.toArgb()
                WindowCompat.getInsetsController(_window, view)
            }
            insets?.isAppearanceLightStatusBars = true
            insets?.isAppearanceLightNavigationBars = true
        }
    }

    MaterialTheme(
        colors = colorScheme,
        content = content,
        typography = RdsTypography,
        shapes = RdsShapes
    )
}

/** DEV-NOTE:
 * Since activity and Dialog has different window
 * We'd ideally want to change Dialog's window to show dialog properly not activity's window
 *
 * Status bar colors and all won't change if we use RapidoTheme to show Dialog **/
@Composable
fun DialogFragment.RapidoDialogTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = dialog?.window
            val insets = window?.run {
                statusBarColor = Color.Transparent.toArgb()
                navigationBarColor = Color.Transparent.toArgb()
                WindowCompat.getInsetsController(this, view)
            }
            insets?.isAppearanceLightStatusBars = true
            insets?.isAppearanceLightNavigationBars = true
        }
    }

    MaterialTheme(
        colors = colorScheme,
        content = content,
        typography = RdsTypography,
        shapes = RdsShapes
    )
}
