package me.kartdroid.androidkitchen2.subscription.models

import java.math.BigDecimal

data class SubscriptionInfo(
    val toolbarTitle: String,
    val bannerInfo: BannerInfo,
    val activeSubscriptions: SubscriptionDetails,
    val purchasedSubscriptions: SubscriptionDetails,
    val availableSubscriptions: SubscriptionDetails,
    val tncInfo: List<String>,
    val extraInfo: ExtraInfo,
    val ctaText: String,
    val bottomSheetTitle: String
) {
    data class BannerInfo(
        val bannerImageUrl: String,
        val headingText: String,
        val descriptionText: String
    )

    data class SubscriptionDetails(
        val nudgeText: String,
        val subscriptions: List<Subscription>
    )

    data class Subscription(
        val subscriptionId: String,
        val title: String,
        val description: String,
        val validityInfoDescription: String,
        val validityTag: ValidityTag,
        val actualPrice: Double,
        val amountAfterDiscount: Double,
        val purchasePrice: BigDecimal,
        val planType: String,
        val ruleType: String,
        val discountType: String,
        val discountValue: Double,
        val passPurchaseDescription: String = ""
    ) {
        companion object {
            const val RULE_TYPE_ZERO_COMMISSION = "zeroCommission"
            const val RULE_TYPE_VARIABLE_COMMISSION = "variableCommission"
            val EMPTY_SUBSCRIPTION = Subscription(
                subscriptionId = "",
                title = "",
                description = "",
                validityInfoDescription = "",
                validityTag = ValidityTag.NO_TAG,
                actualPrice = -1.0,
                amountAfterDiscount = -1.0,
                purchasePrice = BigDecimal("-1.0"),
                planType = "",
                ruleType = "",
                discountType = "",
                discountValue = 0.0,
                passPurchaseDescription = ""
            )
        }
    }

    data class ExtraInfo(
        val gstPercentage: Float,
        val helpInfo: HelpInfo,
        val confirmationPopupTitle: String,
    )

    data class HelpInfo(
        val title: String,
        val context: String
    )


    enum class ValidityTag {
        PROCESSING, STARTS_SOON, ACTIVE, EXPIRING_SOON, EXPIRED, NO_TAG;

        companion object {
            private val map = values().associateBy { it.name.lowercase() }

            fun fromString(tagString: String): ValidityTag {
                return map[tagString.lowercase()] ?: NO_TAG
            }
        }
    }
}


data class PurchasedSubscriptionInfo(
    val subscription: SubscriptionInfo.Subscription,
    val successMessage: String
) {
    companion object {
        val EMPTY_PURCHASED_SUBSCRIPTION_INFO =
            PurchasedSubscriptionInfo(SubscriptionInfo.Subscription.EMPTY_SUBSCRIPTION, "")
    }
}
