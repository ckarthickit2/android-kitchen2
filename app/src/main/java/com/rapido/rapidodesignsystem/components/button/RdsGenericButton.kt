package com.rapido.rapidodesignsystem.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.rapido.rapidodesignsystem.components.icon.RdsIcon
import com.rapido.rapidodesignsystem.components.icon.RdsIconConfig
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import com.rapido.rapidodesignsystem.tokens.component.RdsButtonToken

@Composable
fun RdsGenericButton(
    modifier: Modifier,
    text: String,
    colors: ButtonColors,
    contentPadding: PaddingValues = RdsButtonToken.ContentPadding,
    type: RdsTextType? = null,
    leadingIconConfig: RdsIconConfig? = null,
    trailingIconConfig: RdsIconConfig? = null,
    enable: Boolean = true,
    textColor: Color = Color.Unspecified,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = colors,
        contentPadding = contentPadding,
        enabled = enable
    ) {
        if (leadingIconConfig != null) {
            RdsIcon(config = leadingIconConfig)
        }
        if (type != null) {
            RdsTextView(type = type, text = text, color = textColor)
        } else {
            Text(text = text, color = textColor)
        }
        if (trailingIconConfig != null) {
            RdsIcon(config = trailingIconConfig)
        }
    }
}

@Preview
@Composable
fun PreviewRdsGenericButton() {
    RapidoTheme {
        RdsGenericButton(
            modifier = Modifier,
            text = "Continue",
            colors = RdsButtonColors(
                backgroundColor = RdsColors.blueBase,
                contentColor = RdsColors.white,
                disabledBackgroundColor = RdsColors.blueBase.copy(alpha = 0.12f),
                disabledContentColor = RdsColors.white.copy(alpha = 0.12f)
            ),
            trailingIconConfig = RdsIconConfig(
                imageVector = Icons.Default.ArrowForward
            )
        ) {}
    }
}
