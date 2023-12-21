package me.kartdroid.androidkitchen2.html

import android.graphics.Typeface
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.HtmlCompat

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 09/11/23
 */

val string1 = "<span style=\"font-size: 12px;\">14/20</span> Rides Completed"

val string2 = "<font size=\"12px\">14/20</font> Rides Complete"
@Preview
@Composable
fun HtmlText() {
    Surface {
        Column {
            Text(text = HtmlCompat.fromHtml(string1, HtmlCompat.FROM_HTML_MODE_COMPACT).toAnnotatedString())
            Text(text = HtmlCompat.fromHtml(string2, HtmlCompat.FROM_HTML_MODE_COMPACT).toAnnotatedString())
        }
    }
}


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
        }
    }
}