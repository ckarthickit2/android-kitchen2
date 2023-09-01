package me.kartdroid.androidkitchen2.orders

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.presentation.UIImageResource
import me.kartdroid.androidkitchen2.presentation.UiText
import me.kartdroid.androidkitchen2.ui.theme.AndroidKitchen2Theme
import me.kartdroid.androidkitchen2.ui.theme.DividerStyle
import me.kartdroid.androidkitchen2.ui.theme.RapidoTheme
import me.kartdroid.androidkitchen2.ui.theme.RdsColors
import me.kartdroid.androidkitchen2.utils.addTestTag
import java.text.DecimalFormat

@Composable
fun OrderItem(
    modifier: Modifier = Modifier,
    order: MultiOrderUiItem,
    index: Int,
    orderListSize: Int,
    isShowCardPadding: Boolean = true,
    isShowDistanceVertical: Boolean = true,
    onAcceptOrder: (orderId: String) -> Unit,
    onRejectOrder: (orderId: String) -> Unit,
    lazyColumnSizeProvider: () -> Int,
) {
    var cardHeight by remember { mutableStateOf(0) }
    val isShowOverlay = (order.unavailableReason == PreOrderUnavailableReason.ACCEPTED_BY_OTHER_CAPTAIN || order.unavailableReason == PreOrderUnavailableReason.ORDER_CANCELLED_BY_CUSTOMER)
    val isDistanceOrEtaAvailable = (order.pickUpDistance > 0 || order.pickupEtaInMins > 0 || order.dropDistance > 0 || order.dropEtaInMins > 0)

    val density = LocalDensity.current

    val cardBottomPadding = remember(orderListSize, index, cardHeight) {
        if (orderListSize > 1 && index == orderListSize - 1) {
            with(density) {
                val padding = lazyColumnSizeProvider() - cardHeight - 8.dp.toPx().toInt()
                if (padding > 0) padding.toDp() else 0.dp
            }
        } else {
            0.dp
        }
    }

    val itemModifier = if (isShowCardPadding) {
        modifier.padding(
            start = 8.dp,
            top = if (index == 0) 16.dp else 8.dp,
            end = 16.dp,
            bottom = cardBottomPadding
        )
    } else modifier

    Box(
        modifier = itemModifier
                .clip(RoundedCornerShape(16.dp))
                .background(color = RapidoTheme.colors.surface)
                .border(
                        BorderStroke(1.dp, color = RdsColors.neutrals4),
                        shape = RoundedCornerShape(16.dp)
                )
    ) {
        Column(
            modifier = Modifier.onSizeChanged {
                if (orderListSize > 1 && index == orderListSize - 1) {
                    cardHeight = it.height
                }
            }
        ) {
            OrderHeader(
                modifier = Modifier
                        .background(
                                brush = Brush.linearGradient(RapidoTheme.colors.primaryContainer) // this should be have atleast 2 colors
                        )
                        .padding(horizontal = 16.dp, vertical = 9.dp),
                amountWithoutExtra = order.amountWithoutExtra,
                extraAmount = order.extraAmount,
                paymentType = order.paymentType,
                serviceDetails = order.serviceInfo,
                rightIconUrl = order.headerRightIconUrl
            )
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                OrderPickUpDrop(
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 6.dp),
                    pickUpTitle = order.pickUpTitle,
                    dropTitle = order.dropTitle,
                    pickUpAddress = order.pickUpAddress,
                    dropAddress = order.dropAddress,
                    batchOrderSize = order.batchOrderSize
                )
                if (isDistanceOrEtaAvailable) {
                    DistanceEtaInfoUi(
                        pickupDistanceOrEtaText = getEtaOrDistanceText(
                            distanceInMeters = order.pickUpDistance,
                            etaInMins = order.pickupEtaInMins
                        ),
                        dropDistanceOrEtaText = getEtaOrDistanceText(
                            distanceInMeters = order.dropDistance,
                            etaInMins = order.dropEtaInMins
                        ),
                        isShowVertical = isShowDistanceVertical
                    )
                }
                if (order.orderTipAmount > 0) {
                    OrderTipView(
                        amount = order.orderTipAmount
                    )
                }
                AcceptAndRejectUi(
                    order = order,
                    onAcceptOrder = onAcceptOrder,
                    onRejectOrder = onRejectOrder
                )
            }
        }
        if (isShowOverlay) {
            MissedOrderOverlay(Modifier.matchParentSize(), order.unavailableReason)
        }
    }
}

fun getEtaOrDistanceText(
    distanceInMeters: Double,
    etaInMins: Int
): String {
    return when {
        distanceInMeters > 0 -> {
            val decimalFormat = DecimalFormat("#.#")
            return decimalFormat.format(distanceInMeters * 0.001) + " Km"
        }

        etaInMins > 0 -> {
            "$etaInMins mins"
        }
        else -> ""
    }
}

