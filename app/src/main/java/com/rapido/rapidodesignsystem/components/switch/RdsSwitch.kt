package com.rapido.rapidodesignsystem.components.switch

import androidx.compose.material.Switch
import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RdsSwitch(
    modifier: Modifier,
    colors: SwitchColors = SwitchDefaults.colors(),
    onSwitchClicked: (isChecked: Boolean) -> Unit,
    isChecked: Boolean
) {
    Switch(
        modifier = modifier,
        checked = isChecked,
        colors = colors,
        onCheckedChange = {
            onSwitchClicked(!isChecked)
        }
    )
}
