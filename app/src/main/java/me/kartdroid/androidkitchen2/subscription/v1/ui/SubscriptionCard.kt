package me.kartdroid.androidkitchen2.subscription.v1.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.components.chips.RdsChip
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.subscription.extensions.getTagColor
import me.kartdroid.androidkitchen2.subscription.extensions.getTagIcon
import me.kartdroid.androidkitchen2.subscription.extensions.getTagText
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfo
import java.math.BigDecimal

/**
 * Created by Abhishek Raj on 31/05/23.
 */

@Composable
fun SubscriptionCard(
        modifier: Modifier = Modifier,
        subscription: SubscriptionInfo.Subscription,
        selectable: Boolean = true,
        onSelect: (SubscriptionInfo.Subscription) -> Unit,
        selectedSubscription: SubscriptionInfo.Subscription
) {
    Row(modifier = modifier.wrapContentWidth()) {
        Spacer(modifier = Modifier.width(24.dp))
        Surface(
            modifier = Modifier
                .weight(1f)
                .shadow(
                    if (subscription.validityTag == SubscriptionInfo.ValidityTag.NO_TAG) {
                        if (subscription.subscriptionId == selectedSubscription.subscriptionId) {
                            5.dp
                        } else {
                            3.dp
                        }
                    } else 0.dp,
                    shape = RoundedCornerShape(8.dp)
                ).clickable(enabled = selectable, onClick = { onSelect(subscription) }),
            border = getSubscriptionCardStroke(subscription, selectedSubscription),
            shape = RoundedCornerShape(8.dp),
            elevation = if (subscription.validityTag == SubscriptionInfo.ValidityTag.NO_TAG) 8.dp else 0.dp,
        ) {

            Column(modifier = Modifier, horizontalAlignment = Alignment.Start) {
                if (subscription.validityTag != SubscriptionInfo.ValidityTag.NO_TAG) {
                    RdsChip(
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                        text = stringResource(id = subscription.validityTag.getTagText()),
                        onChecked = {},
                        leadingIconConfig = subscription.validityTag.getTagIcon(),
                        fillColor = subscription.validityTag.getTagColor(),
                        textColor = RdsColors.white,
                    )
                }
                SubscriptionContents(modifier = Modifier.padding(top = if (subscription.validityTag == SubscriptionInfo.ValidityTag.NO_TAG) 4.dp else 0.dp), subscription = subscription, selectedSubscription)
            }
        }
        Spacer(modifier = Modifier.width(24.dp))
    }
}

@Composable
private fun getSubscriptionCardStroke(subscription: SubscriptionInfo.Subscription, selectedSubscriptionForPurchase: SubscriptionInfo.Subscription): BorderStroke {
    return if (subscription.validityTag == SubscriptionInfo.ValidityTag.NO_TAG) {
        if (subscription.subscriptionId == selectedSubscriptionForPurchase.subscriptionId) {
            BorderStroke((1.5).dp, RdsColors.purpleLight5)
        } else {
            BorderStroke(0.dp, RdsColors.transparent)
        }
    } else {
        BorderStroke(2.dp, subscription.validityTag.getTagColor())
    }
}

@Preview(device = Devices.PIXEL_2_XL, showSystemUi = true)
@Composable
fun PreviewSubscriptionCard() {
    RapidoTheme {
        Column {
            SubscriptionCard(
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
                onSelect = { },
                selectedSubscription = SubscriptionInfo.Subscription(
                    subscriptionId = "",
                    title = "₹1000 Earnings left",
                    description = "₹0 Commission",
                    validityInfoDescription = "Valid till 12th June",
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

            Spacer(modifier = Modifier.height(16.dp))
            SubscriptionCard(
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
                onSelect = { },
                selectedSubscription = SubscriptionInfo.Subscription(
                    subscriptionId = "",
                    title = "₹1000 Earnings left",
                    description = "₹0 Commission",
                    validityInfoDescription = "Valid till 12th June",
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

            Spacer(modifier = Modifier.height(16.dp))
            SubscriptionCard(
                modifier = Modifier,
                subscription = SubscriptionInfo.Subscription(
                    subscriptionId = "",
                    title = "₹10000 Earnings left",
                    description = "₹0 Commission",
                    validityInfoDescription = "Valid till 12th June",
                    actualPrice = 1700.0,
                    amountAfterDiscount = 1500.0,
                    validityTag = SubscriptionInfo.ValidityTag.ACTIVE,
                    purchasePrice = BigDecimal(1770.0),
                    planType = "",
                    ruleType = "",
                    discountType = "",
                    discountValue = 0.0
                ),
                onSelect = { },
                selectedSubscription = SubscriptionInfo.Subscription(
                    subscriptionId = "",
                    title = "₹1000 Earnings left",
                    description = "₹0 Commission",
                    validityInfoDescription = "Valid till 12th June",
                    actualPrice = 700.0,
                    amountAfterDiscount = 500.0,
                    validityTag = SubscriptionInfo.ValidityTag.NO_TAG,
                    purchasePrice = BigDecimal(1770.0),
                    planType = "",
                    ruleType = "",
                    discountType = "",
                    discountValue = 0.0
                )
            )
        }
    }
}
