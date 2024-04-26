package com.rapido.rapidodesignsystem.components.bottomsheet

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.Alignment
import com.rapido.rapidodesignsystem.components.icon.RdsIconConfig

data class BottomSheetConfig(
    val header: BottomSheetHeader,
    val body: BottomSheetBody,
    val footer: BottomSheetFooter?,
    val closeOnClick: () -> Unit
)

data class BottomSheetHeader(
    @DrawableRes val image: Int,
)

data class BottomSheetBody(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @StringRes val btnText: Int,
    val contentAlignment: Alignment.Horizontal,
    val onClick: () -> Unit
)

data class BottomSheetFooter(
    @StringRes val btnText: Int,
    @StringRes val leadingIconConfig: RdsIconConfig?,
    @StringRes val trailingIconConfig: RdsIconConfig?,
    val onClick: () -> Unit
)
