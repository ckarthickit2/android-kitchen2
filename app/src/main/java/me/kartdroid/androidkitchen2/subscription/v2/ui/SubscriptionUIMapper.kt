package me.kartdroid.androidkitchen2.subscription.v2.ui

import androidx.annotation.DrawableRes
import com.rapido.rapidodesignsystem.colors.RapidoThemeColors
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.subscription.models.PurchaseProgressedSubscription
import me.kartdroid.androidkitchen2.subscription.models.PurchaseTransactionStatus
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfo
import me.kartdroid.androidkitchen2.subscription.models.ValidityTagInfo
import me.kartdroid.androidkitchen2.subscription.v2.ui.preview.blueTheme
import me.kartdroid.androidkitchen2.subscription.v2.ui.preview.greenTheme
import me.kartdroid.androidkitchen2.subscription.v2.ui.preview.orangeTheme
import me.kartdroid.androidkitchen2.subscription.v2.ui.preview.redBlueTheme
import me.kartdroid.androidkitchen2.subscription.v2.ui.preview.redTheme

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 27/04/24
 */

@DrawableRes
fun ValidityTagInfo.indicatorDrawable(): Int {
    return when (tag) {
        SubscriptionInfo.ValidityTag.ACTIVE -> R.drawable.ic_check_circle_green
        SubscriptionInfo.ValidityTag.STARTS_SOON -> R.drawable.ic_right_turn
        SubscriptionInfo.ValidityTag.PROCESSING -> R.drawable.ic_processing
        SubscriptionInfo.ValidityTag.EXPIRING_SOON, SubscriptionInfo.ValidityTag.EXPIRED -> R.drawable.ic_info
        else -> R.drawable.ic_circular_shape_black
    }
}


@DrawableRes
fun PurchaseTransactionStatus.indicatorDrawable(): Int {
    return when (this) {
        PurchaseTransactionStatus.COMPLETE -> R.drawable.double_tick
        PurchaseTransactionStatus.PROCESSING -> R.drawable.ic_blue_tick
        PurchaseTransactionStatus.FAILED -> R.drawable.ic_info
    }
}


fun PurchaseProgressedSubscription.theme(): RapidoThemeColors {
    return when (this.validityTagInfo.tag) {
        SubscriptionInfo.ValidityTag.ACTIVE -> greenTheme
        SubscriptionInfo.ValidityTag.STARTS_SOON -> blueTheme
        SubscriptionInfo.ValidityTag.PROCESSING -> blueTheme
        SubscriptionInfo.ValidityTag.EXPIRING_SOON -> orangeTheme
        SubscriptionInfo.ValidityTag.EXPIRED -> redTheme
        else -> {
            if (this.purchaseProgressInfo.transactionStatus==PurchaseTransactionStatus.FAILED) {
                redBlueTheme
            } else {
                blueTheme
            }
        }
    }
}