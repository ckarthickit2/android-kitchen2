package me.kartdroid.androidkitchen2.subscription.state

import androidx.annotation.StringRes
import com.rapido.presentation.model.IFaqContext
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfo
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfo.Subscription
import me.kartdroid.androidkitchen2.ui.error.ErrorType

sealed interface SubscriptionsUiState {

    object Idle : SubscriptionsUiState

    object Loading : SubscriptionsUiState

    data class RenderSubscriptions(
            val subscriptionInfo: SubscriptionInfo,
            val selectedSubscription: Subscription,
            val subscriptionsBottomSheetUi: SubscriptionsBottomSheetUi
    ) : SubscriptionsUiState

    data class PurchaseSubscription(
            val subscriptionInfo: SubscriptionInfo,
            val subscription: Subscription,
            val transactionId: String = "",
            val paymentId: String = "",
            val retryCount: Int = 0
    ) : SubscriptionsUiState

    data class Error(
            val errorType: ErrorType,
            @StringRes val message: Int? = null
    ) : SubscriptionsUiState
}

sealed interface SubscriptionsBottomSheetUi {
    object NONE : SubscriptionsBottomSheetUi

    data class SubscriptionConfirmationBottomSheet(val subscriptionInfo: SubscriptionInfo, val confirmationBottomSheet: Subscription) :
            SubscriptionsBottomSheetUi
}

sealed interface SubscriptionsUiSideEffects {
    class RechargeWalletForSubscription(val subscription: Subscription, val transactionId: String, val razorPayChannelName: String, val razorPayKey: String) :
            SubscriptionsUiSideEffects

    data class NavigateToHelp(val iFaqContext: IFaqContext) : SubscriptionsUiSideEffects

    data class SubscriptionPurchaseSuccess(
            val subscription: Subscription,
            val successMessage: String
    ) : SubscriptionsUiSideEffects
}

sealed class Event {

    object FetchSubscriptions : Event()

    class GeneralErrorEvent(val type: ErrorType, @StringRes val message: Int? = null) : Event()

    class FetchSubscriptionsSuccess(val subscriptionInfo: SubscriptionInfo, val defaultSelectedSubscription: Subscription) : Event()

    class BuySubscription(val subscription: Subscription) : Event()

    class WalletRechargeRequired(val transactionId: String, val razorPayChannelName: String, val razorPayKey: String) : Event()

    class WalletRechargeSuccessful(val paymentId: String) : Event()

    class SubscriptionCardSelected(val selectedSubscription: Subscription) : Event()

    class RetryBuySubscription(val retryCount: Int) : Event()

    class ShowConfirmationBottomSheet(val subscriptionInfo: SubscriptionInfo, val subscription: Subscription) : Event()

    object HideBottomSheet : Event()
}

sealed class SideEffects {

    data class FetchSubscriptions(val selectDefaultSubscription: Boolean) : SideEffects()

    data class RechargeWallet(val subscription: Subscription, val transactionId: String, val razorPayChannelName: String, val razorPayKey: String) : SideEffects()

    data class BuySubscription(
        val subscription: Subscription,
        val transactionId: String = "",
        val paymentId: String = "",
        val retryCount: Int = 0
    ) : SideEffects()
}


