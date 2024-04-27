package me.kartdroid.androidkitchen2.subscription.v2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionV2

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 27/04/24
 */

@Composable
fun SubscriptionInfoRow(
    modifier: Modifier = Modifier,
    subscription: SubscriptionV2
) {
    Row(
        modifier = modifier.padding(start = 34.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        with(subscription.eligibleConsumptionInfo) {
            UnitAndTypeInfo(
                modifier = Modifier.weight(1f),
                unitLabel = unitsLabel,
                typeLabel = typeLabel,
            )
        }
        Box(modifier = Modifier
            .width(1.dp)
            .padding(vertical = 8.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(0.5.dp))
            .background(RapidoTheme.colors.onSurfaceDimVariant)

        )
        with(subscription.durationInfo) {
            UnitAndTypeInfo(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp),
                unitLabel = unitsLabel,
                typeLabel = typeLabel,
            )
        }
        Box(modifier = Modifier
            .width(1.dp)
            .padding(vertical = 8.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(0.5.dp))
            .background(RapidoTheme.colors.onSurfaceDimVariant)

        )
        with(subscription.amountInfo) {
            UnitAndTypeInfo(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp),
                strikeThroughTypeLabel = true,
                unitLabel = currentPrice,
                typeLabel = strikeOutPrice,
            )
        }
    }
}

@Composable
fun UnitAndTypeInfo(
    modifier: Modifier = Modifier,
    strikeThroughTypeLabel: Boolean = false,
    unitLabel: String,
    typeLabel: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        RdsTextView(
            text = unitLabel,
            type = RdsTextType.Custom(
                TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontWeight = FontWeight(500),
                )
            )
        )
        RdsTextView(
            text = typeLabel,
            type = RdsTextType.Custom(
                TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontWeight = FontWeight(400),
                    color = RdsColors.gray1000,
                    textDecoration = if (strikeThroughTypeLabel) TextDecoration.LineThrough else TextDecoration.None
                )
            )
        )
    }
}