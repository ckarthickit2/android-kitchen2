package me.kartdroid.androidkitchen2.subscription.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import me.kartdroid.androidkitchen2.subscription.models.PurchasedSubscriptionInfo
import me.kartdroid.androidkitchen2.subscription.v1.ui.SubscriptionScreen
import me.kartdroid.androidkitchen2.subscription.v1.ui.preview.SubsScreenPreviewParameterProvider
import me.kartdroid.androidkitchen2.utils.logDebug

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 26/04/24
 */
class SubscriptionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logDebug("Main ::onCreate")
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContent {
            Content()
        }
    }

    @Composable
    fun Content() {
        val state = SubsScreenPreviewParameterProvider().values.toList()[2]
        SubscriptionScreen(
                state = state,
                onBackPressed = {
                                finish()
                },
                onHelpClicked = {
                },
                retrySubscriptionsFetch = {
                },
                toolbarTextFromIntent = "",
                source = "",
                onCardSelected = {
                },
                onBuy = {
                },
                purchasedSubscriptionInfo = PurchasedSubscriptionInfo.EMPTY_PURCHASED_SUBSCRIPTION_INFO,
                onRechargeSuccessBottomSheetDismissed = {},
                initSubscriptions = {},
                onShowConfirmationBottomSheet = { a, b -> {} },
                onPurchaseConfirmationBottomSheetHide = {},
                onPurchaseConfirmationBottomSheetVisible = {}
        )
    }
}