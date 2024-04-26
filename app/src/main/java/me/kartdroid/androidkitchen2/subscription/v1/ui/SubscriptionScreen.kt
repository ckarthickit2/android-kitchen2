package me.kartdroid.androidkitchen2.subscription.v1.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.rapido.presentation.model.IFaqContext
import com.rapido.rapidodesignsystem.components.bottomsheet.RdsBottomSheetScaffold
import com.rapido.rapidodesignsystem.components.bottomsheet.RdsGenericBottomSheet
import com.rapido.rapidodesignsystem.components.button.HelpToolbarButton
import com.rapido.rapidodesignsystem.components.toolbar.TopAppBarWithSubtitle
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rider.subscriptions.presentation.ui.composables.RechargeSuccessBottomSheet
import kotlinx.coroutines.launch
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.subscription.models.PurchasedSubscriptionInfo
import me.kartdroid.androidkitchen2.subscription.models.PurchasedSubscriptionInfo.Companion.EMPTY_PURCHASED_SUBSCRIPTION_INFO
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfo
import me.kartdroid.androidkitchen2.subscription.state.SubscriptionsBottomSheetUi
import me.kartdroid.androidkitchen2.subscription.state.SubscriptionsUiState
import me.kartdroid.androidkitchen2.subscription.v1.ui.preview.SubsScreenPreviewParameterProvider
import me.kartdroid.androidkitchen2.ui.error.ErrorScreen
import me.kartdroid.androidkitchen2.ui.error.ErrorType
import me.kartdroid.androidkitchen2.utils.toFormattedString

