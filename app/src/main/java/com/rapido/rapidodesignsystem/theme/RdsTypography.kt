package com.rapido.rapidodesignsystem.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.rapido.rapidodesignsystem.tokens.base.BaseTypography

val RdsTypography = Typography(
    h1 = with(BaseTypography.Display.Large) {
        getTextStyle(fontFamily, fontSize, fontWeight, lineHeight, letterSpacing)
    },
    h2 = with(BaseTypography.Display.Medium) {
        getTextStyle(fontFamily, fontSize, fontWeight, lineHeight, letterSpacing)
    },
    h3 = with(BaseTypography.Display.Small) {
        getTextStyle(fontFamily, fontSize, fontWeight, lineHeight, letterSpacing)
    },
    body1 = with(BaseTypography.Body.Large) {
        getTextStyle(fontFamily, fontSize, fontWeight, lineHeight, letterSpacing)
    },
    body2 = with(BaseTypography.Body.Medium) {
        getTextStyle(fontFamily, fontSize, fontWeight, lineHeight, letterSpacing)
    },
    caption = with(BaseTypography.Body.Small) {
        getTextStyle(fontFamily, fontSize, fontWeight, lineHeight, letterSpacing)
    },
    h4 = with(BaseTypography.Headline.Medium) {
        getTextStyle(fontFamily, fontSize, fontWeight, lineHeight, letterSpacing)
    },
    h5 = with(BaseTypography.Headline.Small) {
        getTextStyle(fontFamily, fontSize, fontWeight, lineHeight, letterSpacing)
    },
    button = with(BaseTypography.Label.Large) {
        getTextStyle(fontFamily, fontSize, fontWeight, lineHeight, letterSpacing)
    },
    overline = with(BaseTypography.Label.Small) {
        getTextStyle(fontFamily, fontSize, fontWeight, lineHeight, letterSpacing)
    },
    h6 = with(BaseTypography.Title.Large) {
        getTextStyle(fontFamily, fontSize, fontWeight, lineHeight, letterSpacing)
    },
    subtitle1 = with(BaseTypography.Title.Medium) {
        getTextStyle(fontFamily, fontSize, fontWeight, lineHeight, letterSpacing)
    },
    subtitle2 = with(BaseTypography.Title.Small) {
        getTextStyle(fontFamily, fontSize, fontWeight, lineHeight, letterSpacing)
    }
)

private fun getTextStyle(
    fontFamily: FontFamily,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    lineHeight: TextUnit,
    letterSpacing: TextUnit
): TextStyle {
    return TextStyle(
        fontFamily = fontFamily,
        fontSize = fontSize,
        fontWeight = fontWeight,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing
    )
}
