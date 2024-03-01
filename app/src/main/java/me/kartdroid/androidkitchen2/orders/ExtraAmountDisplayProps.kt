package me.kartdroid.androidkitchen2.orders

import androidx.compose.runtime.Immutable

@Immutable
data class ExtraAmountDisplayProps(val extraAmountColor: String = "", val tagDisplayProps: List<TagDisplayProp> = emptyList()) {

    data class TagDisplayProp(
        val backgroundColor: String = "",
        val prefixIconURL: String = "",
        val name: String = "",
        val textColor: String = ""
    )
}
