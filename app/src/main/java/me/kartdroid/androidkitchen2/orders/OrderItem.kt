package com.rapido.rider.preorder.multi.presentation.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.orders.AcceptButton
import me.kartdroid.androidkitchen2.orders.MultiOrderUiItem
import me.kartdroid.androidkitchen2.orders.OrderDistance
import me.kartdroid.androidkitchen2.orders.OrderPickUpDrop
import me.kartdroid.androidkitchen2.orders.OrderType
import me.kartdroid.androidkitchen2.orders.PreOrderTestTag
import me.kartdroid.androidkitchen2.orders.PreOrderUnavailableReason
import me.kartdroid.androidkitchen2.orders.ServiceInfo
import me.kartdroid.androidkitchen2.orders.preview.MultiOrderPreviewProvider
import me.kartdroid.androidkitchen2.orders.preview.RenderMultiOrderListUi
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
        addBottomPaddingToFillParent: Boolean = true,
        isShowDistanceVertical: Boolean = true,
        onAcceptOrder: (orderId: String, isOnRideBooking: Boolean) -> Unit,
        onRejectOrder: (orderId: String) -> Unit,
        parentHeightProvider: () -> Int = { 0 },
) {
    var cardHeight by remember { mutableIntStateOf(0) }
    val isShowOverlay = (order.unavailableReason==PreOrderUnavailableReason.ACCEPTED_BY_OTHER_CAPTAIN || order.unavailableReason==PreOrderUnavailableReason.ORDER_CANCELLED_BY_CUSTOMER)
    val isDistanceOrEtaAvailable = (order.pickUpDistance > 0 || order.pickupEtaInMins > 0 || order.dropDistance > 0 || order.dropEtaInMins > 0)

    val density = LocalDensity.current

    val cardBottomPadding = remember(orderListSize, index, cardHeight) {
        when {
            !addBottomPaddingToFillParent -> 0.dp
            orderListSize > 1 && index==orderListSize - 1 -> {
                with(density) {
                    val padding = parentHeightProvider() - cardHeight - 8.dp.roundToPx()
                    if (padding > 0) padding.toDp() else 0.dp
                }
            }

            else -> 0.dp
        }
    }

    Box(
            modifier = modifier
                    .padding(bottom = cardBottomPadding)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = RapidoTheme.colors.surface)
                    .border(
                            BorderStroke(1.dp, color = RdsColors.neutrals4),
                            shape = RoundedCornerShape(16.dp)
                    )
    ) {
        Column(
                modifier = Modifier.onSizeChanged {
                    if (orderListSize > 1 && index==orderListSize - 1) {
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
                if (order.callOutBanner.message.isNotBlank()) {
                    OrderBannerView(
                            bannerText = order.callOutBanner.message,
                            iconUrl = order.callOutBanner.iconUrl
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
fun OrderBannerView(
        modifier: Modifier = Modifier,
        bannerText: String,
        iconUrl: String
) {
    Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = RdsColors.greenBase)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Row(
                verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                    modifier = Modifier
                            .size(16.dp),
                    painter = painterResource(id = R.drawable.ic_rupee_icon),
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
                    text = bannerText,
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
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                    modifier = Modifier.size(20.dp),
                    contentDescription = "",
                    painter = painterResource(id = R.drawable.ic_missed_warning),
                    tint = RdsColors.redBase
            )

            Text(
                    style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium,
                            color = RdsColors.redBase
                    ),
                    text = stringResource(
                            id = if (expiryReason==PreOrderUnavailableReason.ORDER_CANCELLED_BY_CUSTOMER) {
                                R.string.order_is_no_longer_available
                            } else {
                                R.string.you_missed_the_order
                            }
                    ),
                    modifier = Modifier.padding(start = 8.dp)
            )
        }
        if (expiryReason==PreOrderUnavailableReason.ACCEPTED_BY_OTHER_CAPTAIN) {
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
        onAcceptOrder: (orderId: String, isOnRideBooking: Boolean) -> Unit,
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
                    modifier = Modifier.addTestTag(PreOrderTestTag.CANCEL),
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
                onAcceptOrder = {
                    onAcceptOrder(order.id, order.isOnRideBooking)
                }
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
    if (RapidoTheme.colors.primaryDividerStyle==DividerStyle.SOLID) {
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
        heightDp = 1250
)
@Composable
fun OrderItemPreview(@PreviewParameter(MultiOrderPreviewProvider::class) renderMultiOrderListUi: RenderMultiOrderListUi) {
    AndroidKitchen2Theme {
        Surface(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 12.dp),
                color = MaterialTheme.colorScheme.background
        ) {
            Column(
                    modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
            ) {
                renderMultiOrderListUi.orderList.items.forEachIndexed { index, multiOrderUiItem ->
                    OrderItem(
                            modifier = Modifier.padding(
                                    start = 8.dp,
                                    top = if (index==0) 16.dp else 8.dp,
                                    end = 16.dp,
                            ),
                            order = multiOrderUiItem,
                            index = index,
                            orderListSize = renderMultiOrderListUi.orderList.items.size,
                            onAcceptOrder = { _, _ -> },
                            onRejectOrder = { _ -> }
                    )
                }
            }
        }
    }
}
