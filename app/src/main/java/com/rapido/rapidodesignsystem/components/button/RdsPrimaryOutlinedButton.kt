package com.rapido.rapidodesignsystem.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.component.RdsButtonToken

@Composable
fun RdsPrimaryOutlinedButton(
    modifier: Modifier,
    text: String,
    contentPadding: PaddingValues = RdsButtonToken.ContentPadding,
    enable: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        contentPadding = contentPadding,
        enabled = enable
    ) {
        Text(text = text)
    }
}

@Preview()
@Composable
fun PreviewPrimaryOutlinedButton() {
    RapidoTheme {
        RdsPrimaryOutlinedButton(modifier = Modifier, text = "Continue") {}
    }
}
