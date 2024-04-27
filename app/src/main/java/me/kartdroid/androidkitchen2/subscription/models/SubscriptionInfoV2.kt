package me.kartdroid.androidkitchen2.subscription.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 26/04/24
 */


interface SubscriptionV2 {
    val title: String
    val ruleType: RuleType
    val eligibleConsumptionInfo: EligibleConsumptionInfo
    val durationInfo: DurationInfo
    val amountInfo: AmountInfo
}
data class AvailableSubscription(
        override val title: String,
        val isRecommended: Boolean,
        override val ruleType: RuleType,
        override val eligibleConsumptionInfo: EligibleConsumptionInfo,
        override val durationInfo: DurationInfo,
        override val amountInfo: AmountInfo,
): SubscriptionV2


data class EligibleConsumptionInfo(
        val typeLabel: String, //Earnings, Rides, Unlimited
        val unitsLabel: String, //₹ 1000, 10 , Unlimited
)

data class DurationInfo(
        val typeLabel: String, // Day , Days, Month etc.,
        val unitsLabel: String, // 10
)

data class AmountInfo(
        val currentPrice: String,
        val strikeOutPrice: String,
)

sealed interface PurchaseProgressedSubscription: SubscriptionV2 {
    val validityTag: SubscriptionInfo.ValidityTag
    val purchaseProgressInfo: PurchaseProgressInfo
}
data class PurchasedSubscriptionV2(
        override val title: String,
        override val ruleType: RuleType,
        override val eligibleConsumptionInfo: EligibleConsumptionInfo,
        override val durationInfo: DurationInfo,
        override val amountInfo: AmountInfo,
        override val validityTag: SubscriptionInfo.ValidityTag,
        override val purchaseProgressInfo: PurchaseProgressInfo,
): PurchaseProgressedSubscription

data class ActivatedSubscriptionV2(
        override val title: String,
        override val ruleType: RuleType,
        override val eligibleConsumptionInfo: EligibleConsumptionInfo,
        override val durationInfo: DurationInfo,
        override val amountInfo: AmountInfo,
        override val validityTag: SubscriptionInfo.ValidityTag,
        override val purchaseProgressInfo: PurchaseProgressInfo,
        //Activated Subs Properties
        val consumptionInfo: SubsConsumptionInfo,
        val expiryInfo: SubsExpiryInfo,
        val commissionSavedInfo: CommissionSavedInfo,
): PurchaseProgressedSubscription


data class PurchaseProgressInfo(
        val purchaseProgressLabel: String,
        val transactionStatus: PurchaseTransactionStatus,
)

data class SubsConsumptionInfo(
        val consumedUnitsLabel: String
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