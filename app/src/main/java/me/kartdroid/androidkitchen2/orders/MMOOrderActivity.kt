package me.kartdroid.androidkitchen2.orders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.presentation.UIImageResource
import me.kartdroid.androidkitchen2.presentation.UiText
import me.kartdroid.androidkitchen2.ui.theme.AndroidKitchen2Theme
import me.kartdroid.androidkitchen2.ui.theme.KitchenDefaultOrderColorsDark
import me.kartdroid.androidkitchen2.ui.theme.KitchenDefaultOrderColorsLight
import me.kartdroid.androidkitchen2.ui.theme.LocalKitchenColors
import me.kartdroid.androidkitchen2.ui.theme.RdsColors
import me.kartdroid.androidkitchen2.utils.logDebug

/**
 * @author [Karthick Chinnathambi](https://github.com/kartdroid)
 * @since 01/09/23
 */
class MMOOrderActivity : ComponentActivity() {

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
        Order(
                order = MOCK_ITEM,
                onOrderClicked = {},
                onAcceptOrder = {},
                onRejectOrder = { finish() })
    }


    @Composable
    private fun Order(
            order: MultiOrderUiItem,
            onOrderClicked: (orderId: MultiOrderUiItem) -> Unit,
            onAcceptOrder: (orderId: MultiOrderUiItem) -> Unit,
            onRejectOrder: (orderId: String) -> Unit,
    ) {
        AndroidKitchen2Theme {
            var columnHeight by remember { mutableStateOf(0) }
            Box(
                    modifier = Modifier
                            .padding(8.dp)
                            .background(color = RdsColors.transparent)
                            .onSizeChanged {
                                columnHeight = it.height
                            }
            ) {
                CompositionLocalProvider(
                        LocalKitchenColors provides if (isSystemInDarkTheme()) {
                            KitchenDefaultOrderColorsLight
                        } else {
                            KitchenDefaultOrderColorsDark
                        }
                ) {
                    var modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .shadow(elevation = 6.dp)
                    if (order.isOnRideBooking.not()) {
                        modifier = modifier.clickable {
                            onOrderClicked(order)
                        }
                    }
                    Column {
                        OrderItem(
                                modifier = modifier,
                                order = order,
                                index = 0,
                                orderListSize = 1,
                                isShowCardPadding = false,
                                isShowDistanceVertical = false,
                                onAcceptOrder = {
                                    onAcceptOrder(order)
                                },
                                onRejectOrder = onRejectOrder
                        ) { columnHeight }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MMOPreview() {
        Content()
    }
}


private val MOCK_ITEM = MultiOrderUiItem(
        "2",
        orderType = "app",
        serviceInfo = ServiceInfo(
                serviceName = UiText.StringResource(R.string.rapido),
                serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_auto_share),
        ),
        pickUpTitle = "Sector 3, HSR Layout",
        pickUpAddress = "#562, 14th main road, Sector 3, HSR Layout",
        dropTitle = "Sector 7, HSR Layout",
        dropAddress = "#562, 14th main road, Sector 3, HSR Layout",
        pickUpDistance = 3000.0,
        dropDistance = 4000.0,
        pickupEtaInMins = 3,
        dropEtaInMins = 4,
        orderCreatedTime = System.currentTimeMillis(),
        captainAcceptTimer = 30,
        timeToEnableAcceptButton = 10,
        isGTAOrder = true,
        amountWithoutExtra = 200,
        extraAmount = 15,
        paymentType = "wallet",
        orderTipAmount = 34,
        isBatchOrder = false,
        unavailableReason = PreOrderUnavailableReason.NONE,
        unavailableAt = -1L,
        acceptedAt = -1L,
        isPAPEnabled = false,
        isOnRideBooking = false,
        propagation = OrderPropagation.UNDEFINED_PROPAGATION,
        isPickupVerified = false,
        amount = 0,
        minutes = 0,
        isBatchedOrder = false,
        extraAmountDisplayProps = ExtraAmountDisplayProps()
)