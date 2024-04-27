package me.kartdroid.androidkitchen2.subscription.v2.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.kartdroid.androidkitchen2.subscription.models.ActivatedSubscriptionV2
import me.kartdroid.androidkitchen2.subscription.models.AmountInfo
import me.kartdroid.androidkitchen2.subscription.models.CommissionSavedInfo
import me.kartdroid.androidkitchen2.subscription.models.DurationInfo
import me.kartdroid.androidkitchen2.subscription.models.EligibleConsumptionInfo
import me.kartdroid.androidkitchen2.subscription.models.PurchaseProgressInfo
import me.kartdroid.androidkitchen2.subscription.models.PurchaseProgressedSubscription
import me.kartdroid.androidkitchen2.subscription.models.PurchaseTransactionStatus
import me.kartdroid.androidkitchen2.subscription.models.PurchasedSubscriptionV2
import me.kartdroid.androidkitchen2.subscription.models.RuleType
import me.kartdroid.androidkitchen2.subscription.models.SubsConsumptionInfo
import me.kartdroid.androidkitchen2.subscription.models.SubsExpiryInfo
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfo
import me.kartdroid.androidkitchen2.subscription.models.ValidityTagInfo

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 26/04/24
 */

class PurchaseProgressedSubscriptionPreviewProvider : PreviewParameterProvider<PurchaseProgressedSubscription> {
    override val values: Sequence<PurchaseProgressedSubscription> = sequenceOf(
        ActivatedSubscriptionV2(
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
                savedAmountLabel = "₹258",
                caption = "saved so far"
            ),
        ),
        PurchasedSubscriptionV2(
            title = "Weekend Special",
            ruleType = RuleType.VARIABLE_COMMISSION,
            eligibleConsumptionInfo = EligibleConsumptionInfo(
                typeLabel = "Rides",
                unitsLabel = "20",
                units = 20f,
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
                tag = SubscriptionInfo.ValidityTag.STARTS_SOON,
                label = "Starts Soon"
            ),
            purchaseProgressInfo = PurchaseProgressInfo(
                purchaseProgressLabel = "₹500 paid",
                transactionStatus = PurchaseTransactionStatus.PROCESSING,
            ),
        )
    )


}