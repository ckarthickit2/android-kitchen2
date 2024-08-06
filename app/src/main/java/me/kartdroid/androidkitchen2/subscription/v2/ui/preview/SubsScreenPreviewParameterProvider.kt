package me.kartdroid.androidkitchen2.subscription.v2.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.kartdroid.androidkitchen2.subscription.models.ActivatedSubscriptionV2
import me.kartdroid.androidkitchen2.subscription.models.AmountInfo
import me.kartdroid.androidkitchen2.subscription.models.AvailableSubscription
import me.kartdroid.androidkitchen2.subscription.models.CommissionSavedInfo
import me.kartdroid.androidkitchen2.subscription.models.DurationInfo
import me.kartdroid.androidkitchen2.subscription.models.EligibleConsumptionInfo
import me.kartdroid.androidkitchen2.subscription.models.FooterCTAInfo
import me.kartdroid.androidkitchen2.subscription.models.LifetimeCommissionSavedInfo
import me.kartdroid.androidkitchen2.subscription.models.PurchaseProgressInfo
import me.kartdroid.androidkitchen2.subscription.models.PurchaseTransactionStatus
import me.kartdroid.androidkitchen2.subscription.models.PurchasedSubscriptionV2
import me.kartdroid.androidkitchen2.subscription.models.RuleType
import me.kartdroid.androidkitchen2.subscription.models.SubsConsumptionInfo
import me.kartdroid.androidkitchen2.subscription.models.SubsExpiryInfo
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfo
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfoV2
import me.kartdroid.androidkitchen2.subscription.models.ValidityTagInfo

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 28/04/24
 */
