package me.kartdroid.androidkitchen2.subscription.v2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.presentation.model.IFaqContext
import com.rapido.rapidodesignsystem.components.bottombar.CtaInfo
import com.rapido.rapidodesignsystem.components.bottombar.RDSFooterCTA
import com.rapido.rapidodesignsystem.components.bottomsheet.RdsBottomSheetScaffold
import com.rapido.rapidodesignsystem.components.bottomsheet.RdsGenericBottomSheet
import com.rapido.rapidodesignsystem.components.button.HelpToolbarButton
import com.rapido.rapidodesignsystem.components.icon.RdsImage
import com.rapido.rapidodesignsystem.components.text.RdsHtmlText
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.components.toolbar.TopAppBarWithSubtitle
import com.rapido.rapidodesignsystem.theme.RapidoDefaultOrderColors
import com.rapido.rapidodesignsystem.theme.RapidoLocalColors
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import com.rapido.rider.subscriptions.presentation.ui.composables.TnCInfo
import com.rapido.rider.subscriptions.presentation.ui.composables.TnCTitle
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.subscription.v2.ui.preview.AvailableSubsPreviewProvider
import me.kartdroid.androidkitchen2.subscription.v2.ui.preview.PurchaseProgressedSubscriptionPreviewProvider
import me.kartdroid.androidkitchen2.subscription.v2.ui.preview.previewTnCInfo
import me.kartdroid.androidkitchen2.utils.toFormattedString

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 28/04/24
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SubscriptionScreenV2() {
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    RdsBottomSheetScaffold(
        bottomSheetState = bottomSheetState,
        onSheetHide = {

        },
        sheetContent = {
            val purchasePrice = 590.00.toFormattedString()
            RdsGenericBottomSheet(
                title = String.format("Continue to buy ₹${purchasePrice} Recharge Plan"),
                description = null,
                primaryButtonText = stringResource(id = R.string.proceed_to_pay_rs_x, purchasePrice),
                onClickPrimaryButton = {

                },
                secondaryButtonText = stringResource(id = R.string.cancel),
                onClickSecondaryButton = {

                },
                onDismiss = {

                }
            )
        },
        topBar = {
            TopAppBarWithSubtitle(
                modifier = Modifier,
                actionIcon = R.drawable.ic_keyboard_back,
                toolbarText = stringResource(id = R.string.recharge_txt),
                actionOnClick = { },
                trailingContent = {
                    HelpToolbarButton(
                        modifier = Modifier,
                        IFaqContext.FaqContext(
                            "recharge",
                            "Recharge"
                        ),
                        onHelpClicked = { },
                        text = stringResource(id = R.string.help)
                    )
                },
                elevation = 2.dp,
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .background(RdsColors.gray50)
                    .padding(it)
            ) {
                //Banner
                RdsImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(156.dp),
                    painter = painterResource(id = R.drawable.pay_o_banner),
                    contentDescription = null,
                )
                SubscriptionContent(bannerHeight = 156.dp)
            }
        },
        footer = {
            RDSFooterCTA(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 40.dp)
                    .border(width = 1.dp, color = RdsColors.gray200)
                    .background(color = RdsColors.white),
                showCalloutsIcon = false,
                calloutsHTML = "<html>Pay <b>₹199 + ₹152</b> (18% GST)</html>",
                ctaInfo = CtaInfo(
                    text = "Subscribe",
                    deepLink = "https://www.google.com"
                )
            )
        }
    )
}

@Composable
fun SubscriptionContent(
    modifier: Modifier = Modifier,
    bannerHeight: Dp
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .padding(top = bannerHeight.times(0.85f))
    ) {
        //Current + Upcoming Plan Cards
        item {
            SubscriptionsPurchasedSection()
        }
        //Next Plan
        subscriptionsAvailableSection(
            itemModifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
        )
        //TNC Section
        item {
            TNCSection(tncInfo = previewTnCInfo)
        }
    }
}

@Composable
fun SubscriptionsPurchasedSection(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(color = RdsColors.white, shape = RoundedCornerShape(16.dp))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
    ) {
        CommissionSavedContent(
            modifier = Modifier
                .fillMaxWidth()
        )

        // Active (or) Expired Plan
        val (subscription, theme) = PurchaseProgressedSubscriptionPreviewProvider().values.toList()[0]
        CompositionLocalProvider(
            RapidoLocalColors provides theme
        ) {

            SubscriptionSection(
                sectionTitle = "Current Plan",
            ) {
                PurchasedSubscriptionCard(
                    modifier = Modifier.padding(top = 8.dp),
                    subscription = subscription
                )
            }

        }


        // Future Plan
        val (subscription2, theme2) = PurchaseProgressedSubscriptionPreviewProvider().values.toList()[3]
        CompositionLocalProvider(
            RapidoLocalColors provides theme2
        ) {
            SubscriptionSection(
                sectionTitle = "Upcoming Plan",
            ) {
                PurchasedSubscriptionCard(
                    modifier = Modifier.padding(top = 8.dp),
                    subscription = subscription2
                )
            }
        }
    }
}


fun LazyListScope.subscriptionsAvailableSection(
    itemModifier: Modifier
) {
    item {
        RdsTextView(
            modifier = itemModifier,
            text = "Select your next Plan",
            type = RdsTextType.Custom(
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            )
        )
    }
    itemsIndexed(AvailableSubsPreviewProvider().values.toList()) { index, subscription ->
        CompositionLocalProvider(
            RapidoLocalColors provides RapidoDefaultOrderColors.copy(
                onSurfaceDimVariant = RdsColors.gray200,
            )
        ) {
            AvailableSubscriptionCard(
                modifier = itemModifier,
                subscription = subscription,
            )
        }
    }
}


@Composable
fun TNCSection(
    tncInfo: List<String>
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TnCTitle()
        tncInfo.forEach { info ->
            TnCInfo(
                item = info,
            )
        }
    }
}


@Composable
fun CommissionSavedContent(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            RdsTextView(
                text = buildAnnotatedString {
                    withStyle(RdsTextType.DisplayMedium.typography.toSpanStyle()) {
                        append("₹34")
                    }
                    append(" ")
                    withStyle(SpanStyle(color = RdsColors.gray900)) {
                        append("Commission Saved")
                    }

                },
                style = RdsTextType.BodyMedium.typography.copy(
                    lineHeight = 16.sp,
                )
            )
            RdsHtmlText(
                text = "<html>In last <b>1</b> month</html>",
                textType = RdsTextType.Custom(
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Normal,
                    )
                )
            )
        }
        RdsImage(
            painter = painterResource(id = R.drawable.human_hand_placing_coin_stack),
            modifier = Modifier
                .padding(top = 6.dp)
                .width(49.5.dp)
                .height(56.dp)
        )
    }
}


@Composable
inline fun SubscriptionSection(
    modifier: Modifier = Modifier,
    sectionTitle: String,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        RdsTextView(
            text = sectionTitle,
            type = RdsTextType.Custom(
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            )
        )
        content()
    }
}

@Preview(heightDp = 1250)
@Composable
fun SubscriptionScreenV2Preview() {
    RapidoTheme {
        SubscriptionScreenV2()
    }
}