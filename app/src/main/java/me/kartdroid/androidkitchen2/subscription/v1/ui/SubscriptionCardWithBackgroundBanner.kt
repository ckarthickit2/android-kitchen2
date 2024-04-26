package com.rapido.rider.subscriptions.presentation.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfo
import me.kartdroid.androidkitchen2.subscription.v1.ui.SubscriptionCard
import java.math.BigDecimal

/**
 * Created by Abhishek Raj on 07/06/23.
 */

@Composable
fun SubscriptionCardWithBackgroundBanner(
        modifier: Modifier = Modifier,
        bannerInfo: SubscriptionInfo.BannerInfo,
        subscription: SubscriptionInfo.Subscription,
        selectable: Boolean = false,
        onSelect: (SubscriptionInfo.Subscription) -> Unit,
        selectedSubscription: SubscriptionInfo.Subscription
) {
    Box(
        modifier = modifier
            .wrapContentHeight(),
    ) {
        val imageHeight = 256
        if (bannerInfo.bannerImageUrl.isNotBlank()) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(bannerInfo.bannerImageUrl)
                    .size(Size.ORIGINAL)
                    .placeholder(me.kartdroid.androidkitchen2.R.drawable.ic_subscription_placeholder_banner)
                    .build()
            )
            Image(
                modifier = Modifier
                    .height(imageHeight.dp)
                    .fillMaxWidth(),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }
        SubscriptionCard(
            modifier = Modifier
                .padding(top = (0.75f * imageHeight).dp)
                .padding()
                .wrapContentSize(),
            subscription, selectable, onSelect, selectedSubscription
        )
    }
}

@Preview(device = Devices.PIXEL_2_XL, showSystemUi = true)
@Composable
fun PreviewSubscriptionCardWithBackgroundBanner() {
    RapidoTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier) {
                SubscriptionCardWithBackgroundBanner(
                    modifier = Modifier,
                    bannerInfo = SubscriptionInfo.BannerInfo("", "", ""),
                    subscription = SubscriptionInfo.Subscription(
                        subscriptionId = "",
                        title = "₹1000 Earnings left  Earnings left  Earnings left",
                        description = "₹0 Commission Commission Commission",
                        validityInfoDescription = "Valid till 12th June till 12th June till 12th June",
                        actualPrice = 700.0,
                        amountAfterDiscount = 500.0,
                        validityTag = SubscriptionInfo.ValidityTag.ACTIVE,
                        purchasePrice = BigDecimal(590.0),
                        planType = "",
                        ruleType = "",
                        discountType = "",
                        discountValue = 0.0
                    ),
                    onSelect = {
                    },
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
            }
        }
    }
}
