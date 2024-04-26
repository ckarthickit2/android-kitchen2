package com.rapido.rapidodesignsystem.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.components.icon.RdsIcon
import com.rapido.rapidodesignsystem.components.icon.RdsIconConfig
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import com.rapido.rapidodesignsystem.tokens.component.RdsButtonToken

@Composable
fun RdsSecondaryOutlinedButton(
    modifier: Modifier,
    text: String,
    contentPadding: PaddingValues = RdsButtonToken.ContentPadding,
    type: RdsTextType? = null,
    colors: ButtonColors = RdsButtonToken.outlinedSecondaryButtonColors(),
    border: BorderStroke = RdsButtonToken.outlinedBorder,
    leadingIconConfig: RdsIconConfig? = null,
    trailingIconConfig: RdsIconConfig? = null,
    enable: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        enabled = enable,
        shape = shape
    ) {
        if (leadingIconConfig != null) {
            RdsIcon(config = leadingIconConfig)
        }
        if (type != null) {
            RdsTextView(type = type, text = text)
        } else {
            Text(text = text)
        }
        if (trailingIconConfig != null) {
            RdsIcon(config = trailingIconConfig)
        }
    }
}

@Composable
fun RdsRoundedSecondaryOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.body1.copy(
        fontWeight = FontWeight.Bold,
        color = RdsColors.dark1,
        textAlign = TextAlign.Start
    ),
    contentPadding: PaddingValues = RdsButtonToken.ContentPadding,
    colors: ButtonColors = RdsButtonToken.outlinedSecondaryButtonColors(),
    border: BorderStroke = RdsButtonToken.outlinedBorder,
    enable: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minHeight = 48.dp),
        onClick = onClick,
        shape = RoundedCornerShape(50),
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        enabled = enable
    ) {
        Text(
            text = text,
            style = textStyle,
        )
    }
}

@Composable
fun RdsSecondaryOutlinedButton(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = RdsButtonToken.ContentPadding,
    colors: ButtonColors = RdsButtonToken.outlinedSecondaryButtonColors(),
    border: BorderStroke = RdsButtonToken.outlinedBorder,
    enable: Boolean = true,
    onClick: () -> Unit,
    shape: Shape = MaterialTheme.shapes.small,
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        enabled = enable,
        shape = shape
    ) {
        content()
    }
}

@Preview()
@Composable
fun PreviewSecondaryOutlinedButton() {
    RapidoTheme {
        RdsSecondaryOutlinedButton(modifier = Modifier, text = "Continue") {}
    }
}
