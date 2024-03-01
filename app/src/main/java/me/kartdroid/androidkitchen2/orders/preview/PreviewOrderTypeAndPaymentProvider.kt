package me.kartdroid.androidkitchen2.orders.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.orders.ServiceInfo
import me.kartdroid.androidkitchen2.presentation.UIImageResource
import me.kartdroid.androidkitchen2.presentation.UiText

class PreviewOrderTypeAndPaymentProvider : PreviewParameterProvider<PreviewOrderTypeAndPaymentProvider.PreviewOrderTypeAndPayment> {
    override val values = sequenceOf(
        PreviewOrderTypeAndPayment(
            amountWithoutExtra = 200,
            extraAmount = 50,
            paymentType = "wallet",
            serviceDetails = ServiceInfo(
                UiText.StringResource(R.string.text_bike),
                serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_bike_multi_order_indicator_round)
            ),
            rightIconUrl = "abcd"
        ),
        PreviewOrderTypeAndPayment(
            amountWithoutExtra = 200,
            extraAmount = 50,
            paymentType = "",
            serviceDetails = ServiceInfo(
                UiText.StringResource(R.string.text_bike),
                serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_bike_multi_order_indicator_round)
            )
        ),
        PreviewOrderTypeAndPayment(
            amountWithoutExtra = 200,
            extraAmount = -1,
            paymentType = "cash",
            serviceDetails = ServiceInfo(
                UiText.StringResource(R.string.auto),
                serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_auto_multi_order_indicator_round)
            )
        ),
        PreviewOrderTypeAndPayment(
            amountWithoutExtra = -1,
            extraAmount = 50,
            paymentType = "wallet",
            serviceDetails = ServiceInfo(
                UiText.StringResource(R.string.text_bike),
                serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_bike_multi_order_indicator_round)
            )
        ),
        PreviewOrderTypeAndPayment(
            amountWithoutExtra = -1,
            extraAmount = -1,
            paymentType = "wallet",
            serviceDetails = ServiceInfo(
                UiText.StringResource(R.string.local),
                serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_local_multi_order_indicator_round)
            )
        ),
        PreviewOrderTypeAndPayment(
            amountWithoutExtra = -1,
            extraAmount = -1,
            paymentType = "",
            serviceDetails = ServiceInfo(
                UiText.StringResource(R.string.local),
                serviceIcon = UIImageResource.DrawableResource(R.drawable.ic_local_multi_order_indicator_round)
            )
        )
    )

    data class PreviewOrderTypeAndPayment(
        val amountWithoutExtra: Int,
        val extraAmount: Int,
        val paymentType: String,
        val serviceDetails: ServiceInfo,
        val rightIconUrl: String = ""
    )
}
