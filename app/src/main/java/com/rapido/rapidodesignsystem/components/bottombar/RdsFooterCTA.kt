package com.rapido.rapidodesignsystem.components.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.components.button.RdsPrimaryButton
import com.rapido.rapidodesignsystem.components.icon.RdsIcon
import com.rapido.rapidodesignsystem.components.icon.RdsIconConfig
import com.rapido.rapidodesignsystem.components.text.RdsHtmlText
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.R

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 28/04/24
 */


data class CtaInfo(
    val text: String,
    val deepLink: String,
)

@Composable
fun RDSFooterCTA(
    modifier: Modifier = Modifier,
    calloutsSurfaceColor: Color = RdsColors.gray50,
    onCalloutsColor: Color = RdsColors.gray900,
    showCalloutsIcon: Boolean = true,
    calloutsHTML: String,
    ctaInfo: CtaInfo,
    onCtaClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.padding(
            start = 28.dp,
            end = 28.dp,
            top = 16.dp,
            bottom = 24.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (calloutsHTML.isNotBlank()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = calloutsSurfaceColor, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                if (showCalloutsIcon) {
                    RdsIcon(
                        config = RdsIconConfig(
                            painter = painterResource(id = R.drawable.ic_info),
                            tintColor = onCalloutsColor,
                            //DEV-NOTE: This should match the height of the calloutsHTMLText
                            modifier = Modifier.size(RdsTextType.BodyLarge.typography.lineHeight.value.dp)
                        )
                    )
                }
                RdsHtmlText(
                    text = calloutsHTML,
                    textType = RdsTextType.Custom(
                        RdsTextType.BodyLarge.typography.copy(color = onCalloutsColor)
                    )
                )
            }
        }

        RdsPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = ctaInfo.text,
            onClick = onCtaClick
        )
    }
}


@Preview
@Composable
fun RDSFooterCTAPreview() {
    RapidoTheme {
        Surface {
            RDSFooterCTA(
                showCalloutsIcon = false,
                calloutsHTML = "<html>Pay <b>₹199 + ₹152</b> (18% GST)</html>",
                ctaInfo = CtaInfo(
                    text = "Subscribe",
                    deepLink = "https://www.google.com"
                )
            )
        }
    }
}



