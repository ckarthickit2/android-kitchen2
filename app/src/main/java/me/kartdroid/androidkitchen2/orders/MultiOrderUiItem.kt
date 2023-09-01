package me.kartdroid.androidkitchen2.orders

import java.util.concurrent.TimeUnit

/**
 * @author [Karthick Chinnathambi]()
 * @since 01/09/23
 */
data class MultiOrderUiItem(
        val id: String,
        val orderType: String,
        val serviceInfo: ServiceInfo,
        val propagation: OrderPropagation,
        val pickUpTitle: String,
        val pickUpAddress: String?,
        val isPickupVerified: Boolean,
        val dropTitle: String,
        val dropAddress: String?,
        val pickUpDistance: Double,
        val dropDistance: Double,
        val pickupEtaInMins: Int,
        val dropEtaInMins: Int,
        val orderCreatedTime: Long,
        val captainAcceptTimer: Int,
        val timeToEnableAcceptButton: Int,
        val orderAcceptanceWaitExpiryTimeInMs: Long = 0L,
        val isGTAOrder: Boolean,
        val amountWithoutExtra: Int,
        val extraAmount: Int,
        val amount: Int,
        val minutes: Int,
        val paymentType: String,
        val orderTipAmount: Int,
        val isBatchOrder: Boolean = false,
        val isBatchedOrder: Boolean,
        val batchOrderSize: Int = 0,
        val unavailableReason: PreOrderUnavailableReason,
        val unavailableAt: Long,
        val acceptedAt: Long,
        val isPAPEnabled: Boolean,
        val isOnRideBooking: Boolean,
        val templateName: String = "default",
        val headerRightIconUrl: String = "",
        val navItemIconUrl: String = "",
        val extraAmountDisplayProps: ExtraAmountDisplayProps
) {

    private val remainingTime: Long get() = this.captainAcceptTimer - ((Utilities.getTrueTime() - this.orderCreatedTime) / 1000)

    val isAcceptButtonEnable: Boolean get() = remainingTime <= captainAcceptTimer - timeToEnableAcceptButton

    val timerText: String get() = if (remainingRenderTimer > 0) (TimeUnit.MILLISECONDS.toSeconds(remainingRenderTimer)).toString() else "0"
    val timerProgressSeconds: Float get() = TimeUnit.MILLISECONDS.toSeconds(remainingRenderTimer).toFloat()

    val remainingTimeToEnableAcceptButtonSeconds: Int get() = (timeToEnableAcceptButton - TimeUnit.MILLISECONDS.toSeconds(Utilities.getTrueTime() - this.orderCreatedTime)).toInt().coerceAtLeast(0)

    val acceptButtonEnableProgress: Float get() = if (timeToEnableAcceptButton > 0 && remainingTimeToEnableAcceptButtonSeconds > 0) (remainingTimeToEnableAcceptButtonSeconds.toFloat() / timeToEnableAcceptButton) else 0f

    val orderAcceptTimerProgress: Float get() = if (captainAcceptTimer > 0) (timerProgressSeconds / (captainAcceptTimer - timeToEnableAcceptButton)).coerceIn(0f, 1f) else 0f

    val isCurrentlyInPAP: Boolean
        get() {
            val curTime = Utilities.getTrueTime()
            return isPAPEnabled && unavailableAt == -1L && acceptedAt != -1L && curTime < (acceptedAt + orderAcceptanceWaitExpiryTimeInMs)
        }

    val remainingRenderTimer: Long
        get() {
            val curTime = Utilities.getTrueTime()
            val captainAcceptTimerMS = TimeUnit.SECONDS.toMillis(captainAcceptTimer.toLong())
            return if (this.unavailableAt != -1L) {
                if (unavailableReason in listOf(
                                PreOrderUnavailableReason.ACCEPTED_BY_OTHER_CAPTAIN,
                                PreOrderUnavailableReason.ORDER_CANCELLED_BY_CUSTOMER
                        ) &&
                        curTime < (unavailableAt + 2500)
                ) {
                    (unavailableAt + 2500) - curTime
                } else {
                    0
                }
            } else if (acceptedAt != -1L && curTime < (acceptedAt + orderAcceptanceWaitExpiryTimeInMs)) {
                (acceptedAt + orderAcceptanceWaitExpiryTimeInMs - curTime)
            } else if (curTime < (orderCreatedTime + captainAcceptTimerMS)) {
                (orderCreatedTime + captainAcceptTimerMS - curTime)
            } else {
                0
            }
        }
}