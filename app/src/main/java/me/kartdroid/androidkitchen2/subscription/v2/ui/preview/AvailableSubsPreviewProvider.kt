package me.kartdroid.androidkitchen2.subscription.v2.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.kartdroid.androidkitchen2.subscription.models.AmountInfo
import me.kartdroid.androidkitchen2.subscription.models.ConsumptionInfo
import me.kartdroid.androidkitchen2.subscription.models.DurationInfo
import me.kartdroid.androidkitchen2.subscription.models.RuleType
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionV2

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 26/04/24
 */
class AvailableSubsPreviewProvider : PreviewParameterProvider<SubscriptionV2> {
    override val values: Sequence<SubscriptionV2> = sequenceOf(
            SubscriptionV2(
                    title = "Weekly Pass",
                    isRecommended = false,
                    ruleType = RuleType.ZERO_COMMISSION,
                    consumptionInfo = ConsumptionInfo(
                            typeLabel = "Rides",
                            unitsLabel = "50"
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
            SubscriptionV2(
                    title = "Monthly Pass",
                    isRecommended = true,
                    ruleType = RuleType.VARIABLE_COMMISSION,
                    consumptionInfo = ConsumptionInfo(
                            typeLabel = "Rides",
                            unitsLabel = "Unlimited"
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
    )
}