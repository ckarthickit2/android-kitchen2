package me.kartdroid.androidkitchen2.orders

import androidx.annotation.Keep

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 01/02/23.
 */
@Keep
data class OrderPropagation(
    val isMultiOrder: Boolean,
    val type: String,
    val riderCount: Int,
) {
    companion object {
        val UNDEFINED_PROPAGATION = OrderPropagation(
            false,
            "",
            0
        )
    }
}
