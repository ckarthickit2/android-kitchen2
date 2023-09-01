package me.kartdroid.androidkitchen2.presentation

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UiText {
    data class DynamicString(val value: String) : UiText
    class StringResource(@StringRes val platformResId: Int, vararg val args: Any) : UiText

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(platformResId, *args)
        }
    }

    fun asString(context: Context?): String {
        if (context == null) {
            return ""
        }
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(platformResId, *args)
        }
    }
}
