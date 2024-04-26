package com.rapido.rapidodesignsystem.components.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.rapidodesignsystem.components.button.RdsPrimaryButton
import com.rapido.rapidodesignsystem.components.button.RdsRoundedSecondaryOutlinedButton
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.R

/**
 * @author [Jayesh Suthar](linkedin.com/in/jayeshsde)
 * @since 27/08/23.
 */
@Composable
fun RdsGenericBottomSheet(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    icon: Painter? = null,
    iconTint: Color = Color.Unspecified,
    primaryButtonText: String,
    onClickPrimaryButton: () -> Unit,
    secondaryButtonText: String? = null,
    onClickSecondaryButton: (() -> Unit)? = null,
    onDismiss: () -> Unit
) {
    DefaultBottomSheetContainer(
        modifier = modifier,
        onClose = onDismiss
    ) {
        Column {
            if (icon != null) {
                Icon(
                    painter = icon,
                    contentDescription = "sheet icon",
                    modifier = Modifier.size(112.dp),
                    tint = iconTint
                )
                Spacer(
                    modifier = Modifier.padding(top = 24.dp)
                )
            }
            RdsTextView(
                text = title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.4.sp,
                    color = RdsColors.dark1
                )
            )
            if (description != null) {
                Spacer(
                    modifier = Modifier.padding(top = 12.dp)
                )
                RdsTextView(
                    type = RdsTextType.BodyLarge,
                    text = description,
                    color = RdsColors.neutrals9,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(
                modifier = Modifier.padding(top = 24.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                RdsPrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = primaryButtonText,
                    onClick = onClickPrimaryButton
                )
                if (secondaryButtonText != null && onClickSecondaryButton != null) {
                    RdsRoundedSecondaryOutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = secondaryButtonText,
                        onClick = onClickSecondaryButton
                    )
                }
            }
        }
    }
}

@Preview(backgroundColor = 0xffeeeeee)
@Composable
fun PreviewRdsGenericBottomSheet() {
    RapidoTheme {
        RdsGenericBottomSheet(
            modifier = Modifier.padding(8.dp),
            title = stringResource(id = R.string.not_getting_orders_caps),
            description = stringResource(id = R.string.now_you_can_do),
            icon = painterResource(id = R.drawable.ic_default_rider),
            primaryButtonText = stringResource(id = R.string.check_now),
            secondaryButtonText = stringResource(id = R.string.ok),
            onClickPrimaryButton = {},
            onClickSecondaryButton = {},
            onDismiss = {}
        )
    }
}
