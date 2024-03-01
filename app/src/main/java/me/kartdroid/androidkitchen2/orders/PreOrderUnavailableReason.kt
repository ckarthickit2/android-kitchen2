package me.kartdroid.androidkitchen2.orders

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 07/02/23.
 */
enum class PreOrderUnavailableReason {
    ORDER_EXPIRED,
    ORDER_CANCELLED_BY_CUSTOMER,
    ORDER_REJECTED_BY_CAPTAIN,
    ACCEPTED_BY_OTHER_CAPTAIN,
    ASSIGNED,
    NONE
}
