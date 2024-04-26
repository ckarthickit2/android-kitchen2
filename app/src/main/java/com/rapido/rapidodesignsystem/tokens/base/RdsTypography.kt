package com.rapido.rapidodesignsystem.tokens.base

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.kartdroid.androidkitchen2.R

val NotoSans = FontFamily(
    Font(R.font.noto_sans_regular, FontWeight.Normal),
    Font(R.font.noto_sans_medium, FontWeight.Medium),
    Font(R.font.noto_sans_bold, FontWeight.Bold),
    Font(R.font.noto_sans_semi_bold, FontWeight.SemiBold)
)

internal object BaseTypography {
    object Body {
        object Large {
            val letterSpacing = 0.sp
            val fontSize = 16.sp
            val lineHeight = 24.sp
            val fontWeight = FontWeight.Normal
            val fontFamily = NotoSans
        }

        object Medium {
            val letterSpacing = 0.sp
            val fontSize = 14.sp
            val lineHeight = 22.sp
            val fontWeight = FontWeight.Normal
            val fontFamily = NotoSans
        }

        object Small {
            val letterSpacing = 0.sp
            val fontSize = 12.sp
            val lineHeight = 20.sp
            val fontWeight = FontWeight.Normal
            val fontFamily = NotoSans
        }
    }

    object Label {
        object Large {
            val letterSpacing = 0.sp
            val fontSize = 12.sp
            val lineHeight = 24.sp
            val fontWeight = FontWeight.Bold
            val fontFamily = NotoSans
        }

        object Small {
            val letterSpacing = 0.5.sp
            val fontSize = 14.sp
            val lineHeight = 20.sp
            val fontWeight = FontWeight.Medium
            val fontFamily = NotoSans
        }
    }

    object Title {
        object Large {
            val letterSpacing = .25.sp
            val fontSize = 22.sp
            val lineHeight = 34.sp
            val fontWeight = FontWeight.Medium
            val fontFamily = NotoSans
        }

        object Medium {
            val letterSpacing = .25.sp
            val fontSize = 18.sp
            val lineHeight = 30.sp
            val fontWeight = FontWeight.Medium
            val fontFamily = NotoSans
        }

        object Small {
            val letterSpacing = 0.25.sp
            val fontSize = 16.sp
            val lineHeight = 28.sp
            val fontWeight = FontWeight.SemiBold
            val fontFamily = NotoSans
        }
    }

    object Headline {

        object Medium {
            val letterSpacing = .5.sp
            val fontSize = 22.sp
            val lineHeight = 30.sp
            val fontWeight = FontWeight.Bold
            val fontFamily = NotoSans
        }

        object Small {
            val letterSpacing = 0.2.sp
            val fontSize = 18.sp
            val lineHeight = 24.sp
            val fontWeight = FontWeight.Bold
            val fontFamily = NotoSans
        }
    }

    object Display {
        object Large {
            val letterSpacing = 0.sp
            val fontSize = 22.sp
            val lineHeight = 24.sp
            val fontWeight = FontWeight.Bold
            val fontFamily = NotoSans
        }

        object Medium {
            val letterSpacing = 0.sp
            val fontSize = 28.sp
            val lineHeight = 36.sp
            val fontWeight = FontWeight.Bold
            val fontFamily = NotoSans
        }

        object Small {
            val letterSpacing = 0.sp
            val fontSize = 20.sp
            val lineHeight = 24.sp
            val fontWeight = FontWeight.Bold
            val fontFamily = NotoSans
        }
    }
}