/**
 * Created by Abhishek Raj on 31/05/23.
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SubscriptionScreen(
        state: SubscriptionsUiState,
        onBackPressed: () -> Unit,
        onHelpClicked: (IFaqContext) -> Unit,
        retrySubscriptionsFetch: () -> Unit,
        toolbarTextFromIntent: String,
        source: String,
        onCardSelected: (SubscriptionInfo.Subscription) -> Unit,
        onBuy: (SubscriptionInfo.Subscription) -> Unit,
        onShowConfirmationBottomSheet: (SubscriptionInfo, SubscriptionInfo.Subscription) -> Unit,
        purchasedSubscriptionInfo: PurchasedSubscriptionInfo,
        onRechargeSuccessBottomSheetDismissed: () -> Unit,
        initSubscriptions: () -> Unit,
        onPurchaseConfirmationBottomSheetVisible: () -> Unit,
        onPurchaseConfirmationBottomSheetHide: (action: String?) -> Unit
) {

    val bottomSheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden
    )
    LaunchedEffect(key1 = purchasedSubscriptionInfo) {
        if (purchasedSubscriptionInfo!=EMPTY_PURCHASED_SUBSCRIPTION_INFO) {
            bottomSheetState.show()
        }
    }
    LaunchedEffect(key1 = Unit) {
        initSubscriptions()
    }
    LaunchedEffect(key1 = state) {
        if (purchasedSubscriptionInfo!=EMPTY_PURCHASED_SUBSCRIPTION_INFO) return@LaunchedEffect
        val showBottomSheet = state is SubscriptionsUiState.RenderSubscriptions && state.subscriptionsBottomSheetUi!=SubscriptionsBottomSheetUi.NONE
        if (showBottomSheet) {
            bottomSheetState.show()
        } else {
            bottomSheetState.hide()
        }
    }

    val coroutineScope = rememberCoroutineScope()

    val subsConfirmationSheetVisible = state is SubscriptionsUiState.RenderSubscriptions &&
            state.subscriptionsBottomSheetUi is SubscriptionsBottomSheetUi.SubscriptionConfirmationBottomSheet

    BackHandler(enabled = bottomSheetState.isVisible) {
        coroutineScope.launch {
            if (bottomSheetState.isVisible) {
                bottomSheetState.hide()
                if (subsConfirmationSheetVisible) {
                    onPurchaseConfirmationBottomSheetHide("go_back")
                }
            }
        }
    }

    RdsBottomSheetScaffold(
            bottomSheetState = bottomSheetState,
            onSheetHide = {
                onRechargeSuccessBottomSheetDismissed()
                onPurchaseConfirmationBottomSheetHide(null)
            },
            sheetContent = {
                if (purchasedSubscriptionInfo!=EMPTY_PURCHASED_SUBSCRIPTION_INFO) {
                    RechargeSuccessBottomSheet(
                            Modifier, purchasedSubscriptionInfo.successMessage, purchasedSubscriptionInfo.subscription.validityInfoDescription, purchasedSubscriptionInfo.subscription.passPurchaseDescription,
                            onCtaClick = {
                                coroutineScope.launch {
                                    if (bottomSheetState.isVisible) {
                                        bottomSheetState.hide()
                                        onPurchaseConfirmationBottomSheetHide(null)
                                    }
                                }
                            }
                    )
                } else {
                    when (state) {
                        is SubscriptionsUiState.RenderSubscriptions -> {
                            if (state.subscriptionsBottomSheetUi is SubscriptionsBottomSheetUi.SubscriptionConfirmationBottomSheet) {
                                val payAmount = state.subscriptionsBottomSheetUi.confirmationBottomSheet.purchasePrice.toFormattedString()
                                val purchaseDescription = state.subscriptionsBottomSheetUi.confirmationBottomSheet.passPurchaseDescription
                                RdsGenericBottomSheet(
                                        title = String.format(state.subscriptionInfo.bottomSheetTitle, payAmount),
                                        description = purchaseDescription.ifEmpty { null },
                                        primaryButtonText = stringResource(id = R.string.proceed_to_pay_rs_x, payAmount),
                                        onClickPrimaryButton = {
                                            coroutineScope.launch {
                                                if (bottomSheetState.isVisible) {
                                                    bottomSheetState.hide()
                                                }
                                                onBuy(state.subscriptionsBottomSheetUi.confirmationBottomSheet)
                                            }
                                        },
                                        secondaryButtonText = stringResource(id = R.string.cancel),
                                        onClickSecondaryButton = {
                                            coroutineScope.launch {
                                                if (bottomSheetState.isVisible) {
                                                    bottomSheetState.hide()
                                                    onPurchaseConfirmationBottomSheetHide(null)
                                                }
                                            }
                                        },
                                        onDismiss = {
                                            coroutineScope.launch {
                                                if (bottomSheetState.isVisible) {
                                                    bottomSheetState.hide()
                                                    onPurchaseConfirmationBottomSheetHide("dismiss")
                                                }
                                            }
                                        }
                                )
                                LaunchedEffect(key1 = Unit) {
                                    onPurchaseConfirmationBottomSheetVisible()
                                }
                            }
                        }

                        else -> {
                            // Do Nothing
                        }
                    }
                }
            },
            topBar = {
                if (state is SubscriptionsUiState.Loading) {
                    LoadingToolbar(onBackPressed = onBackPressed)
                } else {
                    TopAppBarWithSubtitle(
                            modifier = Modifier,
                            actionIcon = R.drawable.ic_keyboard_back,
                            toolbarText = getToolbarTitle(state, toolbarTextFromIntent),
                            actionOnClick = onBackPressed,
                            trailingContent = {
                                if (state is SubscriptionsUiState.RenderSubscriptions) {
                                    HelpToolbarButton(
                                            modifier = Modifier,
                                            IFaqContext.FaqContext(
                                                    state.subscriptionInfo.extraInfo.helpInfo.context,
                                                    state.subscriptionInfo.extraInfo.helpInfo.title
                                            ),
                                            onHelpClicked = onHelpClicked,
                                            text = state.subscriptionInfo.extraInfo.helpInfo.title
                                    )
                                }
                            },
                            elevation = 2.dp,
                    )
                }
            },
            content = {
                Box(
                        modifier = Modifier.fillMaxSize()
                ) {
                    when (state) {
                        is SubscriptionsUiState.Error -> {
                            when (state.errorType) {
                                ErrorType.NETWORK -> {
                                    ErrorScreen(
                                            type = ErrorType.NETWORK,
                                            onRetry = retrySubscriptionsFetch
                                    )
                                }

                                ErrorType.SERVER -> {
                                    val messageStringRes = state.message
                                    ErrorScreen(
                                            type = ErrorType.SERVER,
                                            onRetry = retrySubscriptionsFetch,
                                            description = messageStringRes
                                    )
                                }

                                is ErrorType.OTHER -> {}
                            }
                        }

                        SubscriptionsUiState.Idle -> {
                        }

                        SubscriptionsUiState.Loading -> {
                            LoadingSubscription()
                        }

                        is SubscriptionsUiState.PurchaseSubscription -> {
                            OrderInProgress()
                        }

                        is SubscriptionsUiState.RenderSubscriptions -> {
                            SubscriptionScreenInfo(
                                    Modifier,
                                    state.subscriptionInfo,
                                    onCardSelected = { selectedSubscription ->
                                        onCardSelected(selectedSubscription)
                                    },
                                    state.selectedSubscription
                            )
                        }
                    }
                }
            },
            footer = {
                if (state is SubscriptionsUiState.RenderSubscriptions) {
                    if (showSubscriptionCta(state.subscriptionInfo, state.selectedSubscription)) {
                        SubscriptionCtaCard(
                                modifier = Modifier.wrapContentSize(),
                                subscription = state.selectedSubscription,
                                extraInfo = state.subscriptionInfo.extraInfo,
                                onBuyClick = {
                                    onShowConfirmationBottomSheet(state.subscriptionInfo, it)
                                },
                                ctaText = state.subscriptionInfo.ctaText
                        )
                    }
                }
            }
    )
}

@Composable
private fun getToolbarTitle(state: SubscriptionsUiState, toolbarTextFromIntent: String) =
        if (state is SubscriptionsUiState.RenderSubscriptions) {
            state.subscriptionInfo.toolbarTitle.ifBlank {
                toolbarTextFromIntent
            }
        } else {
            toolbarTextFromIntent
        }

@Composable
private fun showSubscriptionCta(
        subscriptionInfo: SubscriptionInfo,
        selectedSubscription: SubscriptionInfo.Subscription
): Boolean {
    return selectedSubscription!=SubscriptionInfo.Subscription.EMPTY_SUBSCRIPTION && subscriptionInfo.availableSubscriptions.subscriptions.isNotEmpty()
}

@Preview(device = Devices.PIXEL_2_XL, showSystemUi = false)
@Composable
fun PreviewSubscriptionScreen(@PreviewParameter(SubsScreenPreviewParameterProvider::class) state: SubscriptionsUiState) {
    RapidoTheme {
        SubscriptionScreen(
                state = state,
                onBackPressed = {
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
