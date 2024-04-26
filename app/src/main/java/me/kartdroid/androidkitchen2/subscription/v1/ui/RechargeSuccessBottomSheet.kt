package com.rapido.rider.subscriptions.presentation.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.rapidodesignsystem.components.button.RdsButtonColors
import com.rapido.rapidodesignsystem.components.button.RdsGenericButton
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.components.text.TextSize
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors

/**
 * Created by Abhishek Raj on 15/06/23.
 */

@Composable
fun RechargeSuccessBottomSheet(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    description: String,
    onCtaClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Image(
                    modifier = Modifier
                        .padding(end = 16.dp, bottom = 16.dp)
                        .size(69.dp),
                    painter = painterResource(id = me.kartdroid.androidkitchen2.R.drawable.ic_check_success),
                    contentDescription = null
                )
            }
            RdsTextView(
                modifier = Modifier.padding(top = 12.dp),
                style = RdsTextType.TitleLarge.typography.copy(
                    color = RdsColors.greenDark1,
                    fontSize = 22.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Bold,
                ),
                text = title,
                textAlign = TextAlign.Center
            )
            RdsTextView(
                modifier = Modifier.padding(top = 12.dp),
                style = RdsTextType.BodyLarge.typography.copy(
                    color = RdsColors.dark2,
                    fontSize = 19.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Medium
                ),
                text = subTitle,
                textAlign = TextAlign.Center
            )
            if (description.isNotEmpty()) {
                RdsTextView(
                    modifier = Modifier.padding(top = 12.dp),
                    style = RdsTextType.BodyLarge.typography.copy(
                        color = RdsColors.dark2,
                        fontSize = 19.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    text = description,
                    textAlign = TextAlign.Center
                )
            }
            RdsGenericButton(
                modifier = Modifier.padding(top = 24.dp).fillMaxWidth().height(40.dp),
                text = stringResource(id = me.kartdroid.androidkitchen2.R.string.ok),
                type = RdsTextType.Custom(
                    RdsTextType.TitleMedium.typography.copy(
                        fontSize = 17.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    customTextSize = TextSize.Medium
                ),
                colors = RdsButtonColors(
                    backgroundColor = RdsColors.buttonColorYellow,
                    contentColor = RdsColors.dark1,
                    disabledBackgroundColor = RdsColors.dark2.copy(alpha = 0.12f),
                    disabledContentColor = RdsColors.dark1.copy(alpha = 0.12f)
                ),
                onClick = {
                    onCtaClick()
                }
            )
        }
    }
}

@Preview(device = Devices.PIXEL_2_XL, showSystemUi = true)
@Composable
fun PreviewRechargeSuccessBottomSheet() {
    RapidoTheme {
        RechargeSuccessBottomSheet(
            modifier = Modifier,
            title = "₹XX Recharge successful Message",
            subTitle = "From 4 - 12 Jun",
            description = "Incentives may not be applicable",
            onCtaClick = {}
        )
    }
}
