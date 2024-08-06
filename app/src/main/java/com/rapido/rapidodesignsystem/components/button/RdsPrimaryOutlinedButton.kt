package com.rapido.rapidodesignsystem.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
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
    Surface {
        RapidoTheme {
            RdsPrimaryOutlinedButton(
                modifier = Modifier.padding(4.dp),
                text = "Continue",
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun OutineButtonText() {
    Surface {
        OutlinedButton(
            modifier = Modifier
                .background(color = RdsColors.redBase),
            onClick = {  },
            shape = RoundedCornerShape(50),
            border = BorderStroke(width = ButtonDefaults.OutlinedBorderSize, brush = SolidColor(RdsColors.white)),
            colors = RdsButtonColors(
                backgroundColor = RdsColors.redBase,
                contentColor = RdsColors.white,
                disabledBackgroundColor = RdsColors.redBase.copy(alpha = 0.12f),
                disabledContentColor = RdsColors.white.copy(alpha = 0.12f)

            ),
            contentPadding =  PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            enabled = true
        ) {
            Text(text = "Bad")
            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "")
        }
    }
}
