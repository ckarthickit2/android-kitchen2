package me.kartdroid.androidkitchen2.orders

import androidx.compose.runtime.Immutable
import me.kartdroid.androidkitchen2.presentation.UIImageResource
import me.kartdroid.androidkitchen2.presentation.UiText

@Immutable
data class ServiceInfo(
        val serviceName: UiText,
        val serviceIcon: UIImageResource,
        val servicePlaceHolder: UIImageResource? = null,
)
