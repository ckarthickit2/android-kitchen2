package me.kartdroid.androidkitchen2.orders

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.orders.preview.PreviewPickupDropProvider
import me.kartdroid.androidkitchen2.ui.theme.AndroidKitchen2Theme
import me.kartdroid.androidkitchen2.ui.theme.RapidoTheme
import me.kartdroid.androidkitchen2.ui.theme.RdsColors
import me.kartdroid.androidkitchen2.utils.addTestTag

val batchOrderIndicatorColor: Color = Color(0xff707070)

@Composable
fun OrderPickUpDrop(
    modifier: Modifier = Modifier,
    pickUpTitle: String,
    dropTitle: String,
    pickUpAddress: String?,
    dropAddress: String?,
    batchOrderSize: Int = 0
) {
    val isBatchOrder = remember(batchOrderSize) {
        batchOrderSize > 0
    }

    val batchPickupIndicatorCount = if (pickUpAddress.isNullOrBlank()) 2 else 6

    ConstraintLayout(modifier = modifier) {
        val (
            pickupIconRef, pickupTitleRef, dropIconRef,
            dropTitleRef, pickUpAddressRef, dropAddressRef, pickUpDropVerticalDivider, batchSizeIndicatorRef, batchSizeTextRef,
            batchOrderPickupIndicatorRef, batchOrderDropIndicatorRef
        ) = createRefs()

        Image(
            modifier = Modifier
                .constrainAs(pickupIconRef) {
                    top.linkTo(pickupTitleRef.top)
                    bottom.linkTo(pickupTitleRef.bottom)
                    start.linkTo(parent.start)
                },
            painter = painterResource(id = R.drawable.ic_pickup_indicator),
            contentDescription = null
        )

        // Pickup Title
        Text(
            modifier = Modifier
                    .addTestTag(PreOrderTestTag.PICKUP_TITLE)
                    .constrainAs(pickupTitleRef) {
                        top.linkTo(parent.top)
                        start.linkTo(pickupIconRef.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(start = 14.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold,
                color = RapidoTheme.colors.onSurface
            ),
            text = pickUpTitle.ifBlank { stringResource(id = R.string.pickup_text) },
            maxLines = 1
        )

        // Pickup Address
        Text(
            modifier = Modifier
                    .addTestTag(PreOrderTestTag.PICKUP_DESCRIPTION)
                    .constrainAs(pickUpAddressRef) {
                        top.linkTo(pickupTitleRef.bottom, margin = 2.dp)
                        linkTo(parent.start, parent.end, startMargin = 26.dp)
                        width = Dimension.fillToConstraints
                        visibility =
                                if (pickUpAddress.isNullOrBlank()) Visibility.Gone else Visibility.Visible
                    }
                    .fillMaxWidth(),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Normal,
                color = RapidoTheme.colors.onSurfaceVariant
            ),
            text = pickUpAddress ?: "",
            maxLines = 2
        )

        Box(
            modifier = Modifier
                    .constrainAs(batchSizeIndicatorRef) {
                        top.linkTo(batchSizeTextRef.top)
                        bottom.linkTo(batchSizeTextRef.bottom)
                        start.linkTo(pickupIconRef.start)
                        end.linkTo(pickupIconRef.end)
                        visibility = if (isBatchOrder) Visibility.Visible else Visibility.Gone
                    }
                    .size(10.dp)
                    .background(color = RdsColors.blueDark3, shape = CircleShape)
        )

        BatchAddressIndicator(
            modifier = Modifier
                    .constrainAs(batchOrderPickupIndicatorRef) {
                        top.linkTo(pickupIconRef.bottom)
                        start.linkTo(pickupIconRef.start)
                        bottom.linkTo(batchSizeIndicatorRef.top)
                        end.linkTo(pickupIconRef.end)
                        height = Dimension.fillToConstraints
                        visibility = if (isBatchOrder) Visibility.Visible else Visibility.Gone
                    }
                    .fillMaxHeight(),
            count = batchPickupIndicatorCount
        )

        Text(
            modifier = Modifier
                    .addTestTag(PreOrderTestTag.BATCH_COUNT_TEXT)
                    .constrainAs(batchSizeTextRef) {
                        top.linkTo(pickUpAddressRef.bottom, margin = 16.dp, goneMargin = 16.dp)
                        linkTo(parent.start, parent.end, startMargin = 26.dp)
                        width = Dimension.fillToConstraints
                        visibility =
                                if (isBatchOrder && batchOrderSize > 1) Visibility.Visible else Visibility.Gone
                    }
                    .fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium.copy(
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold,
                color = RapidoTheme.colors.onSurface
            ),
            text = "+${batchOrderSize - 1} more pickup & drop",
            maxLines = 1
        )

        if (dropTitle.isNotBlank() || !dropAddress.isNullOrBlank()) {
            Image(
                modifier = Modifier
                    .constrainAs(dropIconRef) {
                        top.linkTo(dropTitleRef.top)
                        bottom.linkTo(dropTitleRef.bottom)
                        start.linkTo(parent.start)
                    },
                painter = painterResource(id = R.drawable.ic_drop_indicator),
                contentDescription = null
            )

            // Drop Title
            Text(
                modifier = Modifier
                        .addTestTag(PreOrderTestTag.DROP_TITLE)
                        .constrainAs(dropTitleRef) {
                            top.linkTo(batchSizeTextRef.bottom, margin = 18.dp, goneMargin = 10.dp)
                            start.linkTo(dropIconRef.end)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = 14.dp),
                style = MaterialTheme.typography.bodyLarge.copy(
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = RapidoTheme.colors.onSurface
                ),
                text = dropTitle.ifBlank { stringResource(id = R.string.drop) },
                maxLines = 1
            )

            // Drop Address
            Text(
                modifier = Modifier
                        .addTestTag(PreOrderTestTag.DROP_DESCRIPTION)
                        .constrainAs(dropAddressRef) {
                            top.linkTo(dropTitleRef.bottom, margin = 2.dp)
                            linkTo(parent.start, parent.end, startMargin = 26.dp)
                            width = Dimension.fillToConstraints
                            visibility =
                                    if (!dropAddress.isNullOrBlank()) Visibility.Visible else Visibility.Gone
                        }
                        .fillMaxWidth(),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Normal,
                    color = RapidoTheme.colors.onSurfaceVariant
                ),
                text = dropAddress ?: "",
                maxLines = 2
            )

            Divider(
                modifier = Modifier
                        .constrainAs(pickUpDropVerticalDivider) {
                            top.linkTo(pickupIconRef.bottom, margin = 1.dp)
                            bottom.linkTo(dropIconRef.top, margin = 1.dp)
                            start.linkTo(pickupIconRef.start)
                            end.linkTo(pickupIconRef.end)
                            height = Dimension.fillToConstraints
                            visibility = if (isBatchOrder) Visibility.Gone else Visibility.Visible
                        }
                        .width(1.dp),
                color = RdsColors.pickupDropConnectorColor
            )

            BatchAddressIndicator(
                modifier = Modifier
                        .constrainAs(batchOrderDropIndicatorRef) {
                            top.linkTo(batchSizeIndicatorRef.bottom)
                            start.linkTo(dropIconRef.start)
                            end.linkTo(dropIconRef.end)
                            bottom.linkTo(dropIconRef.top)
                            height = Dimension.fillToConstraints
                            visibility = if (isBatchOrder) Visibility.Visible else Visibility.Gone
                        }
                        .fillMaxHeight(),
                count = 2
            )
        }
    }
}

@Composable
private fun BatchAddressIndicator(modifier: Modifier = Modifier, count: Int) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        repeat(count) {
            Box(
                modifier = Modifier
                        .padding(
                                top = 2.25.dp,
                                bottom = 2.25.dp
                        )
                        .size(4.dp)
                        .background(
                                color = batchOrderIndicatorColor,
                                shape = CircleShape
                        )
            )
        }
    }
}

@Preview
@Composable
fun OrderPickUpDropPreview(@PreviewParameter(PreviewPickupDropProvider::class) pickupDrop: PreviewPickupDropProvider.PreviewPickupDrop) {
    AndroidKitchen2Theme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            OrderPickUpDrop(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 6.dp),
                pickUpTitle = pickupDrop.pickupTitle,
                dropTitle = pickupDrop.dropTitle,
                pickUpAddress = pickupDrop.pickupAddress,
                dropAddress = pickupDrop.dropAddress,
                batchOrderSize = pickupDrop.batchOrderSize
            )
        }
    }
}
