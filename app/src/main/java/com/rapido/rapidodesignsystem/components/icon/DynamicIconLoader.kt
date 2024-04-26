package com.rapido.rapidodesignsystem.components.icon

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.kartdroid.androidkitchen2.R

@Composable
fun DynamicIconLoader(
    iconPath: String,
    modifier: Modifier,
    assetPath: String,
    @DrawableRes fallbackDrawableRes: Int? = null,
    colorFilter: ColorFilter? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    val resolvedIconUri = remember(iconPath) {
        val assetPrefix = "asset://"
        if (iconPath.startsWith(assetPrefix)) {
            val iconName = iconPath.removePrefix(assetPrefix)
            assetPath + iconName
        } else {
            iconPath
        }
    }

    if (LocalInspectionMode.current) {
        Image(
            painter = painterResource(fallbackDrawableRes ?: R.drawable.ic_circular_shape_black),
            contentDescription = null,
            modifier = modifier,
            colorFilter = colorFilter,
            contentScale = contentScale
        )
    } else {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).apply {
                data(resolvedIconUri)
                if (fallbackDrawableRes != null) {
                    error(fallbackDrawableRes)
                }
            }.build(),
            contentDescription = null,
            modifier = modifier,
            colorFilter = colorFilter,
            contentScale = contentScale
        )
    }
}
