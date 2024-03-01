package me.kartdroid.androidkitchen2.orders.preview

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.persistentHashMapOf
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.orders.ExtraAmountDisplayProps
import me.kartdroid.androidkitchen2.orders.MultiOrderListWrapper
import me.kartdroid.androidkitchen2.orders.MultiOrderUiItem
import me.kartdroid.androidkitchen2.orders.OrderPropagation
import me.kartdroid.androidkitchen2.orders.PreOrderUnavailableReason
import me.kartdroid.androidkitchen2.orders.ServiceInfo
import me.kartdroid.androidkitchen2.presentation.UIImageResource
import me.kartdroid.androidkitchen2.presentation.UiText
import me.kartdroid.androidkitchen2.ui.theme.DividerStyle
import me.kartdroid.androidkitchen2.ui.theme.KitchenDefaultOrderColorsDark
import me.kartdroid.androidkitchen2.ui.theme.KitchenThemeColors
import me.kartdroid.androidkitchen2.ui.theme.RdsColors


class RenderMultiOrderListUi(
        val orderList: MultiOrderListWrapper,
        val orderThemeColorsMap: PersistentMap<String, KitchenThemeColors>,
)
class MultiOrderPreviewProvider :
    PreviewParameterProvider<RenderMultiOrderListUi> {
    override val values = sequenceOf(
        RenderMultiOrderListUi(
            orderList = MultiOrderListWrapper(getOrderList()),
            orderThemeColorsMap = getOrderThemeMap(),
        ),
        RenderMultiOrderListUi(
            orderList = MultiOrderListWrapper(getOrderListV2()),
            orderThemeColorsMap = getOrderThemeMap(),
        )
    )
}

private fun getOrderThemeMap(): PersistentMap<String, KitchenThemeColors> {
    val defaultLocalColors = KitchenDefaultOrderColorsDark.copy(
        onPrimaryContainerVariant = RdsColors.green6,
        onSecondaryContainer = Color(0xFFF4F4FA),
        onSurfaceVariant = Color(0xFF1A202A)
    )

    val autoShareLocalColors = KitchenDefaultOrderColorsDark.copy(
        primaryContainer = arrayListOf(RdsColors.blue100, RdsColors.blue100),
        onPrimaryContainer = RdsColors.dark1,
        onPrimaryContainerVariant = RdsColors.dark1,
        secondaryContainer = Color(0xff1A202A),
        secondaryContainerOutline = RdsColors.blue500,
        onSecondaryContainer = Color(0xFFEFF6FF),
        primaryDividerStyle = DividerStyle.NONE,
        onSurfaceVariant = Color(0xFF1A202A)
    )

    val autoPlusLocalColors = KitchenDefaultOrderColorsDark.copy(
        primaryContainer = arrayListOf(Color(0xFF040404), Color(0xFF555454)),
        onPrimaryContainer = RdsColors.white,
        onPrimaryContainerVariant = RdsColors.white,
        surface = RdsColors.white,
        secondaryContainer = Color(0xFFCBAA54),
        secondaryContainerOutline = Color(0xFFCBAA54),
        onSecondaryContainer = Color(0xFF000000),
        primaryDividerStyle = DividerStyle.NONE,
        onSurfaceVariant = Color(0xFF1A202A)
    )

    val defaultGtaLocalColors = KitchenDefaultOrderColorsDark.copy(
        primaryContainer = arrayListOf(Color(0xFFFFACB2), Color(0xFFFFACB2)),
        onPrimaryContainer = RdsColors.dark1,
        onPrimaryContainerVariant = RdsColors.dark1,
        surface = Color(0xFFFFF6F6),
        secondarySurface = Color(0xFFFFF6F6),
        secondaryContainer = RdsColors.contentPrimary,
        secondaryContainerOutline = Color(0xFFFFDBDE),
        onSecondaryContainer = Color(0xFFFFF2F1),
        primaryDividerStyle = DividerStyle.NONE,
        onSurfaceVariant = Color(0xFF1A202A)
    )

    return persistentHashMapOf(
        "default_v2" to defaultLocalColors,
        "default_gta_v2" to defaultGtaLocalColors,
        "auto_share_v2" to autoShareLocalColors,
        "auto_share_gta_v2" to autoShareLocalColors,
        "auto_plus_v2" to autoPlusLocalColors,
        "auto_plus_gta_v2" to autoPlusLocalColors
    )
}

