package com.rapido.rapidodesignsystem.tokens.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.components.button.RdsButtonColors
import com.rapido.rapidodesignsystem.tokens.base.RdsColors

object RdsButtonToken {
    private val ButtonHorizontalPadding = 24.dp
    private val ButtonVerticalPadding = 8.dp

    val ContentPadding = PaddingValues(
        start = ButtonHorizontalPadding,
        top = ButtonVerticalPadding,
        end = ButtonHorizontalPadding,
        bottom = ButtonVerticalPadding
    )

    @Composable
    fun Elevation(): ButtonElevation = ButtonDefaults.elevation(defaultElevation = 0.dp)

    val outlinedBorder: BorderStroke
        @Composable
        get() = BorderStroke(
            ButtonDefaults.OutlinedBorderSize, MaterialTheme.colors.onSurface
        )

    val secondaryVariantBorder: BorderStroke
        @Composable
        get() = BorderStroke(
            ButtonDefaults.OutlinedBorderSize, MaterialTheme.colors.secondaryVariant
        )

    val outlinedWarningBorder: BorderStroke
        @Composable
        get() = BorderStroke(
            ButtonDefaults.OutlinedBorderSize, RdsColors.redBase
        )

    @Composable
    fun secondaryButtonColors(
        backgroundColor: Color = MaterialTheme.colors.secondary,
        contentColor: Color = contentColorFor(backgroundColor),
        disabledBackgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
            .compositeOver(MaterialTheme.colors.surface),
        disabledContentColor: Color = MaterialTheme.colors.onSurface
            .copy(alpha = ContentAlpha.disabled)
    ): ButtonColors = RdsButtonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = disabledBackgroundColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun outlinedSecondaryButtonColors(
        backgroundColor: Color = MaterialTheme.colors.surface,
        contentColor: Color = MaterialTheme.colors.secondary,
        disabledContentColor: Color = MaterialTheme.colors.onSurface
            .copy(alpha = ContentAlpha.disabled)
    ): ButtonColors = RdsButtonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = backgroundColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun textSecondaryButtonColors(
        backgroundColor: Color = Color.Transparent,
        contentColor: Color = MaterialTheme.colors.secondaryVariant,
        disabledContentColor: Color = MaterialTheme.colors.onSurface
            .copy(alpha = ContentAlpha.disabled)
    ): ButtonColors = RdsButtonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = backgroundColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun secondaryVariantButtonColors(
        backgroundColor: Color = MaterialTheme.colors.surface,
        contentColor: Color = MaterialTheme.colors.secondaryVariant,
        disabledBackgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
            .compositeOver(MaterialTheme.colors.surface),
        disabledContentColor: Color = MaterialTheme.colors.onSurface
            .copy(alpha = ContentAlpha.disabled)
    ): ButtonColors = RdsButtonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = disabledBackgroundColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun outlinedWarningButtonColors(
        backgroundColor: Color = MaterialTheme.colors.surface,
        contentColor: Color = RdsColors.redBase,
        disabledContentColor: Color = MaterialTheme.colors.onSurface
            .copy(alpha = ContentAlpha.disabled)
    ): ButtonColors = RdsButtonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = backgroundColor,
        disabledContentColor = disabledContentColor
    )
}
