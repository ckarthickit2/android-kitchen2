package me.kartdroid.androidkitchen2.subscription.v2.ui.preview

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rapido.rapidodesignsystem.colors.RapidoThemeColors
import com.rapido.rapidodesignsystem.theme.RapidoDefaultOrderColors
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
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

val greenTheme = RapidoDefaultOrderColors.copy(
    secondaryContainer = RdsColors.green2,
    onSecondaryContainer = RdsColors.white,
    onSurfaceVariant = RdsColors.greenDark500,
    onSurfaceDimVariant = RdsColors.green200,
    secondarySurface = RdsColors.greenLight,
)

val orangeTheme = RapidoDefaultOrderColors.copy(
    secondaryContainer = Color(0xFFD16329),
    onSecondaryContainer = RdsColors.white,
    onSurfaceVariant = Color(0xFFBF4D0C),
    onSurfaceDimVariant = Color(0xFFF4B68F),
    secondarySurface = Color(0xFFFFF6EF),
)

val redTheme = RapidoDefaultOrderColors.copy(
    secondaryContainer = Color(0xFFD03B2A),
    onSecondaryContainer = RdsColors.white,
    onSurfaceVariant = Color(0xFFAA2D1F),
    onSurfaceDimVariant = Color(0xFFF5A69D),
    secondarySurface = Color(0xFFFFF2F1),
)

val blueTheme = RapidoDefaultOrderColors.copy(
    secondaryContainer = Color(0xFF146EDC),
    onSecondaryContainer = RdsColors.white,
    onSurfaceVariant = Color(0xFF146EDC),
    onSurfaceDimVariant = Color(0xFFA0C8F9),
    secondarySurface = Color(0xFFEEF5FE),
)

val redBlueTheme = RapidoDefaultOrderColors.copy(
    secondaryContainer = Color(0xFFD03B2A),
    onSecondaryContainer = RdsColors.white,
    onSurfaceVariant = Color(0xFF146EDC),
    onSurfaceDimVariant = Color(0xFFA0C8F9),
    secondarySurface = Color(0xFFEEF5FE),
)

class PurchaseProgressedSubscriptionPreviewProvider : PreviewParameterProvider<Pair<PurchaseProgressedSubscription, RapidoThemeColors>> {
    override val values: Sequence<Pair<PurchaseProgressedSubscription, RapidoThemeColors>> = sequenceOf(
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
                savedAmountLabel = "34",
                caption = "saved so far"
            ),
        ) to greenTheme,
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
                currentPrice = "₹125",
                strikeOutPrice = "₹250"
            ),
            validityTagInfo = ValidityTagInfo(
                tag = SubscriptionInfo.ValidityTag.EXPIRING_SOON,
                label = "Expiring Soon",
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
        ) to orangeTheme,
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
                currentPrice = "₹125",
                strikeOutPrice = "₹250"
            ),
            validityTagInfo = ValidityTagInfo(
                tag = SubscriptionInfo.ValidityTag.EXPIRED,
                label = "Expired",
            ),
            purchaseProgressInfo = PurchaseProgressInfo(
                purchaseProgressLabel = "₹124 Paid",
                transactionStatus = PurchaseTransactionStatus.COMPLETE,
            ),
            consumptionInfo = SubsConsumptionInfo(
                consumedUnitsLabel = "9000",
                consumedUnits = 9000f
            ),
            expiryInfo = SubsExpiryInfo(
                dateLabel = "Until 12/04/24",
                timeLabel = "10:00 am"
            ),
            commissionSavedInfo = CommissionSavedInfo(
                savedAmountLabel = "₹458",
                caption = "saved so far"
            ),
        ) to redTheme,
        PurchasedSubscriptionV2(
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