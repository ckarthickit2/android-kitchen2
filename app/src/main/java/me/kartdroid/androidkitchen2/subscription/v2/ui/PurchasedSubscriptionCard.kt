package me.kartdroid.androidkitchen2.subscription.v2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalAbsoluteElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.rapidodesignsystem.components.RdsRibbon
import com.rapido.rapidodesignsystem.components.icon.DynamicIconLoader
import com.rapido.rapidodesignsystem.components.progress.LinearProgressBar
import com.rapido.rapidodesignsystem.components.progress.LinearProgressBarShape
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoDefaultOrderColors
import com.rapido.rapidodesignsystem.theme.RapidoLocalColors
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.subscription.models.ActivatedSubscriptionV2
import me.kartdroid.androidkitchen2.subscription.models.PurchaseProgressedSubscription
import me.kartdroid.androidkitchen2.subscription.v2.ui.preview.PurchaseProgressedSubscriptionPreviewProvider

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 26/04/24
 */
@Composable
fun PurchasedSubscriptionCard(
    modifier: Modifier = Modifier,
    subscription: PurchaseProgressedSubscription,
) {
    val absoluteElevation = LocalAbsoluteElevation.current + 4.dp
    val shape = RoundedCornerShape(8.dp)
    Box(
        modifier = modifier
            .shadow(elevation = absoluteElevation, shape = shape, clip = false)
            .background(color = RapidoTheme.colors.surface, shape = shape)
            .fillMaxWidth(),
    ) {
        RdsRibbon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 6.dp, y = 8.dp),
            text = subscription.validityTagInfo.label,
            iconUrl = "",
            iconAssetsSearchPath = "file:///android_asset/common-assets/",
            fallbackDrawableRes = subscription.validityTagInfo.indicatorDrawable(),
        )
        Column {
            SubscriptionStatusSection(
                subscription = subscription,
                modifier = Modifier
                    .fillMaxWidth()
            )
            SubscriptionProgressBar(
                if (subscription is ActivatedSubscriptionV2) {
                    subscription.consumptionInfo.consumedUnits / subscription.eligibleConsumptionInfo.units
                } else {
                    0.03f
                }
            )
            if (subscription is ActivatedSubscriptionV2) {
                SubscriptionProgressSection(
                    subscription = subscription,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            } else {
                SubscriptionInfoRow(
                    modifier = Modifier.background(RapidoTheme.colors.secondarySurface),
                    subscription = subscription
                )
            }
        }
    }
}

@Composable
fun SubscriptionStatusSection(
    modifier: Modifier = Modifier,
    subscription: PurchaseProgressedSubscription,
) {
    Column(
        modifier = modifier.padding(start = 16.dp, top = 16.dp)
    ) {
        RdsTextView(
            text = subscription.title,
            style = RdsTextType.TitleSmall.typography.copy(
                fontSize = 18.sp
            )
        )
        Row(
            modifier = Modifier.padding(top = 4.dp),
        ) {
            RdsTextView(
                text = subscription.purchaseProgressInfo.purchaseProgressLabel,
                style = RdsTextType.BodySmall.typography
            )
            DynamicIconLoader(
                iconPath = "assets://double_tick.xml",
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(16.dp),
                assetPath = "file:///android_asset/payments/",
                fallbackDrawableRes = subscription.purchaseProgressInfo.transactionStatus.indicatorDrawable()
            )
            Spacer(modifier = Modifier.weight(1f))
            RdsTextView(
                modifier = Modifier.padding(end = 8.dp, bottom = 12.dp),
                text = if (subscription is ActivatedSubscriptionV2) "${subscription.expiryInfo.dateLabel}\n${subscription.expiryInfo.timeLabel}" else " \n ",
                style = RdsTextType.BodySmall.typography.copy(
                    textAlign = TextAlign.Right
                )
            )
        }
    }
}

@Composable
fun SubscriptionProgressBar(
    progress: Float
) {
    LinearProgressBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp),
        backgroundColor = RapidoTheme.colors.onSurfaceDimVariant,
        progressColor = RapidoTheme.colors.onSurfaceVariant,
        progress = progress,
        progressCornerRadius = 16.dp,
        progressBarShape = LinearProgressBarShape.ROUNDED_PROGRESS_ONLY
    )
}

@Composable
fun SubscriptionProgressSection(
    modifier: Modifier,
    subscription: ActivatedSubscriptionV2,
) {
    Row(
        modifier = modifier
            .background(RapidoTheme.colors.secondarySurface)
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        UnitAndTypeInfo(
            unitLabel = "${subscription.consumptionInfo.consumedUnitsLabel} / ${subscription.eligibleConsumptionInfo.unitsLabel}",
            typeLabel = subscription.eligibleConsumptionInfo.typeLabel
        )
        Spacer(modifier = Modifier.weight(1f))
        UnitAndTypeInfo(unitLabel = subscription.commissionSavedInfo.savedAmountLabel, typeLabel = subscription.commissionSavedInfo.caption)
    }
}


@Preview
@Composable
fun PurchasedSubscriptionCardPreview(@PreviewParameter(PurchaseProgressedSubscriptionPreviewProvider::class) subscription: PurchaseProgressedSubscription) {
    Box(
        modifier = Modifier
            .background(RdsColors.gray_50)
            .padding(24.dp)
            .height(152.dp)
    ) {
        RapidoTheme {
            CompositionLocalProvider(
                RapidoLocalColors provides RapidoDefaultOrderColors.copy(
                    secondaryContainer = RdsColors.green2,
                    onSecondaryContainer = RdsColors.white,
                    onSurfaceVariant = RdsColors.greenDark500,
                    onSurfaceDimVariant = RdsColors.green200,
                    secondarySurface = RdsColors.greenLight,
                )
            ) {
                PurchasedSubscriptionCard(subscription = subscription)
            }
        }
    }
}