fun getOrderList(): List<MultiOrderUiItem> {
    val list = mutableListOf<MultiOrderUiItem>()
    list.add(
        MultiOrderUiItem(
            "1",
            "1",
            "app",
            serviceInfo = ServiceInfo(
                serviceName = UiText.StringResource(R.string.auto),
                serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_auto_multi_order_indicator_round)
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
            isGTAOrder = false,
            amountWithoutExtra = 200,
            extraAmount = 50,
            paymentType = "wallet",
            orderTipAmount = 0,
            unavailableReason = PreOrderUnavailableReason.NONE,
            unavailableAt = -1L,
            acceptedAt = -1L,
            isPAPEnabled = false,
            isOnRideBooking = false,
            templateName = "default_v2",
            propagation = OrderPropagation.UNDEFINED_PROPAGATION,
            isPickupVerified = false,
            amount = 0,
            minutes = 0,
            isBatchedOrder = false,
            extraAmountDisplayProps = ExtraAmountDisplayProps(),
            callOutBanner = MultiOrderUiItem.CallOutBanner(
                message = "Customer added ₹20 extra"
            )
        )
    )
    list.add(
        MultiOrderUiItem(
            "1",
            "2",
            "auto",
            serviceInfo = ServiceInfo(
                serviceName = UiText.DynamicString("Auto Plus"),
                serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_auto_multi_order_indicator_round)
            ),
            pickUpTitle = "Sector 3, HSR Layout",
            pickUpAddress = "#562, 14th main road, Sector 3, HSR Layout",
            dropTitle = "Sector 7, HSR Layout",
            dropAddress = "",
            pickUpDistance = -1.0,
            dropDistance = -1.0,
            pickupEtaInMins = 3,
            dropEtaInMins = 4,
            orderCreatedTime = System.currentTimeMillis(),
            captainAcceptTimer = 30,
            timeToEnableAcceptButton = 10,
            isGTAOrder = true,
            amountWithoutExtra = 0,
            extraAmount = 15,
            paymentType = "wallet",
            orderTipAmount = 34,
            unavailableReason = PreOrderUnavailableReason.NONE,
            unavailableAt = -1L,
            acceptedAt = -1L,
            isPAPEnabled = false,
            isOnRideBooking = false,
            templateName = "auto_plus_v2",
            propagation = OrderPropagation.UNDEFINED_PROPAGATION,
            isPickupVerified = false,
            amount = 0,
            minutes = 0,
            isBatchedOrder = false,
            extraAmountDisplayProps = ExtraAmountDisplayProps()
        )
    )
    list.add(
        MultiOrderUiItem(
            "1",
            "4",
            "auto",
            serviceInfo = ServiceInfo(
                serviceName = UiText.StringResource(R.string.text_bike),
                serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_bike_multi_order_indicator_round)
            ),
            pickUpTitle = "Sector 3, HSR Layout",
            pickUpAddress = "#562, 14th main road, Sector 3, HSR Layout",
            dropTitle = "",
            dropAddress = "",
            pickUpDistance = -1.0,
            dropDistance = -1.0,
            pickupEtaInMins = -1,
            dropEtaInMins = -1,
            orderCreatedTime = System.currentTimeMillis(),
            captainAcceptTimer = 30,
            timeToEnableAcceptButton = 10,
            isGTAOrder = false,
            amountWithoutExtra = 200,
            extraAmount = 15,
            paymentType = "wallet",
            orderTipAmount = 34,
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
            templateName = "default_gta_v2",
            extraAmountDisplayProps = ExtraAmountDisplayProps()
        )
    )
    list.add(
        MultiOrderUiItem(
            "1",
            "6",
            "auto",
            serviceInfo = ServiceInfo(
                serviceName = UiText.StringResource(R.string.auto),
                serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_auto_multi_order_indicator_round)
            ),
            pickUpTitle = "Sector 3, HSR Layout",
            pickUpAddress = "#562, 14th main road, Sector 3, HSR Layout",
            dropTitle = "",
            dropAddress = "",
            pickUpDistance = 3000.0,
            dropDistance = -1.0,
            pickupEtaInMins = 3,
            dropEtaInMins = -1,
            orderCreatedTime = System.currentTimeMillis(),
            captainAcceptTimer = 30,
            timeToEnableAcceptButton = 10,
            isGTAOrder = true,
            amountWithoutExtra = 0,
            extraAmount = 0,
            paymentType = "wallet",
            orderTipAmount = 34,
            unavailableReason = PreOrderUnavailableReason.NONE,
            unavailableAt = -1L,
            acceptedAt = -1L,
            isPAPEnabled = false,
            isOnRideBooking = false,
            propagation = OrderPropagation.UNDEFINED_PROPAGATION,
            isPickupVerified = false,
            amount = 0,
            minutes = 0,
            templateName = "default_v2",
            isBatchedOrder = false,
            extraAmountDisplayProps = ExtraAmountDisplayProps()
        )
    )
    return list
}

