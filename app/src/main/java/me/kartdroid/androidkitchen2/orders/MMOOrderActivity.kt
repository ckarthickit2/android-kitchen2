package me.kartdroid.androidkitchen2.orders

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.orders.preview.MultiOrderPreviewProvider
import me.kartdroid.androidkitchen2.presentation.UIImageResource
import me.kartdroid.androidkitchen2.presentation.UiText
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
        val mutilOrderList = MultiOrderPreviewProvider().values.first()
        var orderListFeed by remember {
           mutableStateOf(MultiOrderListWrapper(emptyList()))
        }
        LaunchedEffect(Unit) {
            val accList = mutableListOf<MultiOrderUiItem>()
            delay(100)
            for (item in mutilOrderList.orderList.items) {
                accList.add(item)
                Log.i("KC_DEBUG", "orderListFeed = ${accList}")
                orderListFeed = MultiOrderListWrapper(accList)
                delay(1000)
            }
        }
        OrderList(
                orderList = orderListFeed,
                orderThemeColorsMap = mutilOrderList.orderThemeColorsMap,
                lazyListState = rememberLazyListState(),
                updateSelectedOrder = { _, _ -> },
                onAcceptOrder = { _, _ -> },
                onRejectOrder = { },
                logOrdersScroll = { },
                // uiVersion = UiVersion.UI_VERSION_1_0,
                logOnCustomerTipFTuxViewed = { },
                logCustomerTipFTuxClicked = { }
        )
    }


//

    @Preview(showBackground = true)
    @Composable
    fun MMOPreview() {
        Content()
    }
}


private val MOCK_ITEM = MultiOrderUiItem(
        id = "2",
        version = "1.0",
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