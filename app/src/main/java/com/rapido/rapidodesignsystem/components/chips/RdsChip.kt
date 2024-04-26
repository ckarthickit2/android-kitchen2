package com.rapido.rapidodesignsystem.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.components.icon.RdsIcon
import com.rapido.rapidodesignsystem.components.icon.RdsIconDrawableConfig
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import com.rapido.rapidodesignsystem.utils.extension.noRippleClickable

/**
 * Created by Abhishek Raj on 31/05/23.
 */

@Composable
fun RdsChip(
    modifier: Modifier,
    text: String,
    fillColor: Color = RdsColors.gray100,
    borderColor: Color = RdsColors.unspecified,
    borderWidth: Dp = 1.dp,
    type: RdsTextType? = null,
    leadingIconConfig: RdsIconDrawableConfig ? = null,
    trailingIconConfig: RdsIconDrawableConfig? = null,
    isSelectable: Boolean = false,
    selectedColor: Color = Color.Unspecified,
    isSelected: Boolean = false,
    textColor: Color = Color.Unspecified,
    onChecked: (Boolean) -> Unit,
    textModifier: Modifier = Modifier.padding(horizontal = 4.dp),
    maxLines: Int = Int.MAX_VALUE,
) {

    val shape = CircleShape
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentWidth()
            .padding(4.dp)
            .border(
                width = borderWidth,
                color = if (isSelectable) {
                    if (isSelected) fillColor else borderColor
                } else if (borderColor != RdsColors.unspecified) {
                    borderColor
                } else {
                    fillColor
                },
                shape = shape
            )
            .background(
                color = if (isSelectable) {
                    if (isSelected) selectedColor else fillColor
                } else {
                    fillColor
                },
                shape = shape
            )
            .clip(shape = shape)
            .noRippleClickable {
                onChecked(!isSelected)
            }
            .padding(4.dp)
    ) {
        leadingIconConfig?.let {
            RdsIcon(config = leadingIconConfig)
        }
        if (type != null) {
            RdsTextView(
                type = type,
                text = text,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = textModifier,
                maxLines = maxLines
            )
        } else {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                color = textColor,
                modifier = textModifier,
                maxLines = maxLines
            )
        }
        trailingIconConfig?.let {
            RdsIcon(config = trailingIconConfig)
        }
    }
}

@Preview
@Composable
fun PreviewRdsChip() {
    RdsChip(
        text = "Active",
        modifier = Modifier,
        onChecked = {},
        fillColor = RdsColors.greenDark1,
        textColor = RdsColors.white
    )
}
