package me.kartdroid.androidkitchen2.subscription.v2.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rapido.rapidodesignsystem.colors.RapidoThemeColors
import me.kartdroid.androidkitchen2.subscription.models.AmountInfo
import me.kartdroid.androidkitchen2.subscription.models.DurationInfo
import me.kartdroid.androidkitchen2.subscription.models.EligibleConsumptionInfo
import me.kartdroid.androidkitchen2.subscription.models.PurchaseProgressInfo
import me.kartdroid.androidkitchen2.subscription.models.PurchaseTransactionStatus
import me.kartdroid.androidkitchen2.subscription.models.PurchasedSubscriptionV2
import me.kartdroid.androidkitchen2.subscription.models.RuleType
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfo
import me.kartdroid.androidkitchen2.subscription.models.ValidityTagInfo

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 26/04/24
 */


class SubscriptionInfoRowPreviewProvider : PreviewParameterProvider<Pair<PurchasedSubscriptionV2, RapidoThemeColors>> {
    override val values: Sequence<Pair<PurchasedSubscriptionV2, RapidoThemeColors>> = sequenceOf(
        PurchasedSubscriptionV2(
            subscriptionId = "weekend_saver_1",
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
        ) to blueTheme,
        PurchasedSubscriptionV2(
            subscriptionId = "weekend_saver_2",
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
        ) to blueTheme,
        PurchasedSubscriptionV2(
            subscriptionId = "weekend_saver_3",
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
                tag = SubscriptionInfo.ValidityTag.EXPIRED,
                label = "Payment Failed"
            ),
            purchaseProgressInfo = PurchaseProgressInfo(
                purchaseProgressLabel = "₹299 Failed",
                transactionStatus = PurchaseTransactionStatus.FAILED,
            ),
        ) to redBlueTheme,
    )

}