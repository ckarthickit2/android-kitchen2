package me.kartdroid.androidkitchen2.subscription.v1.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.rapidodesignsystem.components.icon.RdsIcon
import com.rapido.rapidodesignsystem.components.icon.RdsIconDrawableConfig
import com.rapido.rapidodesignsystem.components.text.RdsHtmlText
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfo
import me.kartdroid.androidkitchen2.utils.toFormattedString
import java.math.BigDecimal

/**
 * Created by Abhishek Raj on 01/06/23.
 */

@Composable
fun SubscriptionContents(
        modifier: Modifier = Modifier,
        subscription: SubscriptionInfo.Subscription,
        selectedSubscription: SubscriptionInfo.Subscription,
) {
    Row(modifier = modifier) {
        if (subscription.validityTag == SubscriptionInfo.ValidityTag.NO_TAG) {
            RdsIcon(getSubscriptionCardSelectIcon(subscription, Modifier.wrapContentWidth(), selectedSubscription))
        }
        Column(
            modifier = Modifier
                .padding(start = if (subscription.validityTag == SubscriptionInfo.ValidityTag.NO_TAG) 16.dp else 20.dp, top = 12.dp, end = 16.dp, bottom = 16.dp)
                .weight(1f)
        ) {
            RdsTextView(
                modifier = Modifier, text = subscription.title,
                style = RdsTextType.TitleMedium.typography.copy(
                    color = RdsColors.dark1,
                    fontSize = 20.sp,
                    lineHeight = 27.sp,
                    fontWeight = FontWeight.Medium
                ),
                maxLines = 2
            )

            RdsTextView(
                modifier = Modifier, text = subscription.description,
                style = RdsTextType.TitleMedium.typography.copy(
                    color = RdsColors.dark1,
                    fontSize = 14.sp,
                    lineHeight = 19.sp,
                    fontWeight = FontWeight.Medium
                ),
                maxLines = 2
            )

            RdsHtmlText(
                text = subscription.validityInfoDescription,
                modifier = Modifier
                    .padding(top = if (subscription.validityTag == SubscriptionInfo.ValidityTag.NO_TAG) 16.dp else 24.dp)
                    .align(Alignment.Start),
                maxLines = 2,
                textType = RdsTextType.Custom(
                    RdsTextType.BodyMedium.typography.copy(
                        color = RdsColors.dark3,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 19.sp
                    ),
                    RdsTextType.TitleSmall.textSize
                ),
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 12.dp, end = 16.dp, bottom = 16.dp)
                .wrapContentWidth(),
            horizontalAlignment = Alignment.End
        ) {
            val strikedOutPrice: String =
                if (subscription.actualPrice > 0f && subscription.amountAfterDiscount >= 0f) {
                    "₹${subscription.actualPrice.toFormattedString()}"
                } else {
                    ""
                }
            val purchasePrice = if (subscription.amountAfterDiscount == 0.0) {
                stringResource(id = R.string.free)
            } else if (subscription.amountAfterDiscount > 0f) {
                "₹${subscription.amountAfterDiscount.toFormattedString()}"
            } else {
                "₹${subscription.actualPrice.toFormattedString()}"
            }

            val purchasePriceTextColor = if (subscription.validityTag == SubscriptionInfo.ValidityTag.NO_TAG) {
                RdsColors.purpleDark1
            } else {
                RdsColors.dark1
            }

            if (strikedOutPrice.isNotBlank()) {
                RdsTextView(
                    text = strikedOutPrice,
                    style = RdsTextType.TitleMedium.typography.copy(
                        color = RdsColors.light3,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        lineHeight = 19.sp,
                        /**
                         * For unlimited plan, the amountAfterDiscount could be zero and in that case we want to show the actual price without strikethrough
                         * */
                        textDecoration = TextDecoration.LineThrough
                    ),
                    modifier = Modifier.align(Alignment.End)
                )
            }

            if (purchasePrice.isNotBlank()) {
                RdsTextView(
                    text = purchasePrice,
                    style = RdsTextType.TitleMedium.typography.copy(
                        color = purchasePriceTextColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        lineHeight = 27.sp
                    ),
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Composable
private fun getSubscriptionCardSelectIcon(subscription: SubscriptionInfo.Subscription, modifier: Modifier, selectedSubscription: SubscriptionInfo.Subscription) =
    if (subscription.subscriptionId == selectedSubscription.subscriptionId) RdsIconDrawableConfig(drawable = R.drawable.ic_selected_plan, modifier = modifier.padding(start = 16.dp, top = 16.dp)) else RdsIconDrawableConfig(drawable = R.drawable.ic_unselected_plan, modifier = modifier.padding(start = 16.dp, top = 16.dp))

@Preview
@Composable
fun PreviewSubscriptionContents() {
    SubscriptionContents(
        modifier = Modifier,
        subscription = SubscriptionInfo.Subscription(
            subscriptionId = "",
            title = "₹1000 Earnings Earnings Earnings Earnings ",
            description = "₹0 Commission Commission Commission Commission Commission",
            validityInfoDescription = "Valid for 10 days for 10 days for 10 days for 10 days for 10 days",
            actualPrice = 700.0,
            amountAfterDiscount = 500.0,
            validityTag = SubscriptionInfo.ValidityTag.ACTIVE,
            purchasePrice = BigDecimal(590.0),
            planType = "",
            ruleType = "",
            discountType = "",
            discountValue = 0.0
        ),
        selectedSubscription = SubscriptionInfo.Subscription(
            subscriptionId = "",
            title = "₹1000 Earnings Earnings Earnings Earnings ",
            description = "₹0 Commission Commission Commission Commission Commission",
            validityInfoDescription = "Valid for 10 days for 10 days for 10 days for 10 days for 10 days",
            actualPrice = 700.0,
            amountAfterDiscount = 500.0,
            validityTag = SubscriptionInfo.ValidityTag.NO_TAG,
            purchasePrice = BigDecimal(590.0),
            planType = "",
            ruleType = "",
            discountType = "",
            discountValue = 0.0
        )
    )
}
