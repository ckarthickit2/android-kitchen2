package com.rapido.rapidodesignsystem.tokens.component

import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwitchColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import com.rapido.rapidodesignsystem.components.switch.RdsSwitchColors
import com.rapido.rapidodesignsystem.tokens.base.RdsColors

object RdsSwitchToken {

    @Composable
    fun getOnDutySwitchColors(
        checkedThumbColor: Color = RdsColors.green5,
        uncheckedThumbColor: Color = RdsColors.white,
        uncheckedTrackColor: Color = RdsColors.neutrals4,
        checkedTrackColor: Color = checkedThumbColor,
        checkedTrackAlpha: Float = 0.54f,
        uncheckedTrackAlpha: Float = 0.38f,
        disabledCheckedThumbColor: Color = checkedThumbColor
            .copy(alpha = ContentAlpha.disabled)
            .compositeOver(MaterialTheme.colors.surface),
        disabledCheckedTrackColor: Color = checkedTrackColor
            .copy(alpha = ContentAlpha.disabled)
            .compositeOver(MaterialTheme.colors.surface),
        disabledUncheckedThumbColor: Color = uncheckedThumbColor
            .copy(alpha = ContentAlpha.disabled)
            .compositeOver(MaterialTheme.colors.surface),
        disabledUncheckedTrackColor: Color = uncheckedTrackColor
            .copy(alpha = ContentAlpha.disabled)
            .compositeOver(MaterialTheme.colors.surface)
    ): SwitchColors = RdsSwitchColors(
        checkedThumbColor = checkedThumbColor,
        checkedTrackColor = checkedTrackColor.copy(alpha = checkedTrackAlpha),
        uncheckedThumbColor = uncheckedThumbColor,
        uncheckedTrackColor = uncheckedTrackColor.copy(alpha = uncheckedTrackAlpha),
        disabledCheckedThumbColor = disabledCheckedThumbColor,
        disabledCheckedTrackColor = disabledCheckedTrackColor.copy(alpha = checkedTrackAlpha),
        disabledUncheckedThumbColor = disabledUncheckedThumbColor,
        disabledUncheckedTrackColor = disabledUncheckedTrackColor.copy(alpha = uncheckedTrackAlpha)
    )
}