@Composable
fun OrderTipView(amount: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
                .padding(top = 12.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = RdsColors.greenBase)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row {
            Image(
                modifier = Modifier
                    .size(16.dp),
                painter =
                painterResource(
                    id = R.drawable.ic_rupee_icon
                ),
                contentDescription = null
            )
            Spacer(
                modifier = Modifier
                    .width(8.dp)
            )
            Text(
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = RdsColors.white,
                    textAlign = TextAlign.Center
                ),
                text = stringResource(id = R.string.customer_added_x_tip, amount),
                modifier = Modifier.addTestTag(PreOrderTestTag.TIP)
            )
        }
    }
}

@Composable
fun MissedOrderOverlay(modifier: Modifier = Modifier, expiryReason: PreOrderUnavailableReason) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = modifier
                .background(color = RdsColors.missedOrderOverlayColor)
                .clickable { }
    ) {
        MissedOrderUi(expiryReason = expiryReason)
    }
}

@Composable
fun MissedOrderUi(modifier: Modifier = Modifier, expiryReason: PreOrderUnavailableReason) {
    Column(
        modifier = modifier
                .padding(12.dp)
                .fillMaxWidth()
                .background(color = RdsColors.lightRed, shape = RoundedCornerShape(16.dp))
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                    modifier = Modifier.size(20.dp),
                    contentDescription = "",
                    painter = painterResource(id = R.drawable.ic_missed_warning),
                    tint = RdsColors.redBase,
            )

            Text(
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = RdsColors.redBase
                ),
                text = stringResource(
                    id = if (expiryReason == PreOrderUnavailableReason.ORDER_CANCELLED_BY_CUSTOMER) {
                        R.string.order_is_no_longer_available
                    } else {
                        R.string.you_missed_the_order
                    }
                ),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        if (expiryReason == PreOrderUnavailableReason.ACCEPTED_BY_OTHER_CAPTAIN) {
            Text(
                style = MaterialTheme.typography.bodyMedium.copy(
                    lineHeight = 16.sp,
                    color = RdsColors.neutrals8
                ),
                text = stringResource(id = R.string.order_accepted_by_another_captain_message),
                modifier = Modifier.padding(start = 28.dp)
            )
        }
    }
}

@Composable
fun AcceptAndRejectUi(
    modifier: Modifier = Modifier,
    order: MultiOrderUiItem,
    onAcceptOrder: (orderId: String) -> Unit,
    onRejectOrder: (orderId: String) -> Unit
) {
    Row(
        modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                    .padding(end = 8.dp)
                    .width(64.dp)
                    .fillMaxHeight()
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
                    .background(color = RdsColors.white, shape = RoundedCornerShape(8.dp))
                    .clickable(
                            onClick = {
                                onRejectOrder(order.id)
                            }
                    ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "",
                    tint = RdsColors.dark2,
                    modifier = Modifier.addTestTag(PreOrderTestTag.CANCEL)
            )
        }
        AcceptButton(
            modifier = Modifier
                    .addTestTag(PreOrderTestTag.ACCEPT)
                    .fillMaxWidth()
                    .weight(1f, true),
            timerText = order.timerText,
            progress = order.timerProgressSeconds,
            maxProgress = order.captainAcceptTimer,
            isAcceptButtonEnable = order.isAcceptButtonEnable,
            isRunningOutOfTime = order.timerProgressSeconds < 10,
            orderId = order.id,
            onAcceptOrder = onAcceptOrder
        )
    }
}

@Composable
fun OrderHeader(
    modifier: Modifier,
    amountWithoutExtra: Int,
    extraAmount: Int,
    paymentType: String,
    serviceDetails: ServiceInfo,
    rightIconUrl: String = ""
) {
    OrderType(
        modifier = modifier,
        amountWithoutExtra = amountWithoutExtra,
        extraAmount = extraAmount,
        paymentType = paymentType,
        serviceDetails = serviceDetails,
        rightIconUrl = rightIconUrl
    )
    if (RapidoTheme.colors.primaryDividerStyle == DividerStyle.SOLID) {
        Divider(
            color = RdsColors.neutrals3,
            modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .height(1.dp)
        )
    }
}