class SubsScreenPreviewParameterProvider : PreviewParameterProvider<SubscriptionInfoV2> {
    override val values: Sequence<SubscriptionInfoV2> = sequenceOf(
        SubscriptionInfoV2(
            toolbarTitle = "Recharge",
            bannerInfo = SubscriptionInfo.BannerInfo(
                "https://rapido-app-assets.storage.googleapis.com/86207e8617ff77a1d3ee359573a4a6da_1714720328925.png",
                "",
                ""
            ),
            lifetimeCommissionSavedInfo = LifetimeCommissionSavedInfo(
                commissionSavedLabel = "₹34",
                commissionLifetimeLabel = "<html>In last <b>1</b> month</html>"
            ),
            currentSubscription = ActivatedSubscriptionV2(
                subscriptionId = "1",
                title = "Ramzaan Special",
                ruleType = RuleType.ZERO_COMMISSION,
                eligibleConsumptionInfo = EligibleConsumptionInfo(
                    typeLabel = "Earnings",
                    unitsLabel = "₹10,000",
                    units = 10000f,
                ),
                durationInfo = DurationInfo(
                    typeLabel = "Month",
                    unitsLabel = "1"
                ),
                amountInfo = AmountInfo(
                    currentPrice = "₹ 125",
                    strikeOutPrice = "₹ 250"
                ),
                validityTagInfo = ValidityTagInfo(
                    tag = SubscriptionInfo.ValidityTag.ACTIVE,
                    label = "Active",
                ),
                purchaseProgressInfo = PurchaseProgressInfo(
                    purchaseProgressLabel = "₹124 Paid",
                    transactionStatus = PurchaseTransactionStatus.COMPLETE,
                ),
                consumptionInfo = SubsConsumptionInfo(
                    consumedUnitsLabel = "₹7046",
                    consumedUnits = 7046f
                ),
                expiryInfo = SubsExpiryInfo(
                    dateLabel = "Until 12/04/24",
                    timeLabel = "10:00 am"
                ),
                commissionSavedInfo = CommissionSavedInfo(
                    savedAmountLabel = "34",
                    caption = "saved so far"
                ),
            ),
            upcomingSubscriptions = listOf(
                PurchasedSubscriptionV2(
                    subscriptionId = "2",
                    title = "Weekend Saver",
                    ruleType = RuleType.VARIABLE_COMMISSION,
                    eligibleConsumptionInfo = EligibleConsumptionInfo(
                        typeLabel = "Rides",
                        unitsLabel = "50",
                        units = 50f,
                    ),
                    durationInfo = DurationInfo(
                        typeLabel = "Days",
                        unitsLabel = "7"
                    ),
                    amountInfo = AmountInfo(
                        currentPrice = "₹299",
                        strikeOutPrice = "₹499"
                    ),
                    validityTagInfo = ValidityTagInfo(
                        tag = SubscriptionInfo.ValidityTag.STARTS_SOON,
                        label = "Starts Soon"
                    ),
                    purchaseProgressInfo = PurchaseProgressInfo(
                        purchaseProgressLabel = "₹299 paid",
                        transactionStatus = PurchaseTransactionStatus.COMPLETE,
                    ),
                ),
                PurchasedSubscriptionV2(
                    subscriptionId = "3",
                    title = "Weekend Saver",
                    ruleType = RuleType.VARIABLE_COMMISSION,
                    eligibleConsumptionInfo = EligibleConsumptionInfo(
                        typeLabel = "Rides",
                        unitsLabel = "50",
                        units = 50f,
                    ),
                    durationInfo = DurationInfo(
                        typeLabel = "Days",
                        unitsLabel = "7"
                    ),
                    amountInfo = AmountInfo(
                        currentPrice = "₹299",
                        strikeOutPrice = "₹499"
                    ),
                    validityTagInfo = ValidityTagInfo(
                        tag = SubscriptionInfo.ValidityTag.PROCESSING,
                        label = "Processing"
                    ),
                    purchaseProgressInfo = PurchaseProgressInfo(
                        purchaseProgressLabel = "₹299 Processing",
                        transactionStatus = PurchaseTransactionStatus.PROCESSING,
                    ),
                )
            ),
            availableSubscriptions = listOf(
                AvailableSubscription(
                    subscriptionId = "4",
                    title = "Weekly Pass",
                    isRecommended = false,
                    ruleType = RuleType.ZERO_COMMISSION,
                    eligibleConsumptionInfo = EligibleConsumptionInfo(
                        typeLabel = "Rides",
                        unitsLabel = "50",
                        units = 50f,
                    ),
                    durationInfo = DurationInfo(
                        typeLabel = "Days",
                        unitsLabel = "7"
                    ),
                    amountInfo = AmountInfo(
                        strikeOutPrice = "₹429",
                        currentPrice = "₹299",
                    )
                ),
                AvailableSubscription(
                    subscriptionId = "5",
                    title = "Monthly Pass",
                    isRecommended = true,
                    ruleType = RuleType.VARIABLE_COMMISSION,
                    eligibleConsumptionInfo = EligibleConsumptionInfo(
                        typeLabel = "Rides",
                        unitsLabel = "Unlimited",
                        units = -1f,
                    ),
                    durationInfo = DurationInfo(
                        typeLabel = "Month",
                        unitsLabel = "1"
                    ),
                    amountInfo = AmountInfo(
                        strikeOutPrice = "₹999",
                        currentPrice = "₹799",
                    )
                ),
            ),
            tncInfo = listOf(
                "GST will be charged on plan purchases",
                "No refunds will be given once plan is purchased",
                "Captain will not receive orders until there is an active recharge plan",
                "Plan is active for cabs only",
                "Expiry date is applicable for all plans"
            ),
            extraInfo = SubscriptionInfo.ExtraInfo(
                gstPercentage = 18f,
                helpInfo = SubscriptionInfo.HelpInfo(
                    title = "Help",
                    context = "recharge"
                ),
                confirmationPopupTitle = ""
            ),
            footerCTAInfo = FooterCTAInfo(
                calloutsHtmlText = "<html>Pay <b>₹199 + ₹152</b> (18% GST)</html>",
                ctaLabel = "Recharge",
                ctaDeepLink = "",
            ),
            selectedSubscriptionID = "4",
        )
    )
}