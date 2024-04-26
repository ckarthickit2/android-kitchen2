package me.kartdroid.androidkitchen2.subscription.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.rapido.rapidodesignsystem.components.icon.RdsIconDrawableConfig
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfo

/**
 * Created by Abhishek Raj on 22/06/23.
 */

fun SubscriptionInfo.ValidityTag.getTagColor(): Color {
    return when (this) {
        SubscriptionInfo.ValidityTag.STARTS_SOON -> RdsColors.purpleLight5
        SubscriptionInfo.ValidityTag.ACTIVE -> RdsColors.greenDark1
        SubscriptionInfo.ValidityTag.EXPIRING_SOON -> RdsColors.lightRed5
        SubscriptionInfo.ValidityTag.EXPIRED -> RdsColors.darkRed1
        SubscriptionInfo.ValidityTag.NO_TAG -> RdsColors.transparent
    }
}

fun SubscriptionInfo.ValidityTag.getTagColorString(): Int {
    return when (this) {
        SubscriptionInfo.ValidityTag.STARTS_SOON -> RdsColors.purpleLight5.toNativeColor()
        SubscriptionInfo.ValidityTag.ACTIVE -> RdsColors.greenDark1.toNativeColor()
        SubscriptionInfo.ValidityTag.EXPIRING_SOON -> RdsColors.lightRed5.toNativeColor()
        SubscriptionInfo.ValidityTag.EXPIRED -> RdsColors.darkRed1.toNativeColor()
        SubscriptionInfo.ValidityTag.NO_TAG -> RdsColors.transparent.toNativeColor()
    }
}

fun Color.toNativeColor(): Int {
    return android.graphics.Color.argb(
        this.toArgb().alpha,
        this.toArgb().red,
        this.toArgb().green,
        this.toArgb().blue
    )
}

fun SubscriptionInfo.ValidityTag.getTagText(): Int {
    return when (this) {
        SubscriptionInfo.ValidityTag.STARTS_SOON -> R.string.starts_soon_status
        SubscriptionInfo.ValidityTag.ACTIVE -> R.string.active_status
        SubscriptionInfo.ValidityTag.EXPIRING_SOON -> R.string.expiring_soon_status
        SubscriptionInfo.ValidityTag.EXPIRED -> R.string.expired_status
        SubscriptionInfo.ValidityTag.NO_TAG -> R.string.no
    }
}

fun SubscriptionInfo.ValidityTag.getTagIcon(): RdsIconDrawableConfig {
    return when (this) {
        SubscriptionInfo.ValidityTag.STARTS_SOON -> RdsIconDrawableConfig(drawable = R.drawable.timer)
        SubscriptionInfo.ValidityTag.ACTIVE -> RdsIconDrawableConfig(drawable = R.drawable.ic_check_circle)
        SubscriptionInfo.ValidityTag.EXPIRING_SOON -> RdsIconDrawableConfig(drawable = R.drawable.ic_check_circle)
        SubscriptionInfo.ValidityTag.EXPIRED -> RdsIconDrawableConfig(drawable = R.drawable.cross)
        SubscriptionInfo.ValidityTag.NO_TAG -> RdsIconDrawableConfig(drawable = R.drawable.ic_check_circle)
    }
}

fun SubscriptionInfo.ValidityTag.getTagIcon(context: Context): Drawable? {
    return when (this) {
        SubscriptionInfo.ValidityTag.STARTS_SOON -> ContextCompat.getDrawable(context, R.drawable.timer)
        SubscriptionInfo.ValidityTag.ACTIVE -> ContextCompat.getDrawable(context, R.drawable.ic_check_circle)
        SubscriptionInfo.ValidityTag.EXPIRING_SOON -> ContextCompat.getDrawable(context, R.drawable.ic_check_circle)
        SubscriptionInfo.ValidityTag.EXPIRED -> ContextCompat.getDrawable(context, R.drawable.cross)
        SubscriptionInfo.ValidityTag.NO_TAG -> ContextCompat.getDrawable(context, R.drawable.ic_check_circle)
    }
}
