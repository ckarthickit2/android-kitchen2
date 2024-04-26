package me.kartdroid.androidkitchen2.subscription.v1.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rapido.rider.subscriptions.presentation.ui.composables.SubscriptionCardWithBackgroundBanner
import com.rapido.rider.subscriptions.presentation.ui.composables.TnCInfo
import com.rapido.rider.subscriptions.presentation.ui.composables.TnCTitle
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfo

/**
 * Created by Abhishek Raj on 15/06/23.
 */

@Composable
fun SubscriptionScreenInfo(
        modifier: Modifier = Modifier,
        subscriptionInfo: SubscriptionInfo,
        onCardSelected: (SubscriptionInfo.Subscription) -> Unit,
        selectedSubscription: SubscriptionInfo.Subscription
) {
    LazyColumn(
        modifier = modifier,
        content = {
            items(subscriptionInfo.activeSubscriptions.subscriptions) { subscription ->
                if (subscriptionInfo.activeSubscriptions.subscriptions.indexOf(subscription) == 0) {
                    SubscriptionCardWithBackgroundBanner(
                        modifier = Modifier,
                        bannerInfo = subscriptionInfo.bannerInfo,
                        subscription = subscription,
                        selectable = false,
                        onSelect = {
                            /* Do nothing for click on active subscription which is at the top. Only available card can be selected for purchase */
                        },
                        selectedSubscription = selectedSubscription
                    )
                } else {
                    SubscriptionCard(
                        modifier = Modifier.padding(top = 16.dp),
                        subscription = subscription,
                        selectable = false,
                        onSelect = {
                            /* Do nothing for click on active subscription which is at the top. Only available card can be selected for purchase */
                        },
                        selectedSubscription = selectedSubscription
                    )
                }
            }
            item {
                if (showPurchasedSubscriptionNudge(subscriptionInfo)) {
                    InfoText(modifier = Modifier.padding(top = 26.dp, bottom = 7.dp), infoText = subscriptionInfo.purchasedSubscriptions.nudgeText)
                }
            }
            items(subscriptionInfo.purchasedSubscriptions.subscriptions) { subscription ->
                if (showBannerForPurchasedSubscription(subscriptionInfo, subscription)) {
                    SubscriptionCardWithBackgroundBanner(
                        modifier = Modifier,
                        bannerInfo = subscriptionInfo.bannerInfo,
                        subscription = subscription,
                        selectable = false,
                        onSelect = {
                            /* Do nothing for click on purchased subscription. Only available card can be selected for purchase */
                        },
                        selectedSubscription = selectedSubscription
                    )
                } else {
                    SubscriptionCard(
                        modifier = Modifier.padding(top = 16.dp),
                        subscription = subscription,
                        selectable = false,
                        onSelect = {
                            /* Do nothing for click on purchased subscription. Only available card can be selected for purchase */
                        },
                        selectedSubscription = selectedSubscription
                    )
                }
            }
            item {
                if (subscriptionInfo.availableSubscriptions.subscriptions.isNotEmpty()) {
                    InfoText(modifier = Modifier.padding(top = 26.dp, bottom = 7.dp), infoText = subscriptionInfo.availableSubscriptions.nudgeText)
                }
            }
            items(subscriptionInfo.availableSubscriptions.subscriptions) { subscription ->
                SubscriptionCard(
                    modifier = Modifier.padding(top = 16.dp),
                    subscription = subscription,
                    selectable = true,
                    onSelect = {
                        onCardSelected(subscription)
                    },
                    selectedSubscription = selectedSubscription
                )
            }
            /* Show empty state if no subscription info active, purchased or available is present */
            if (subscriptionInfo.activeSubscriptions.subscriptions.isEmpty() && subscriptionInfo.purchasedSubscriptions.subscriptions.isEmpty() && subscriptionInfo.availableSubscriptions.subscriptions.isEmpty())
                item {
                    NoSubscription()
                }
            if (subscriptionInfo.tncInfo.isNotEmpty()) {
                item {
                    TnCTitle()
                }
            }
            items(subscriptionInfo.tncInfo) { info ->
                if (subscriptionInfo.tncInfo.last() == info) {
                    TnCInfo(info, modifier = Modifier.padding(bottom = 120.dp))
                } else {
                    TnCInfo(info)
                }
            }
        }
    )
}

@Composable
private fun showBannerForPurchasedSubscription(subscriptionInfo: SubscriptionInfo, subscription: SubscriptionInfo.Subscription) =
    subscriptionInfo.activeSubscriptions.subscriptions.isEmpty() && subscriptionInfo.purchasedSubscriptions.subscriptions.indexOf(subscription) == 0

@Composable
private fun showPurchasedSubscriptionNudge(subscriptionInfo: SubscriptionInfo) =
    subscriptionInfo.purchasedSubscriptions.subscriptions.isNotEmpty() && subscriptionInfo.activeSubscriptions.subscriptions.isNotEmpty()
