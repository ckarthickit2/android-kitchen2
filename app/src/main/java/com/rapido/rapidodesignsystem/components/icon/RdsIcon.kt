package com.rapido.rapidodesignsystem.components.icon

import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun RdsIcon(config: RdsIconConfig) {
    if (config.painter != null) {
        Icon(
            painter = config.painter,
            contentDescription = config.contentDescription,
            tint = config.tintColor
                ?: LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
            modifier = config.modifier
        )
    } else {
        Icon(
            imageVector = config.getIcon(),
            contentDescription = config.contentDescription,
            tint = config.tintColor
                ?: LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
            modifier = config.modifier
        )
    }
}

@Composable
fun RdsIcon(config: RdsIconDrawableConfig) {
    Icon(
        painter = painterResource(id = config.drawable),
        contentDescription = config.contentDescription,
        tint = config.tintColor ?: Color.Unspecified,
        modifier = config.modifier
    )
}
