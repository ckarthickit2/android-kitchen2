package me.kartdroid.androidkitchen2.subscription.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 26/04/24
 */


data class SubscriptionInfoV2(
    val toolbarTitle: String,
    val bannerInfo: SubscriptionInfo.BannerInfo,
    val lifetimeCommissionSavedInfo: LifetimeCommissionSavedInfo,
    val currentSubscription: PurchaseProgressedSubscription?, //Payment complete, processing (or) Failed
    val upcomingSubscriptions: List<PurchaseProgressedSubscription>, //Payment Complete, Processing or Failed
    val availableSubscriptions: List<AvailableSubscription>,
    val tncInfo: List<String>,
    val extraInfo: SubscriptionInfo.ExtraInfo,
    val footerCTAInfo: FooterCTAInfo,
    val selectedSubscriptionID: String? = null,
)

data class LifetimeCommissionSavedInfo(
    val commissionSavedLabel: String,
    val commissionLifetimeLabel: String,
)

data class FooterCTAInfo(
    val calloutsHtmlText: String,
    val ctaLabel: String,
    val ctaDeepLink: String,
)

interface SubscriptionV2 {
    val subscriptionId: String
    val title: String
    val ruleType: RuleType
    val eligibleConsumptionInfo: EligibleConsumptionInfo
    val durationInfo: DurationInfo
    val amountInfo: AmountInfo
}

data class AvailableSubscription(
    override val subscriptionId: String,
    override val title: String,
    val isRecommended: Boolean,
    override val ruleType: RuleType,
    override val eligibleConsumptionInfo: EligibleConsumptionInfo,
    override val durationInfo: DurationInfo,
    override val amountInfo: AmountInfo,
) : SubscriptionV2


data class EligibleConsumptionInfo(
    val typeLabel: String, //Earnings, Rides, Unlimited
    val unitsLabel: String, //₹ 1000, 10 , Unlimited
    val units: Float,
)

data class DurationInfo(
    val typeLabel: String, // Day , Days, Month etc.,
    val unitsLabel: String, // 10
)

data class AmountInfo(
    val currentPrice: String,
    val strikeOutPrice: String,
)

sealed interface PurchaseProgressedSubscription : SubscriptionV2 {
    val validityTagInfo: ValidityTagInfo
    val purchaseProgressInfo: PurchaseProgressInfo
}

data class ValidityTagInfo(
    val tag: SubscriptionInfo.ValidityTag,
    val label: String,
)

data class PurchasedSubscriptionV2(
    override val subscriptionId: String,
    override val title: String,
    override val ruleType: RuleType,
    override val eligibleConsumptionInfo: EligibleConsumptionInfo,
    override val durationInfo: DurationInfo,
    override val amountInfo: AmountInfo,
    override val validityTagInfo: ValidityTagInfo,
    override val purchaseProgressInfo: PurchaseProgressInfo,
) : PurchaseProgressedSubscription

data class ActivatedSubscriptionV2(
    override val subscriptionId: String,
    override val title: String,
    override val ruleType: RuleType,
    override val eligibleConsumptionInfo: EligibleConsumptionInfo,
    override val durationInfo: DurationInfo,
    override val amountInfo: AmountInfo,
    override val validityTagInfo: ValidityTagInfo,
    override val purchaseProgressInfo: PurchaseProgressInfo,
    //Activated Subs Properties
    val consumptionInfo: SubsConsumptionInfo,
    val expiryInfo: SubsExpiryInfo,
    val commissionSavedInfo: CommissionSavedInfo,
) : PurchaseProgressedSubscription


data class PurchaseProgressInfo(
    val purchaseProgressLabel: String,
    val transactionStatus: PurchaseTransactionStatus,
)

data class SubsConsumptionInfo(
    val consumedUnitsLabel: String,
    val consumedUnits: Float,
)

data class SubsExpiryInfo(
    val dateLabel: String,
    val timeLabel: String,
)

data class CommissionSavedInfo(
    val savedAmountLabel: String,
    val caption: String,
)


enum class PurchaseTransactionStatus {
    PROCESSING,
    COMPLETE,
    FAILED
}

@Serializable
enum class RuleType {
    @SerialName("zeroCommission")
    ZERO_COMMISSION,

    @SerialName("variableCommission")
    VARIABLE_COMMISSION
}