package me.kartdroid.androidkitchen2.subscription.v1.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.rapidodesignsystem.components.button.RdsButtonColors
import com.rapido.rapidodesignsystem.components.button.RdsGenericButton
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.components.text.TextSize
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfo
import me.kartdroid.androidkitchen2.ui.theme.AndroidKitchen2Theme
import me.kartdroid.androidkitchen2.utils.toFormattedString
import java.math.BigDecimal

/**
 * Created by Abhishek Raj on 01/06/23.
 */

@Composable
fun SubscriptionCtaCard(
        modifier: Modifier = Modifier,
        subscription: SubscriptionInfo.Subscription,
        extraInfo: SubscriptionInfo.ExtraInfo,
        onBuyClick: (SubscriptionInfo.Subscription) -> Unit,
        ctaText: String
) {
    Surface(
        modifier = modifier
            .wrapContentHeight(),
        elevation = 12.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                val annotatedString = buildAnnotatedString {
                    val placeholder = "₹%"
                    val placeholderIndex = stringResource(id = R.string.pay_amount).indexOf(placeholder)
                    val payAmount = subscription.purchasePrice.toFormattedString()
                    RdsTextType.TitleLarge.typography
                    append(stringResource(id = R.string.pay_amount, payAmount))
                    addStyle(SpanStyle(RdsColors.greenBase, fontSize = 24.sp), placeholderIndex, placeholderIndex + payAmount.toString().length + 1) // Add 1 because end offset is exclusive
                }

                RdsTextView(
                    text = annotatedString,
                    type = RdsTextType.HeadlineSmall
                )

                val gstFormattedString = if (extraInfo.gstPercentage > 0) {
                    stringResource(R.string.inclusive_of_gst, "${extraInfo.gstPercentage.toFormattedString()}%")
                } else {
                    ""
                }
                if (gstFormattedString.isNotBlank()) {
                    RdsTextView(
                        text = gstFormattedString,
                        style = RdsTextType.BodySmall.typography.copy(
                            color = RdsColors.dark3,
                            fontSize = 14.sp,
                            lineHeight = 19.sp
                        )
                    )
                }
            }
            RdsGenericButton(
                modifier = Modifier
                    .height(48.dp),
                text = ctaText.ifBlank { stringResource(id = R.string.recharge_txt) },
                type = RdsTextType.Custom(
                    textStyle = RdsTextType.TitleMedium.typography.copy(
                        color = com.rapido.rapidodesignsystem.tokens.base.RdsColors.dark1,
                        fontSize = 17.sp,
                        lineHeight = 26.sp
                    ),
                    customTextSize = TextSize.Medium
                ),
                colors = RdsButtonColors(
                    backgroundColor = RdsColors.yellow400,
                    contentColor = RdsColors.white,
                    disabledBackgroundColor = RdsColors.dark2.copy(alpha = 0.12f),
                    disabledContentColor = RdsColors.dark1.copy(alpha = 0.12f),
                ),
                onClick = { onBuyClick(subscription) }
            )
        }
    }
}

@Preview(device = Devices.PIXEL_2_XL, showSystemUi = true)
@Composable
fun PreviewRdsGenericButton() {
    AndroidKitchen2Theme {
        SubscriptionCtaCard(
            modifier = Modifier,
            subscription = SubscriptionInfo.Subscription(
                subscriptionId = "",
                title = "₹1000 Earnings ",
                description = "₹0 Commission",
                validityInfoDescription = "Valid for 10 days",
                actualPrice = 700.0,
                amountAfterDiscount = 500.0,
                validityTag = SubscriptionInfo.ValidityTag.NO_TAG,
                purchasePrice = BigDecimal(590.0),
                planType = "",
                ruleType = "",
                discountType = "",
                discountValue = 0.0
            ),
            extraInfo = SubscriptionInfo.ExtraInfo(
                gstPercentage = 18f,
                helpInfo = SubscriptionInfo.HelpInfo(
                    title = "Recharge",
                    context = "recharge"
                )
            ),
            onBuyClick = {},
            ctaText = "Recharge"
        )
    }
}
