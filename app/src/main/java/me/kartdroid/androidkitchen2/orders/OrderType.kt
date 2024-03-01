package me.kartdroid.androidkitchen2.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import coil.compose.AsyncImage
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.orders.preview.PreviewOrderTypeAndPaymentProvider
import me.kartdroid.androidkitchen2.ui.theme.AndroidKitchen2Theme
import me.kartdroid.androidkitchen2.ui.theme.RapidoTheme
import me.kartdroid.androidkitchen2.utils.UIImageResourceRender
import me.kartdroid.androidkitchen2.utils.addTestTag
import java.util.Locale

@Composable
fun OrderType(
        modifier: Modifier = Modifier,
        amountWithoutExtra: Int,
        extraAmount: Int,
        paymentType: String,
        serviceDetails: ServiceInfo,
        rightIconUrl: String
) {
    ConstraintLayout(
            modifier = modifier.fillMaxWidth()
    ) {
        val (serviceIconRef, endIconRef, earningsLayoutRef) = createRefs()
        val shouldDisplayEarnings = amountWithoutExtra > 0 || extraAmount > 0
        UIImageResourceRender(
                serviceDetails.serviceIcon,
                modifier = Modifier
                        .addTestTag(PreOrderTestTag.SERVICE_ICON)
                        .constrainAs(serviceIconRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        .padding(vertical = if (shouldDisplayEarnings) 0.dp else 3.dp)
                        .size(32.dp)
        )
        Row(
                modifier = Modifier.constrainAs(earningsLayoutRef) {
                    start.linkTo(serviceIconRef.end, margin = 12.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(endIconRef.start, margin = 8.dp, goneMargin = 8.dp)
                    width = Dimension.fillToConstraints
                },
                verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                if (shouldDisplayEarnings) {
                    ServiceNameTag(serviceName = serviceDetails.serviceName.asString())
                } else {
                    ServiceNameText(serviceName = serviceDetails.serviceName.asString())
                }

                if (shouldDisplayEarnings) {
                    Spacer(modifier = Modifier.height(3.dp))
                    AmountAndPayment(
                            amountWithoutExtra = amountWithoutExtra,
                            extraAmount = extraAmount,
                            paymentType = paymentType
                    )
                }
            }
            if (!shouldDisplayEarnings && paymentType.isNotBlank()) {
                PaymentType(
                        modifier = Modifier
                                .addTestTag(PreOrderTestTag.PAYMENT_TYPE)
                                .padding(start = 8.dp),
                        paymentType = paymentType
                )
            }
        }

        AsyncImage(
                model = rightIconUrl,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                error = null,
                modifier = Modifier
                        .constrainAs(endIconRef) {
                            end.linkTo(parent.end, margin = 8.dp)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            visibility =
                                    if (rightIconUrl.isNotBlank()) Visibility.Visible else Visibility.Gone
                        }
                        .addTestTag(PreOrderTestTag.RIGHT_ICON)
                        .size(width = 35.dp, height = 46.dp),
        )
    }
}

@Composable
private fun ServiceNameTag(serviceName: String) {
    Box(
            modifier = Modifier
                    .border(
                            width = 0.3.dp,
                            color = RapidoTheme.colors.secondaryContainerOutline,
                            shape = RoundedCornerShape(size = 100.dp)
                    )
                    .background(
                            color = RapidoTheme.colors.onSecondaryContainer,
                            shape = RoundedCornerShape(size = 100.dp)
                    )
    ) {
        Text(
                text = serviceName,
                style = MaterialTheme.typography.bodySmall.copy(
                        letterSpacing = 0.4.sp,
                        lineHeight = 12.sp,
                        color = RapidoTheme.colors.secondaryContainer
                ),
                maxLines = 1,
                modifier = Modifier
                        .addTestTag(PreOrderTestTag.SERVICE_TAG)
                        .padding(start = 8.dp, end = 8.dp)
        )
    }
}

@Composable
private fun ServiceNameText(serviceName: String) {
    Text(
            text = serviceName,
            style = MaterialTheme.typography.bodyLarge.copy(
                    letterSpacing = 0.4.sp,
                    lineHeight = 16.sp,
                    color = RapidoTheme.colors.onPrimaryContainer
            ),
            maxLines = 1,
            modifier = Modifier
                    .addTestTag(PreOrderTestTag.SERVICE_TAG)
    )
}

@Composable
private fun AmountAndPayment(
        amountWithoutExtra: Int,
        extraAmount: Int,
        paymentType: String
) {
    Row(
            verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
                text = getPayableAmountText(amountWithoutExtra, extraAmount),
                style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = RapidoTheme.colors.onPrimaryContainer
                ),
                modifier = Modifier
                        .addTestTag(PreOrderTestTag.ESTIMATED_AMOUNT)
                        .weight(1f, false)
        )
        if (paymentType.isNotEmpty()) {
            PaymentType(
                    modifier = Modifier
                            .addTestTag(PreOrderTestTag.PAYMENT_TYPE)
                            .padding(start = 8.dp),
                    paymentType = paymentType
            )
        }
    }
}

@Composable
private fun PaymentType(modifier: Modifier = Modifier, paymentType: String) {
    Text(
            modifier = modifier,
            text = getPaymentTypeText(paymentType = paymentType),
            style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Normal,
                    color = RapidoTheme.colors.onPrimaryContainer
            )
    )
}

@Composable
fun getPayableAmountText(amountWithoutExtra: Int, extraAmount: Int): AnnotatedString {
    return buildAnnotatedString {
        withStyle(SpanStyle(color = RapidoTheme.colors.onPrimaryContainer)) {
            if (amountWithoutExtra > 0) {
                append("₹")
                append(amountWithoutExtra.toString())
            } else if (extraAmount > 0) {
                append(stringResource(id = R.string.text_oa_extra))
                append(" ")
            }
        }
        if (amountWithoutExtra > 0 && extraAmount > 0) {
            withStyle(SpanStyle(color = RapidoTheme.colors.onPrimaryContainer)) {
                append(" + ")
            }
        }
        if (extraAmount > 0) {
            withStyle(SpanStyle(color = RapidoTheme.colors.onPrimaryContainerVariant)) {
                append("₹")
                append(extraAmount.toString())
            }
        }
    }
}

@Composable
@ReadOnlyComposable
fun getPaymentTypeText(paymentType: String): String {
    val paymentMode = if (paymentType.lowercase()=="wallet") {
        stringResource(R.string.online)
    } else {
        paymentType.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
    return "($paymentMode)"
}

@Preview
@Composable
fun OrderTypePreview(@PreviewParameter(PreviewOrderTypeAndPaymentProvider::class) paymentAndOrderType: PreviewOrderTypeAndPaymentProvider.PreviewOrderTypeAndPayment) {
    AndroidKitchen2Theme {
        Column(
                modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White),
        ) {
            OrderType(
                    amountWithoutExtra = paymentAndOrderType.amountWithoutExtra,
                    extraAmount = paymentAndOrderType.extraAmount,
                    paymentType = paymentAndOrderType.paymentType,
                    serviceDetails = paymentAndOrderType.serviceDetails,
                    rightIconUrl = paymentAndOrderType.rightIconUrl
            )
        }
    }
}
