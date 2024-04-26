package com.rapido.rapidodesignsystem.utils

import android.content.res.Resources
import android.graphics.Typeface
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.TypedValue
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.core.text.HtmlCompat

@Composable
fun rememberFocusRequester() = remember { FocusRequester() }

@Composable
fun MutableInteractionSource.isFocused() = collectIsFocusedAsState().value

@Composable
fun InvokeClickIfNotNull(
    interactionSource: MutableInteractionSource,
    onClick: (() -> Unit)?
) {
    val pressedState = interactionSource.interactions.collectAsState(
        initial = PressInteraction.Cancel(PressInteraction.Press(Offset.Zero))
    )
    if (pressedState.value is PressInteraction.Release) {
        onClick?.invoke()
        interactionSource.tryEmit(PressInteraction.Cancel(PressInteraction.Press(Offset.Zero)))
    }
}

fun String.htmlToString() = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT)

/** Converts a [Spanned] into an [AnnotatedString] trying to keep as much formatting as possible.
Currently supports `bold`, `italic`, `underline` and `color` **/
@Composable
fun Spanned.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
    val spanned = this@toAnnotatedString
    append(spanned.toString())
    getSpans(0, spanned.length, Any::class.java).forEach { span ->
        val start = getSpanStart(span)
        val end = getSpanEnd(span)
        when (span) {
            is StyleSpan -> when (span.style) {
                Typeface.BOLD -> addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, end)
                Typeface.ITALIC -> addStyle(SpanStyle(fontStyle = FontStyle.Italic), start, end)
                Typeface.BOLD_ITALIC -> addStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    ),
                    start, end
                )
            }

            is UnderlineSpan -> addStyle(
                SpanStyle(textDecoration = TextDecoration.Underline),
                start,
                end
            )

            is ForegroundColorSpan -> addStyle(
                SpanStyle(color = Color(span.foregroundColor)),
                start,
                end
            )
            is RelativeSizeSpan -> {
                // DEV-NOTE: Since couldn't find solution to control absolute size via HTML, using RelativeSizeSpan.
                // Assuming the default font-size to be 18 and lets use <big>, <small> which gives 1.25, 0.8 respectively as sizeChange
                val fontSize = span.sizeChange * 18.sp
                addStyle(
                    SpanStyle(fontSize = fontSize), start, end
                )
            }
        }
    }
}


val Number.toPx get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
)
