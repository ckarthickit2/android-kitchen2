package com.rapido.rapidodesignsystem.components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.tokens.base.RdsColors

/**
 * @property type The [RdsTextType] based on the different typography that you want
 * @property text The text to be displayed inside the text
 * @property modifier The [Modifier] that is to be applied to the text view
 */
@Composable
fun RdsTextView(
    type: RdsTextType,
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null
) {
    Text(
        text,
        maxLines = maxLines,
        style = type.typography.copy(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        modifier = modifier,
        overflow = overflow,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun RdsTextView(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null
) {
    Text(
        text,
        maxLines = maxLines,
        style = style.copy(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        modifier = modifier,
        overflow = overflow,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun RdsTextView(
    type: RdsTextType,
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null
) {
    Text(
        text,
        maxLines = maxLines,
        style = type.typography.copy(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        modifier = modifier,
        overflow = overflow,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun RdsTextView(
    text: AnnotatedString,
    style: TextStyle,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    inlineContent: Map<String, InlineTextContent> = mapOf()
) {
    Text(
        text,
        maxLines = maxLines,
        style = style.copy(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        modifier = modifier,
        overflow = overflow,
        color = color,
        textAlign = textAlign,
        inlineContent = inlineContent
    )
}

@Composable
fun RdsTextViewWithBulletPoint(
    type: RdsTextType,
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    bulletPointColor: Color = RdsColors.black,
    textModifier: Modifier = Modifier
) {

    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Box(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(8.dp)
                .background(bulletPointColor, shape = CircleShape),
        )

        RdsTextView(
            type = type,
            text = text,
            maxLines = maxLines,
            modifier = textModifier,
            overflow = overflow,
            color = color,
            textAlign = textAlign
        )
    }
}

@Preview(
    showBackground = true,
    name = "RdsText",
    group = "Typography"
)
@Composable
internal fun RdsTextViewPreview() {
    Column(Modifier.padding(8.dp)) {
        RdsTextView(type = RdsTextType.DisplayLarge, text = "DisplayLarge")
        RdsTextView(type = RdsTextType.DisplayMedium, text = "DisplayMedium")
        RdsTextView(type = RdsTextType.DisplaySmall, text = "DisplaySmall")
        Spacer(Modifier.height(10.dp))
        RdsTextView(type = RdsTextType.HeadlineLarge, text = "HeadlineLarge")
        RdsTextView(type = RdsTextType.HeadlineMedium, text = "HeadlineMedium")
        RdsTextView(type = RdsTextType.HeadlineSmall, text = "HeadlineSmall")
        Spacer(Modifier.height(10.dp))
        RdsTextView(type = RdsTextType.TitleLarge, text = "TitleLarge")
        RdsTextView(type = RdsTextType.TitleMedium, text = "TitleMedium")
        RdsTextView(type = RdsTextType.TitleSmall, text = "TitleSmall")
        Spacer(Modifier.height(10.dp))
        RdsTextView(type = RdsTextType.BodyLarge, text = "BodyLarge")
        RdsTextView(type = RdsTextType.BodyMedium, text = "BodyMedium")
        RdsTextView(type = RdsTextType.BodySmall, text = "BodySmall")
        Spacer(Modifier.height(10.dp))
        RdsTextView(type = RdsTextType.LabelLarge, text = "LabelLarge")
        RdsTextView(type = RdsTextType.LabelMedium, text = "LabelMedium")
        RdsTextView(type = RdsTextType.LabelSmall, text = "LabelSmall")
    }
}

@Preview(
    showBackground = true,
    name = "RdsText",
    group = "Typography"
)
@Composable
internal fun RdsTextViewWithBulletPoint() {
    RdsTextViewWithBulletPoint(
        text = "text with bullet point",
        type = RdsTextType.BodyLarge,
        color = RdsColors.black,
        bulletPointColor = RdsColors.black
    )
}
