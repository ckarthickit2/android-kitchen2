package me.kartdroid.androidkitchen2.subscription.v2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.rapidodesignsystem.colors.RapidoThemeColors
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoLocalColors
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.subscription.models.PurchasedSubscriptionV2
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionV2
import me.kartdroid.androidkitchen2.subscription.v2.ui.preview.SubscriptionInfoRowPreviewProvider

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
        modifier = modifier,
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
            .height(36.dp)
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
            .height(36.dp)
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
                unitLabelStyle = RdsTextType.LabelLarge.typography.copy(
                    fontSize = 16.sp,
                )
            )
        }
    }
}

@Composable
fun UnitAndTypeInfo(
    modifier: Modifier = Modifier,
    strikeThroughTypeLabel: Boolean = false,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    unitLabelStyle: TextStyle = RdsTextType.LabelSmall.typography.copy(
        fontSize = 16.sp,
        letterSpacing = TextUnit.Unspecified,
    ),
    unitLabel: String,
    typeLabel: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment,
    ) {
        RdsTextView(
            text = unitLabel,
            style = unitLabelStyle,
        )
        RdsTextView(
            text = typeLabel,
            style = RdsTextType.BodySmall.typography.copy(
                lineHeight = 20.sp,
                color = RdsColors.gray1000,
                textDecoration = if (strikeThroughTypeLabel) TextDecoration.LineThrough else TextDecoration.None
            )
        )
    }
}


@Preview
@Composable
fun SubscriptionInfoRowPreview(@PreviewParameter(SubscriptionInfoRowPreviewProvider::class) subsAndTheme: Pair<PurchasedSubscriptionV2, RapidoThemeColors>) {
    val (subscription, theme) = subsAndTheme
    Box(
        modifier = Modifier
            .background(RdsColors.gray_50)
            .padding(24.dp)
            .wrapContentSize()
    ) {
        RapidoTheme {
            CompositionLocalProvider(
                RapidoLocalColors provides theme
            ) {
                SubscriptionInfoRow(subscription = subscription)
            }
        }
    }

}