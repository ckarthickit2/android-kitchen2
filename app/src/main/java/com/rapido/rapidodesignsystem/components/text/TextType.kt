package com.rapido.rapidodesignsystem.components.text

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import com.rapido.rapidodesignsystem.components.text.TextSize.Large
import com.rapido.rapidodesignsystem.components.text.TextSize.Medium
import com.rapido.rapidodesignsystem.components.text.TextSize.Small
import com.rapido.rapidodesignsystem.tokens.base.NotoSans

/**
 * The different variants according to the design system that are possible for the text sizes
 */
enum class Variant {
    Display, Headline, Label, Body, Title, Custom
}

/**
 * The different text sizes as per the design systems and T-shirt sizes
 */

enum class TextSize {
    Large, Medium, Small
}

@Immutable
sealed class RdsTextType(val variant: Variant, val textSize: TextSize) {
    object DisplayLarge : RdsTextType(variant = Variant.Display, textSize = Large)
    object DisplayMedium : RdsTextType(variant = Variant.Display, textSize = Medium)
    object DisplaySmall : RdsTextType(variant = Variant.Display, textSize = Small)
    object HeadlineLarge : RdsTextType(variant = Variant.Headline, textSize = Large)
    object HeadlineMedium : RdsTextType(variant = Variant.Headline, textSize = Medium)
    object HeadlineSmall : RdsTextType(variant = Variant.Headline, textSize = Small)
    object LabelLarge : RdsTextType(variant = Variant.Label, textSize = Large)
    object LabelMedium : RdsTextType(variant = Variant.Label, textSize = Medium)
    object LabelSmall : RdsTextType(variant = Variant.Label, textSize = Small)
    object BodyLarge : RdsTextType(variant = Variant.Body, textSize = Large)
    object BodyMedium : RdsTextType(variant = Variant.Body, textSize = Medium)
    object BodySmall : RdsTextType(variant = Variant.Body, textSize = Small)
    object TitleLarge : RdsTextType(variant = Variant.Title, textSize = Large)
    object TitleMedium : RdsTextType(variant = Variant.Title, textSize = Medium)
    object TitleSmall : RdsTextType(variant = Variant.Title, textSize = Small)

    data class Custom(val textStyle: TextStyle, val customTextSize: TextSize = Medium) : RdsTextType(Variant.Custom, customTextSize)

    val typography: TextStyle
        @Composable
        @ReadOnlyComposable
        get() =
            when (this.variant) {
                Variant.Display -> when (this.textSize) {
                    Large -> MaterialTheme.typography.h1
                    Medium -> MaterialTheme.typography.h2
                    Small -> MaterialTheme.typography.h3
                }
                Variant.Headline -> when (this.textSize) {
                    Large -> MaterialTheme.typography.h4
                    Medium -> MaterialTheme.typography.h4
                    Small -> MaterialTheme.typography.h5
                }
                Variant.Label -> when (this.textSize) {
                    Large -> MaterialTheme.typography.button
                    Medium -> MaterialTheme.typography.button
                    Small -> MaterialTheme.typography.overline
                }
                Variant.Body -> when (this.textSize) {
                    Large -> MaterialTheme.typography.body1
                    Medium -> MaterialTheme.typography.body2
                    Small -> MaterialTheme.typography.caption
                }
                Variant.Title -> when (this.textSize) {
                    Large -> MaterialTheme.typography.h6
                    Medium -> MaterialTheme.typography.subtitle1
                    Small -> MaterialTheme.typography.subtitle2
                }
                Variant.Custom -> {
                    require(this is Custom)
                    textStyle.copy(fontFamily = NotoSans)
                }
            }
}
