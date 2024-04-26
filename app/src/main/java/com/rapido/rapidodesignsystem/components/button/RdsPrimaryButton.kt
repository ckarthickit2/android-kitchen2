package com.rapido.rapidodesignsystem.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import com.rapido.rapidodesignsystem.tokens.component.RdsButtonToken

@Composable
fun RdsPrimaryButton(
    modifier: Modifier,
    text: String,
    maxLines: Int = Int.MAX_VALUE,
    contentPadding: PaddingValues = RdsButtonToken.ContentPadding,
    elevation: ButtonElevation = RdsButtonToken.Elevation(),
    type: RdsTextType? = null,
    enable: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = contentPadding,
        elevation = elevation,
        enabled = enable,
        shape = shape
    ) {
        if (type != null) {
            RdsTextView(
                type = type,
                text = text,
                textAlign = TextAlign.Center,
                maxLines = maxLines
            )
        } else {
            Text(text = text, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun RdsPrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    maxLines: Int = Int.MAX_VALUE,
    contentPadding: PaddingValues = RdsButtonToken.ContentPadding,
    elevation: ButtonElevation = RdsButtonToken.Elevation(),
    enable: Boolean = true,
    shape: Shape = RoundedCornerShape(percent = 50),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 48.dp),
        enabled = enable,
        elevation = elevation,
        shape = shape,
        contentPadding = contentPadding
    ) {
        Text(
            text = text,
            color = RdsColors.dark1,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            maxLines = maxLines
        )
    }
}

@Composable
fun RdsPrimaryButton(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = RdsButtonToken.ContentPadding,
    elevation: ButtonElevation = RdsButtonToken.Elevation(),
    enable: Boolean = true,
    shape: Shape = RoundedCornerShape(percent = 50),
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 48.dp),
        enabled = enable,
        elevation = elevation,
        shape = shape,
        contentPadding = contentPadding,
        content = content
    )
}

@Preview()
@Composable
fun PreviewPrimaryButton() {
    RapidoTheme {
        RdsPrimaryButton(modifier = Modifier, text = "Continue") {}
    }
}
