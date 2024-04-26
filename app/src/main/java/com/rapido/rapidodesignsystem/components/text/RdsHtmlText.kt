package com.rapido.rapidodesignsystem.components.text

import android.graphics.Typeface
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import android.text.style.TypefaceSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.rapido.rapidodesignsystem.tokens.base.RdsColors

private const val URL_TAG = "url_tag"

@Composable
fun RdsHtmlText(
    text: String,
    modifier: Modifier = Modifier,
    softWrap: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onLinkClicked: ((String) -> Unit) = {},
    flags: Int = HtmlCompat.FROM_HTML_MODE_COMPACT,
    urlSpanStyle: SpanStyle = SpanStyle(
        color = RdsColors.blue400,
        textDecoration = TextDecoration.Underline
    ),
    textType: RdsTextType,
    color: Color = Color.Unspecified
) {
    val typography = textType.typography

    val content = remember(text) {
        text.asHTML(typography, flags, urlSpanStyle, color)
    }
    ClickableText(
        modifier = modifier,
        text = content,
        style = textType.typography,
        softWrap = softWrap,
        overflow = overflow,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
        onClick = {
            content
                .getStringAnnotations(URL_TAG, it, it)
                .firstOrNull()
                ?.let { stringAnnotation -> onLinkClicked(stringAnnotation.item) }
        }
    )
}

private fun String.asHTML(
    typography: TextStyle,
    flags: Int,
    urlSpanStyle: SpanStyle,
    defaultColor: Color
) = buildAnnotatedString {
    val spanned = HtmlCompat.fromHtml(this@asHTML, flags)
    val spans = spanned.getSpans(0, spanned.length, Any::class.java)

    append(spanned.toString())

    spans
        .filter { it !is BulletSpan }
        .forEach { span ->
            val start = spanned.getSpanStart(span)
            val end = spanned.getSpanEnd(span)
            when (span) {
                is RelativeSizeSpan -> SpanStyle(
                    fontSize = (typography.fontSize.value * span.sizeChange).sp

                )
                is StyleSpan -> span.spanStyle()
                is UnderlineSpan -> SpanStyle(
                    textDecoration = TextDecoration.Underline

                )
                is ForegroundColorSpan -> {
                    SpanStyle(color = Color(span.foregroundColor))
                }
                is TypefaceSpan -> typography.fontFamily?.let {
                    SpanStyle(fontFamily = it)
                }
                is StrikethroughSpan -> SpanStyle(
                    textDecoration = TextDecoration.LineThrough

                )
                is SuperscriptSpan -> SpanStyle(
                    baselineShift = BaselineShift.Superscript

                )
                is SubscriptSpan -> SpanStyle(
                    baselineShift = BaselineShift.Subscript

                )
                is URLSpan -> {
                    addStringAnnotation(
                        tag = URL_TAG,
                        annotation = span.url,
                        start = start,
                        end = end
                    )
                    urlSpanStyle
                }
                else -> {
                    null
                }
            }?.let { spanStyle ->
                addStyle(spanStyle, start, end)
            }
        }
}

internal fun StyleSpan.spanStyle(): SpanStyle? = when (style) {
    Typeface.BOLD -> SpanStyle(fontWeight = FontWeight.Bold)
    Typeface.ITALIC -> SpanStyle(fontStyle = FontStyle.Italic)
    Typeface.BOLD_ITALIC -> SpanStyle(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic
    )
    else -> null
}
