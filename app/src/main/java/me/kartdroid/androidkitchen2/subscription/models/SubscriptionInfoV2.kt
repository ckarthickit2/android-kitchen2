package me.kartdroid.androidkitchen2.subscription.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 26/04/24
 */

data class SubscriptionV2(
        val title: String,
        val isRecommended: Boolean,
        val ruleType: RuleType,
        val consumptionInfo: ConsumptionInfo,
        val durationInfo: DurationInfo,
        val amountInfo: AmountInfo,
)


data class ConsumptionInfo(
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

@Serializable
enum class RuleType {
    @SerialName("zeroCommission")
    ZERO_COMMISSION,

    @SerialName("variableCommission")
    VARIABLE_COMMISSION
}