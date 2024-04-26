package com.rapido.rapidodesignsystem.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.rapido.rapidodesignsystem.components.icon.RdsIcon
import com.rapido.rapidodesignsystem.components.icon.RdsIconConfig
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.component.RdsButtonToken

@Composable
fun RdsSecondaryVariantButton(
    modifier: Modifier,
    text: String,
    contentPadding: PaddingValues = RdsButtonToken.ContentPadding,
    type: RdsTextType? = null,
    leadingIconConfig: RdsIconConfig? = null,
    trailingIconConfig: RdsIconConfig? = null,
    enable: Boolean = true,
    color: Color = Color.Unspecified,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = RdsButtonToken.secondaryVariantButtonColors(),
        contentPadding = contentPadding,
        enabled = enable
    ) {
        if (leadingIconConfig != null) {
            RdsIcon(config = leadingIconConfig)
        }
        if (type != null) {
            RdsTextView(type = type, text = text, color = color)
        } else {
            Text(text = text, color = color)
        }
        if (trailingIconConfig != null) {
            RdsIcon(config = trailingIconConfig)
        }
    }
}

@Preview
@Composable
fun PreviewRdsSecondaryVariantButton() {
    RapidoTheme {
        RdsSecondaryButton(modifier = Modifier, text = "Continue") {}
    }
}
