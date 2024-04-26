package com.rapido.rapidodesignsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.rapido.rapidodesignsystem.components.icon.DynamicIconLoader
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoDefaultOrderColors
import com.rapido.rapidodesignsystem.theme.RapidoLocalColors
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.R

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 26/04/24
 */

@Composable
fun RdsRibbon(
        modifier: Modifier = Modifier,
        text: String,
        iconUrl: String,
        iconAssetsSearchPath: String,
        @DrawableRes fallbackDrawableRes: Int? = null,
) {
    Column(
            modifier = modifier,
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
    ) {
        Card(
                modifier = Modifier.zIndex(1f),
                shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp, topEnd = 8.dp),
                backgroundColor = RapidoTheme.colors.secondaryContainer,
                contentColor = RapidoTheme.colors.onSecondaryContainer,
                elevation = 3.dp
        ) {
            Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(start = 6.dp, top = 3.dp, end = 6.dp, bottom = 3.dp)
            ) {
                DynamicIconLoader(
                        iconPath = iconUrl,
                        modifier = Modifier.size(16.dp),
                        assetPath = iconAssetsSearchPath,
                        fallbackDrawableRes = fallbackDrawableRes,
                        colorFilter = ColorFilter.tint(RapidoTheme.colors.onSecondaryContainer)
                )
                RdsTextView(
                        text = text,
                        type = RdsTextType.Custom(
                                TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = RapidoTheme.colors.onSecondaryContainer
                                )
                        )
                )
            }
        }
        HalfTriangleShape(
                modifier = Modifier
                        .size(6.dp, 8.dp)
                        .offset(y = (-0.2).dp)
        )
    }
}

@Composable
fun HalfTriangleShape(modifier: Modifier = Modifier) {
    val fillColor = RapidoTheme.colors.secondaryContainer
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width, 0f)
            lineTo(0f, 0f)
            lineTo(0f, size.height)
            lineTo(size.width, 0f)
            close()
        }
        drawPath(path, fillColor)
    }
}


@Preview
@Composable
fun RDSRibbonPreview() {
    CompositionLocalProvider(
            RapidoLocalColors provides RapidoDefaultOrderColors.copy(
                    secondaryContainer = RdsColors.greenDark3,
                    onSecondaryContainer = RdsColors.white
            )
    ) {
        RdsRibbon(
                modifier = Modifier,
                text = "Active",
                "",
                iconAssetsSearchPath = "file:///android_asset/common-assets/",
                fallbackDrawableRes = R.drawable.ic_check_circle_green
        )
    }
}