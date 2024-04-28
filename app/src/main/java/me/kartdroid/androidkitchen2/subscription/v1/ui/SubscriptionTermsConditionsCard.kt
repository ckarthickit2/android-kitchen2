package com.rapido.rider.subscriptions.presentation.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.R

/**
 * Created by Abhishek Raj on 08/06/23.
 */

@Composable
fun TnCTitle(
    modifier: Modifier = Modifier
) {
    RdsTextView(
        modifier = modifier,
        text = stringResource(id = R.string.tnc),
        style = RdsTextType.LabelSmall.typography.copy(
            color = RdsColors.dark2,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 16.sp
        ),
    )
}

@Composable
fun TnCInfo(item: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        RdsTextView(text = "\u2022", type = RdsTextType.BodySmall, modifier = Modifier.padding(start = 8.dp))
        RdsTextView(
            text = item,
            style = RdsTextType.TitleMedium.typography.copy(
                color = RdsColors.dark2,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                lineHeight = 16.sp
            ),
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            color = RdsColors.dark2
        )
    }
}

@Preview(device = Devices.PIXEL_2_XL, showSystemUi = true)
@Composable
fun PreviewSubscriptionTermsConditionsCard() {
    RapidoTheme {
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            TnCTitle(modifier = Modifier.padding(horizontal = 24.dp))
            val tnCTexts = listOf(
                "GST will be charged on plan purchases",
                "No refunds will be given once plan is purchased",
                "Captain will not receive orders until there is an active recharge plan",
                "Plan is active for cabs only",
                "Expiry date is applicable for all plans"
            )
            Column(modifier = Modifier) {
                tnCTexts.forEach { item ->
                    TnCInfo(
                        modifier = Modifier.padding(start = 24.dp, top = 8.dp, end = 24.dp),
                        item = item
                    )
                }
            }
        }
    }
}
