package com.rapido.rapidodesignsystem.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rapido.rapidodesignsystem.components.icon.RdsIcon
import com.rapido.rapidodesignsystem.components.icon.RdsIconConfig
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.component.RdsButtonToken

@Composable
fun RdsSecondaryTextButton(
    modifier: Modifier = Modifier,
    text: String,
    type: RdsTextType? = null,
    leadingIconConfig: RdsIconConfig? = null,
    trailingIconConfig: RdsIconConfig? = null,
    contentPadding: PaddingValues = RdsButtonToken.ContentPadding,
    enable: Boolean = true,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        contentPadding = contentPadding,
        colors = RdsButtonToken.textSecondaryButtonColors(),
        enabled = enable
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

@Preview()
@Composable
fun PreviewRdsTextButton() {
    RapidoTheme {
        RdsSecondaryTextButton(
            modifier = Modifier,
            text = "Continue",
            leadingIconConfig = RdsIconConfig(
                imageVector = Icons.Outlined.Call,
            ),
            trailingIconConfig = RdsIconConfig(
                imageVector = Icons.Outlined.Call,
            )
        ) {}
    }
}