fun getOrderListV2(): List<MultiOrderUiItem> {
    return listOf(
        MultiOrderUiItem(
            "1",
            "3",
            "auto",
            serviceInfo = ServiceInfo(
                serviceName = UiText.StringResource(R.string.auto_share),
                serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_auto_share)
            ),
            pickUpTitle = "Sector 3, HSR Layout",
            pickUpAddress = "#562, 14th main road, Sector 3, HSR Layout",
            dropTitle = "Sector 7, HSR Layout",
            dropAddress = "#562, 14th main road, Sector 3, HSR Layout",
            pickUpDistance = -1.0,
            dropDistance = 4000.0,
            pickupEtaInMins = -1,
            dropEtaInMins = 4,
            orderCreatedTime = System.currentTimeMillis(),
            captainAcceptTimer = 30,
            timeToEnableAcceptButton = 10,
            isGTAOrder = false,
            amountWithoutExtra = 50,
            extraAmount = 0,
            paymentType = "",
            orderTipAmount = 0,
            unavailableReason = PreOrderUnavailableReason.NONE,
            unavailableAt = -1L,
            acceptedAt = -1L,
            isPAPEnabled = false,
            isOnRideBooking = false,
            templateName = "auto_share_v2",
            batchOrderSize = 3,
            propagation = OrderPropagation.UNDEFINED_PROPAGATION,
            isPickupVerified = false,
            amount = 0,
            minutes = 0,
            isBatchedOrder = false,
            extraAmountDisplayProps = ExtraAmountDisplayProps()
        ),
        MultiOrderUiItem(
            "1",
            "5",
            "auto",
            serviceInfo = ServiceInfo(
                serviceName = UiText.StringResource(R.string.auto_share),
                serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_auto_share)
            ),
            pickUpTitle = "Sector 3, HSR Layout",
            pickUpAddress = "#562, 14th main road, Sector 3, HSR Layout",
            dropTitle = "",
            dropAddress = "#562, 14th main road, Sector 3, HSR Layout",
            pickUpDistance = 3000.0,
            dropDistance = 4000.0,
            pickupEtaInMins = 3,
            dropEtaInMins = 4,
            orderCreatedTime = System.currentTimeMillis(),
            captainAcceptTimer = 30,
            timeToEnableAcceptButton = 10,
            isGTAOrder = true,
            amountWithoutExtra = 0,
            extraAmount = 15,
            paymentType = "wallet",
            orderTipAmount = 0,
            unavailableReason = PreOrderUnavailableReason.NONE,
            unavailableAt = -1L,
            acceptedAt = -1L,
            isPAPEnabled = false,
            isOnRideBooking = false,
            propagation = OrderPropagation.UNDEFINED_PROPAGATION,
            isPickupVerified = false,
            amount = 0,
            minutes = 0,
            templateName = "auto_share_v2",
            isBatchedOrder = true,
            batchOrderSize = 5,
            extraAmountDisplayProps = ExtraAmountDisplayProps()
        ),
        MultiOrderUiItem(
            "1",
            "t",
            "app",
            serviceInfo = ServiceInfo(
                serviceName = UiText.StringResource(R.string.auto),
                serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_auto_multi_order_indicator_round)
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
            isGTAOrder = false,
            amountWithoutExtra = 200,
            extraAmount = 50,
            paymentType = "wallet",
            orderTipAmount = 0,
            unavailableReason = PreOrderUnavailableReason.ACCEPTED_BY_OTHER_CAPTAIN,
            unavailableAt = -1L,
            acceptedAt = -1L,
            isPAPEnabled = false,
            isOnRideBooking = false,
            templateName = "default_gta_v2",
            propagation = OrderPropagation.UNDEFINED_PROPAGATION,
            isPickupVerified = false,
            amount = 0,
            minutes = 0,
            isBatchedOrder = false,
            extraAmountDisplayProps = ExtraAmountDisplayProps()
        )
    )
}
