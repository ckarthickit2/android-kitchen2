package me.kartdroid.androidkitchen2.subscription.v1.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfo
import me.kartdroid.androidkitchen2.subscription.state.SubscriptionsBottomSheetUi
import me.kartdroid.androidkitchen2.subscription.state.SubscriptionsUiState
import java.math.BigDecimal

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 26/04/24
 */


class SubsScreenPreviewParameterProvider : PreviewParameterProvider<SubscriptionsUiState> {
    override val values: Sequence<SubscriptionsUiState> = sequenceOf(
        SubscriptionsUiState.RenderSubscriptions(
            subscriptionInfo = SubscriptionInfo(
                toolbarTitle = "Recharge",
                bannerInfo = SubscriptionInfo.BannerInfo("https://rapido-app-assets.storage.googleapis.com/0ba485b21b46fbc8563bebf2a8de1293_1686145423896.png", "", ""),
                activeSubscriptions = SubscriptionInfo.SubscriptionDetails(
                    "",
                    listOf(
                        SubscriptionInfo.Subscription(
                            subscriptionId = "",
                            title = "₹1000 Earnings left",
                            description = "₹0 Commission",
                            validityInfoDescription = "Valid till 12th June",
                            actualPrice = 700.0,
                            amountAfterDiscount = 500.0,
                            validityTag = SubscriptionInfo.ValidityTag.ACTIVE,
                            purchasePrice = BigDecimal(590.0),
                            planType = "",
                            ruleType = "",
                            discountType = "",
                            discountValue = 0.0,
                            passPurchaseDescription = "Incentives may not be applicable"
                        ),
                        SubscriptionInfo.Subscription(
                            subscriptionId = "",
                            title = "₹1000 Earnings left",
                            description = "₹0 Commission",
                            validityInfoDescription = "Valid till 12th June",
                            actualPrice = 700.0,
                            amountAfterDiscount = 500.0,
                            validityTag = SubscriptionInfo.ValidityTag.EXPIRING_SOON,
                            purchasePrice = BigDecimal(590.0),
                            planType = "",
                            ruleType = "",
                            discountType = "",
                            discountValue = 0.0,
                            passPurchaseDescription = "Incentives may not be applicable"
                        ),
                        SubscriptionInfo.Subscription(
                            subscriptionId = "",
                            title = "₹1000 Earnings left",
                            description = "₹0 Commission",
                            validityInfoDescription = "Valid till 12th June",
                            actualPrice = 700.0,
                            amountAfterDiscount = 500.0,
                            validityTag = SubscriptionInfo.ValidityTag.EXPIRED,
                            purchasePrice = BigDecimal(590.0),
                            planType = "",
                            ruleType = "",
                            discountType = "",
                            discountValue = 0.0,
                            passPurchaseDescription = "Incentives may not be applicable"
                        )
                    )
                ),
                purchasedSubscriptions = SubscriptionInfo.SubscriptionDetails(
                    nudgeText = "Your upcoming plan will be activated automatically",
                    subscriptions =
                    listOf(
                        SubscriptionInfo.Subscription(
                            subscriptionId = "", title = "₹1000 Earnings left", description = "₹0 Commission", validityInfoDescription = "Valid till 12th June", actualPrice = 700.0, amountAfterDiscount = 500.0, purchasePrice = BigDecimal(50000.0), validityTag = SubscriptionInfo.ValidityTag.STARTS_SOON, planType = "", ruleType = "", discountType = "", discountValue = 0.0,
                            passPurchaseDescription = "Incentives may not be applicable"
                        ),
                        SubscriptionInfo.Subscription(
                            subscriptionId = "", title = "₹1000 Earnings left", description = "₹0 Commission", validityInfoDescription = "Valid till 12th June", actualPrice = 700.0, amountAfterDiscount = 500.0, purchasePrice = BigDecimal(50000.0), validityTag = SubscriptionInfo.ValidityTag.STARTS_SOON, planType = "", ruleType = "", discountType = "", discountValue = 0.0,
                            passPurchaseDescription = "Incentives may not be applicable"
                        )
                    )
                ),
                availableSubscriptions = SubscriptionInfo.SubscriptionDetails(
                    "Buy a Plan to continue after your 5 DAY free trial",
                    listOf(
                        SubscriptionInfo.Subscription(
                            subscriptionId = "a",
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
                            discountValue = 0.0,
                            passPurchaseDescription = "Incentives may not be applicable"
                        ),
                        SubscriptionInfo.Subscription(
                            subscriptionId = "b",
                            title = "₹1000 Earnings left",
                            description = "₹0 Commission",
                            validityInfoDescription = "Valid till 12th June",
                            actualPrice = 0.0,
                            amountAfterDiscount = 500.0,
                            validityTag = SubscriptionInfo.ValidityTag.NO_TAG,
                            purchasePrice = BigDecimal(590.0),
                            planType = "",
                            ruleType = "",
                            discountType = "",
                            discountValue = 0.0,
                            passPurchaseDescription = "Incentives may not be applicable"
                        ),
                        SubscriptionInfo.Subscription(
                            subscriptionId = "c",
                            title = "₹500 Earnings left",
                            description = "₹0 Commission",
                            validityInfoDescription = "Valid till 12th June",
                            actualPrice = 500.0,
                            amountAfterDiscount = -1.0,
                            validityTag = SubscriptionInfo.ValidityTag.NO_TAG,
                            purchasePrice = BigDecimal(590.0),
                            planType = "",
                            ruleType = "",
                            discountType = "",
                            discountValue = 0.0,
                            passPurchaseDescription = "Incentives may not be applicable"
                        ),
                        SubscriptionInfo.Subscription(
                            subscriptionId = "d",
                            title = "₹800 Earnings left",
                            description = "₹0 Commission",
                            validityInfoDescription = "Valid till 12th June",
                            actualPrice = 800.0,
                            amountAfterDiscount = -1.0,
                            validityTag = SubscriptionInfo.ValidityTag.NO_TAG,
                            purchasePrice = BigDecimal(590.0),
                            planType = "",
                            ruleType = "",
                            discountType = "",
                            discountValue = 0.0,
                            passPurchaseDescription = "Incentives may not be applicable"
                        ),
                        SubscriptionInfo.Subscription(
                            subscriptionId = "e",
                            title = "₹1000 Earnings left",
                            description = "₹0 Commission",
                            validityInfoDescription = "Valid till 12th June",
                            actualPrice = 0.0,
                            amountAfterDiscount = 0.0,
                            validityTag = SubscriptionInfo.ValidityTag.NO_TAG,
                            purchasePrice = BigDecimal(590.0),
                            planType = "",
                            ruleType = "",
                            discountType = "",
                            discountValue = 0.0,
                            passPurchaseDescription = "Incentives may not be applicable"
                        ),
                        SubscriptionInfo.Subscription(
                            subscriptionId = "f",
                            title = "₹600 Earnings left",
                            description = "₹0 Commission",
                            validityInfoDescription = "Valid till 12th June",
                            actualPrice = 600.0,
                            amountAfterDiscount = 0.0,
                            validityTag = SubscriptionInfo.ValidityTag.NO_TAG,
                            purchasePrice = BigDecimal(590.0),
                            planType = "",
                            ruleType = "",
                            discountType = "",
                            discountValue = 0.0,
                            passPurchaseDescription = "Incentives may not be applicable"
                        )
                    )
                ),
                tncInfo = listOf(
                    "GST will be charged on plan purchases",
                    "No refunds will be given once plan is purchased",
                    "Captain will not receive orders until there is an active recharge plan",
                    "Plan is active for cabs only",
                    "Expiry date is applicable for all plans"
                ),
                ctaText = "Recharge",
                extraInfo = SubscriptionInfo.ExtraInfo(
                    gstPercentage = 18f,
                    helpInfo = SubscriptionInfo.HelpInfo(
                        title = "Help",
                        context = "recharge"
                    )
                ),
                bottomSheetTitle = ""
            ),
            selectedSubscription = SubscriptionInfo.Subscription(
                subscriptionId = "b",
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
                discountValue = 0.0,
                passPurchaseDescription = "Incentives may not be applicable"
            ),
            SubscriptionsBottomSheetUi.NONE
        ),
        SubscriptionsUiState.Loading,
        SubscriptionsUiState.Idle,
    )

}