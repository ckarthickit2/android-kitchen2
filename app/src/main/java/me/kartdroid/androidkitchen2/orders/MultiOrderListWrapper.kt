package me.kartdroid.androidkitchen2.orders

import androidx.compose.runtime.Immutable

/**
 * Collection classes like List, Set and Map are always determined unstable as it is not guaranteed they are immutable.
 * If recomposition happens, composable using list wont be skipped. Since we are using new List of MultiOrderUiItem everytime and items
 * are not added to existing list, wrapping MultiOrderUiItem list with in a wrapper marked as @Immutable so that composable using
 * this wrapper will be skipped during recomposition.
 */
@Immutable
class MultiOrderListWrapper(val items: List<MultiOrderUiItem>) {
    val isInPAP get() = items.any { it.isCurrentlyInPAP }
}
