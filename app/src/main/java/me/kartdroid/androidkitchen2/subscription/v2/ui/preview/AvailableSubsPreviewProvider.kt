package me.kartdroid.androidkitchen2.subscription.v2.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.kartdroid.androidkitchen2.subscription.models.AmountInfo
import me.kartdroid.androidkitchen2.subscription.models.DurationInfo
import me.kartdroid.androidkitchen2.subscription.models.EligibleConsumptionInfo
import me.kartdroid.androidkitchen2.subscription.models.RuleType
import me.kartdroid.androidkitchen2.subscription.models.AvailableSubscription

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 26/04/24
 */
class AvailableSubsPreviewProvider : PreviewParameterProvider<AvailableSubscription> {
    override val values: Sequence<AvailableSubscription> = sequenceOf(
            AvailableSubscription(
                    title = "Weekly Pass",
                    isRecommended = false,
                    ruleType = RuleType.ZERO_COMMISSION,
                    eligibleConsumptionInfo = EligibleConsumptionInfo(
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
            AvailableSubscription(
                    title = "Monthly Pass",
                    isRecommended = true,
                    ruleType = RuleType.VARIABLE_COMMISSION,
                    eligibleConsumptionInfo = EligibleConsumptionInfo(
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