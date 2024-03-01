package me.kartdroid.androidkitchen2.orders

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.rider.preorder.multi.presentation.ui.composables.OrderItem
import kotlinx.collections.immutable.PersistentMap
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.orders.preview.MultiOrderPreviewProvider
import me.kartdroid.androidkitchen2.orders.preview.RenderMultiOrderListUi
import me.kartdroid.androidkitchen2.ui.lazycolumn.LazyColumnScrollbar
import me.kartdroid.androidkitchen2.ui.lazycolumn.ScrollbarSelectionMode
import me.kartdroid.androidkitchen2.ui.theme.AndroidKitchen2Theme
import me.kartdroid.androidkitchen2.ui.theme.KitchenDefaultOrderColorsDark
import me.kartdroid.androidkitchen2.ui.theme.KitchenThemeColors
import me.kartdroid.androidkitchen2.ui.theme.LocalKitchenColors
import me.kartdroid.androidkitchen2.ui.theme.RdsColors
import me.kartdroid.androidkitchen2.utils.addTestTag

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderList(
    modifier: Modifier = Modifier,
    orderList: MultiOrderListWrapper,
    orderThemeColorsMap: PersistentMap<String, KitchenThemeColors>,
    lazyListState: LazyListState,
    updateSelectedOrder: (Int, Boolean) -> Unit,
    onAcceptOrder: (orderId: String, isOnRideBooking: Boolean) -> Unit,
    onRejectOrder: (orderId: String) -> Unit,
    logOrdersScroll: () -> Unit,
    logOnCustomerTipFTuxViewed: () -> Unit,
    logCustomerTipFTuxClicked: () -> Unit
) {
    var lazyColumnHeight by remember { mutableIntStateOf(0) }
    var showCallOutFloatingMessage by remember { mutableStateOf(false) }
    var callOutBannerOrder by remember { mutableStateOf(Pair(-1, MultiOrderUiItem.CallOutBanner.EMPTY)) }
    var tipFTuxViewedEventSent by remember { mutableStateOf(false) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                logOrdersScroll()
                return Velocity.Zero
            }
        }
    }
    Box(modifier = modifier) {
        LazyColumnScrollbar(
            listState = lazyListState,
            thickness = 4.dp,
            padding = 6.dp,
            thumbColor = RdsColors.scrollbarThumbColor,
            selectionMode = ScrollbarSelectionMode.Disabled
        ) {
            LazyColumn(
                modifier = Modifier
                        .fillMaxSize()
                        .onSizeChanged {
                            lazyColumnHeight = it.height
                        }
                        .nestedScroll(nestedScrollConnection),
                state = lazyListState,
                flingBehavior = getSnapFlingBehavior(
                    lazyListState
                ),
                content = {
                    itemsIndexed(orderList.items, key = { _, item -> item.id }) { index, order ->
                        CompositionLocalProvider(
                                LocalKitchenColors provides (orderThemeColorsMap[order.templateName] ?: KitchenDefaultOrderColorsDark)
                        ) {
                            OrderItem(
                                    modifier = Modifier
                                            .addTestTag(PreOrderTestTag.CARD_INDEX_PREFIX + index)
                                            .animateItemPlacement()
                                            .padding(
                                                    start = 8.dp,
                                                    top = if (index==0) 16.dp else 8.dp,
                                                    end = 16.dp,
                                            ),
                                    order = order,
                                    index = index,
                                    orderListSize = orderList.items.size,
                                    onAcceptOrder = onAcceptOrder,
                                    onRejectOrder = onRejectOrder,
                                    parentHeightProvider = { lazyColumnHeight }
                            )
                        }
                    }
                }
            )

            LaunchedEffect(key1 = orderList.items.size) {
                snapshotFlow { lazyListState.isScrollInProgress }.distinctUntilChanged()
                    .filter { !it }
                    .collect {
                        val visibleItemIndex =
                            lazyListState.layoutInfo
                                .visibleItemsInfo
                                .firstOrNull { visibleItem -> visibleItem.offset >= 0 }?.index
                                ?: lazyListState.firstVisibleItemIndex

                        val floatingMessageOrderIndex = orderList.items.indexOfFirst {
                            it.callOutBanner.floaterMessage.isNotBlank() && orderList.items.indexOf(it) > visibleItemIndex
                        }
                        callOutBannerOrder = Pair(floatingMessageOrderIndex, orderList.items.getOrNull(floatingMessageOrderIndex)?.callOutBanner ?: MultiOrderUiItem.CallOutBanner.EMPTY)
                        showCallOutFloatingMessage = if (visibleItemIndex >= 0 && visibleItemIndex < orderList.items.size) {
                            orderList.items[visibleItemIndex].callOutBanner.floaterMessage.isBlank() && visibleItemIndex <= callOutBannerOrder.first
                        } else false
                        updateSelectedOrder(visibleItemIndex, false)
                    }
            }
        }
        if (showCallOutFloatingMessage && callOutBannerOrder.second != MultiOrderUiItem.CallOutBanner.EMPTY) {
            FloatingMessage(
                modifier = Modifier.align(Alignment.BottomCenter),
                orderIndex = callOutBannerOrder.first,
                iconUrl = callOutBannerOrder.second.iconUrl,
                message = callOutBannerOrder.second.floaterMessage,
                updateSelectedOrder,
                logCustomerTipFTuxClicked
            )
            if (tipFTuxViewedEventSent.not()) logOnCustomerTipFTuxViewed()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun getSnapFlingBehavior(lazyListState: LazyListState): FlingBehavior {
    val snappingLayout = remember(lazyListState) {
        SnapLayoutInfoProvider(lazyListState = lazyListState, positionInLayout = { _, _, _ -> 0 })
    }
    return rememberSnapFlingBehavior(snappingLayout)
}

@Composable
fun FloatingMessage(
    modifier: Modifier = Modifier,
    orderIndex: Int,
    iconUrl: String,
    message: String,
    updateSelectedOrder: (Int, Boolean) -> Unit,
    logCustomerTipFTuxClicked: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "OrderTip")

    val translation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -20f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        ),
        label = "OrderTipTranslation"
    )
    Box(
        modifier = modifier
                .graphicsLayer { translationY = translation }
                .padding(all = 24.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(color = RdsColors.greenBase)
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .clickable {
                    updateSelectedOrder(orderIndex, true)
                    logCustomerTipFTuxClicked()
                },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(32.dp),
                    painter = painterResource(id = R.drawable.ic_rupee_icon),
                contentDescription = null
            )
            Spacer(
                modifier = Modifier
                    .width(8.dp)
            )
            Text(
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = RdsColors.white,
                    textAlign = TextAlign.Center
                ),
                text = message,
                modifier = Modifier
            )
            Spacer(
                modifier = Modifier
                    .width(18.dp)
            )
            Image(
                modifier = Modifier
                    .size(32.dp),
                painter =
                painterResource(
                    id = R.drawable.ic_arrow_downward
                ),
                contentDescription = null
            )
        }
    }
}

@Preview(heightDp = 1250)
@Composable
fun OrderListPreview(@PreviewParameter(MultiOrderPreviewProvider::class) renderMultiOrderListUi: RenderMultiOrderListUi) {
    AndroidKitchen2Theme {
        Surface(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp, 12.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                OrderList(
                    modifier = Modifier,
                    orderList = renderMultiOrderListUi.orderList,
                    orderThemeColorsMap = renderMultiOrderListUi.orderThemeColorsMap,
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
        }
    }
}
