package com.rapido.rapidodesignsystem.utils.extension

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
// import com.google.accompanist.placeholder.PlaceholderHighlight
// import com.google.accompanist.placeholder.material.shimmer
// import com.google.accompanist.placeholder.placeholder
// import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import com.rapido.rapidodesignsystem.utils.multipleEventsCutter

/**
 * Returns Activity or null
 */

fun Context.getActivity(): Activity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is Activity) {
            return currentContext
        }
        currentContext = currentContext.baseContext
    }
    return null
}

/**
 * Modifier that makes the composable clickable only once
 */
fun Modifier.clickableSingle(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    multipleEventsCutter { manager ->
        Modifier.clickable(
            enabled = enabled,
            onClickLabel = onClickLabel,
            onClick = { manager.processEvent { onClick() } },
            role = role,
            indication = LocalIndication.current,
            interactionSource = remember { MutableInteractionSource() }
        )
    }
}
//
// /**
// * Adds a shimmer loading indicator when the [isLoading] flag is set to true.
// *
// * The size of the shimmer should be customised according to the composable where
// * this modifier will be applied by chaining other modifiers to it
// */
// fun Modifier.isLoading(isLoading: Boolean) =
//    composed {
//        this.placeholder(
//            visible = isLoading,
//            highlight = PlaceholderHighlight.shimmer(),
//            color = RdsColors.gray100
//        )
//    }

/**
 * This is used to prevent showing a ripple when a click is detected, hence
 * showing the behaviour that a certain element did not respond to clicks
 */
inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = { onClick() }
    )
}

/**
 * Modifier that can add a shadow to an icon button, and make it circular
 *
 * Usually used for [RdsIconButton] that it overlay on the maps
 */
fun Modifier.iconShadow(
    cornerRadius: Dp = 100.dp,
    elevation: Dp = 4.dp,
    color: Color = Color.White,
    size: Dp = 48.dp
) = composed {
    Modifier
        .shadow(elevation = elevation, shape = RoundedCornerShape(cornerRadius), clip = true)
        .background(shape = RoundedCornerShape(cornerRadius), color = color)
        .size(size)
}

fun Modifier.dashedBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

        this.then(
            Modifier.drawWithCache {
                onDrawBehind {
                    val stroke = Stroke(
                        width = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )

                    drawRoundRect(
                        color = color,
                        style = stroke,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }
        )
    }
)
