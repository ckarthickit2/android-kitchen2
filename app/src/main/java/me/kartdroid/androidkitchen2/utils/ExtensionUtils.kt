package me.kartdroid.androidkitchen2.utils

import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * Created by Abhishek Raj on 07/06/23.
 */

/**
 * If the given number is having zero as decimal place values, returns a stringified whole number
 * If the given number is having non-zero as decimal place values, returns a stringified decimal number
 * */
fun Float.toFormattedString(): String {
    return if (this.rem(1) == 0f) this.toInt().toString() else this.toString()
}

fun Double.toFormattedString(): String {
    return if (this.rem(1) == 0.0) this.toInt().toString() else this.toString()
}

fun BigDecimal.toFormattedString(): String {
    val decimalFormat = DecimalFormat("0.00")
    return decimalFormat.format(this).removeSuffix(".00")
}
