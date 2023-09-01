package me.kartdroid.androidkitchen2.presentation

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 13/04/23.
 */
@Immutable
sealed interface UIImageResource {
    data class RemoteResource(val uri: String, @DrawableRes val fallbackResourceID: Int = -1, val placeholderDrawable: Drawable? = null) : UIImageResource
    data class DrawableResource(@DrawableRes val resourceID: Int) : UIImageResource
}