@Composable
fun DistanceEtaInfoUi(
    pickupDistanceOrEtaText: String,
    dropDistanceOrEtaText: String,
    isShowVertical: Boolean = true
) {
    Row(
        modifier = Modifier
                .padding(top = 16.dp, start = 16.dp)
                .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (pickupDistanceOrEtaText.isNotBlank()) {
            RenderOrderDistance(
                title = stringResource(id = R.string.pickup_text),
                distance = pickupDistanceOrEtaText,
                distanceTestTag = PreOrderTestTag.PICKUP_DISTANCE,
                isShowVertical = isShowVertical && dropDistanceOrEtaText.isNotBlank(),
            )
        }
        if (pickupDistanceOrEtaText.isNotBlank() && dropDistanceOrEtaText.isNotBlank()) {
            Spacer(modifier = Modifier.width(16.dp))
            Divider(
                color = RdsColors.gray90,
                modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        if (dropDistanceOrEtaText.isNotBlank()) {
            RenderOrderDistance(
                title = stringResource(id = R.string.drop),
                distance = dropDistanceOrEtaText,
                distanceTestTag = PreOrderTestTag.DROP_DISTANCE,
                isShowVertical = isShowVertical && pickupDistanceOrEtaText.isNotBlank(),
            )
        }
    }
}

@Composable
fun RenderOrderDistance(
    title: String,
    distance: String,
    distanceTestTag: String = "",
    isShowVertical: Boolean = true
) {
    if (isShowVertical) {
        Column {
            OrderDistance(
                title = title,
                distance = distance,
                distanceTestTag = distanceTestTag
            )
        }
    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OrderDistance(
                title = title,
                distance = distance,
                distanceTestTag = distanceTestTag
            )
        }
    }
}

@Preview(
    heightDp = 900
)
@Composable
fun OrderItemPreview() {
    AndroidKitchen2Theme {
        Surface(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp, 12.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                OrderItem(
                    Modifier,
                    MultiOrderUiItem(
                        "1",
                        orderType = "Auto",
                        serviceInfo = ServiceInfo(
                            serviceName = UiText.StringResource(R.string.rapido),
                            serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_info_gray)
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
                        extraAmount = 15,
                        paymentType = "wallet",
                        orderTipAmount = 34,
                        unavailableReason = PreOrderUnavailableReason.ACCEPTED_BY_OTHER_CAPTAIN,
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
                    ),
                    0,
                    1,
                    isShowCardPadding = true,
                    isShowDistanceVertical = true,
                    { },
                    { }

                ) { 0 }
                Spacer(modifier = Modifier.height(24.dp))
                OrderItem(
                    Modifier,
                    MultiOrderUiItem(
                        "2",
                        orderType = "app",
                        serviceInfo = ServiceInfo(
                            serviceName = UiText.StringResource(R.string.rapido),
                            serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_auto_icon),
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
                    ),
                    0,
                    1,
                    isShowCardPadding = true,
                    isShowDistanceVertical = false,
                    { },
                    { }
                ) { 0 }
            }
        }
    }
}

@Preview
@Composable
fun OrderItemGtaPreview() {
    AndroidKitchen2Theme {
        Surface(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp, 12.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                OrderItem(
                    Modifier,
                    MultiOrderUiItem(
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
                    ),
                    0,
                    1,
                    isShowCardPadding = true,
                    isShowDistanceVertical = true,
                    { },
                    { }
                ) { 0 }
            }
        }
    }
}

@Preview
@Composable
fun OrderItemAutoPreview() {
    AndroidKitchen2Theme {
        Surface(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp, 12.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                OrderItem(
                    Modifier,
                    MultiOrderUiItem(
                        "2",
                        orderType = "app",
                        serviceInfo = ServiceInfo(
                            serviceName = UiText.DynamicString("Kitchen"),
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
                        isGTAOrder = false,
                        amountWithoutExtra = -1,
                        extraAmount = -1,
                        paymentType = "wallet",
                        orderTipAmount = 34,
                        isBatchOrder = true,
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
                    ),
                    0,
                    1,
                    isShowCardPadding = true,
                    isShowDistanceVertical = true,
                    { },
                    { }
                ) { 0 }
            }
        }
    }
}

//@Preview
//@Composable
//fun DistanceEtaInfoPreview(@PreviewParameter(PreviewDistanceEtaInfoProvider::class) distanceEtaInfo: DistanceEtaInfo) {
//    RapidoTheme {
//        Column(
//            modifier = Modifier
//                    .fillMaxWidth()
//                    .background(color = RdsColors.white)
//                    .padding(24.dp)
//        ) {
//            DistanceEtaInfoUi(
//                pickupDistanceOrEtaText = getEtaOrDistanceText(
//                    distanceInMeters = distanceEtaInfo.pickupDistance,
//                    etaInMins = distanceEtaInfo.pickupEta
//                ),
//                dropDistanceOrEtaText = getEtaOrDistanceText(
//                    distanceInMeters = distanceEtaInfo.dropDistance,
//                    etaInMins = distanceEtaInfo.dropEta
//                )
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            DistanceEtaInfoUi(
//                pickupDistanceOrEtaText = getEtaOrDistanceText(
//                    distanceInMeters = distanceEtaInfo.pickupDistance,
//                    etaInMins = distanceEtaInfo.pickupEta
//                ),
//                dropDistanceOrEtaText = getEtaOrDistanceText(
//                    distanceInMeters = distanceEtaInfo.dropDistance,
//                    etaInMins = distanceEtaInfo.dropEta
//                ),
//                isShowVertical = false
//            )
//        }
//    }
//}
