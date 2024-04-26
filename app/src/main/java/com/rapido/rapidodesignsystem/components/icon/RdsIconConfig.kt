package com.rapido.rapidodesignsystem.components.icon

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

data class RdsIconConfig(
    val imageVector: ImageVector? = null,
    val painter: Painter? = null,
    @DrawableRes
    val svg: Int? = null,
    val contentDescription: String? = null,
    val modifier: Modifier = Modifier,
    val tintColor: Color? = null,
    val onClick: () -> Unit = {}
)

data class RdsIconDrawableConfig(
    @DrawableRes val drawable: Int,
    val contentDescription: String? = null,
    val modifier: Modifier = Modifier,
    val tintColor: Color? = null,
    val onClick: () -> Unit = {}
)

private const val RDS_ICON_CONFIG_VALIDATION_ERROR =
    "Invalid RdsIconConfig, please provide either imageVector or svg"

private fun RdsIconConfig.isValid(): Boolean {
    return ((this.imageVector != null && this.svg == null) || (this.imageVector == null && this.svg != null))
}

@Composable
fun RdsIconConfig.getIcon(): ImageVector {
    assert(this.isValid()) {
        RDS_ICON_CONFIG_VALIDATION_ERROR
    }
    lateinit var icon: ImageVector
    imageVector?.let {
        icon = it
    }
    svg?.let {
        icon = ImageVector.vectorResource(id = it)
    }

    return icon
}

@Composable
fun RdsIconConfig.getImagePainter(): Painter? {
    return painter
}
