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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
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
import me.kartdroid.androidkitchen2.subscription.models.AvailableSubscription
import me.kartdroid.androidkitchen2.subscription.models.LifetimeCommissionSavedInfo
import me.kartdroid.androidkitchen2.subscription.models.SubscriptionInfoV2
import me.kartdroid.androidkitchen2.subscription.v2.ui.preview.SubsScreenPreviewParameterProvider
import me.kartdroid.androidkitchen2.subscription.v2.ui.preview.previewTnCInfo
import me.kartdroid.androidkitchen2.utils.toFormattedString

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 28/04/24
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SubscriptionScreenV2(
    subsInfo: SubscriptionInfoV2
) {
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
                primaryButtonText = stringResource(
                    id = R.string.proceed_to_pay_rs_x,
                    purchasePrice
                ),
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
                toolbarText = subsInfo.toolbarTitle,
                actionOnClick = { },
                trailingContent = {
                    HelpToolbarButton(
                        modifier = Modifier,
                        IFaqContext.FaqContext(
                            subsInfo.extraInfo.helpInfo.context,
                            subsInfo.extraInfo.helpInfo.title,
                        ),
                        onHelpClicked = { },
                        text = stringResource(id = R.string.help)
                    )
                },
                elevation = 2.dp,
            )
        },
        content = {
            val contentPadding = 156 * 0.85f
            val scrollConnection = remember {
                SubsContentScrollConnection(contentPadding)
            }
            Box(
                modifier = Modifier
                    .nestedScroll(scrollConnection)
                    .background(RdsColors.gray50)
                    .padding(it)
            ) {
                //Banner
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(subsInfo.bannerInfo.bannerImageUrl)
                        .size(Size.ORIGINAL)
                        .placeholder(R.drawable.ic_subs_banner_placeholder)
                        .error(R.drawable.ic_subs_banner_placeholder)
                        .build()
                )
                RdsImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(156.dp),
                    painter = painter,
                    contentDescription = null,
                )
                SubscriptionContent(
                    contentPadding = scrollConnection.subsContentPadding.dp,
                    subsInfo = subsInfo
                )
            }
        },
        footer = {
            if (subsInfo.selectedSubscriptionID.isNullOrBlank().not()) {
                RDSFooterCTA(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 40.dp)
                        .border(width = 1.dp, color = RdsColors.gray200)
                        .background(color = RdsColors.white),
                    showCalloutsIcon = false,
                    calloutsHTML = subsInfo.footerCTAInfo.calloutsHtmlText,
                    ctaInfo = CtaInfo(
                        text = subsInfo.footerCTAInfo.ctaLabel,
                        deepLink = subsInfo.footerCTAInfo.ctaDeepLink,
                    )
                )
            }
        }
    )
}

@Composable
fun SubscriptionContent(
    modifier: Modifier = Modifier,
    contentPadding: Dp,
    subsInfo: SubscriptionInfoV2,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .padding(top = contentPadding)
    ) {
        //Current + Upcoming Plan Cards
        item {
            SubscriptionsPurchasedSection(subsInfo = subsInfo)
        }

        //Available Plans
        if (subsInfo.availableSubscriptions.isNotEmpty()) {
            subscriptionsAvailableSection(
                itemModifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                availableSubscriptions = subsInfo.availableSubscriptions,
                selectedSubscriptionID = subsInfo.selectedSubscriptionID,
            )
        }

        //TNC Section
        item {
            TNCSection(tncInfo = previewTnCInfo)
        }
    }
}

@Composable
fun SubscriptionsPurchasedSection(
    modifier: Modifier = Modifier,
    subsInfo: SubscriptionInfoV2,
) {
    Column(
        modifier = modifier
            .background(color = RdsColors.white, shape = RoundedCornerShape(16.dp))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
    ) {
        if (subsInfo.lifetimeCommissionSavedInfo.commissionSavedLabel.isNotBlank()) {
            CommissionSavedContent(
                modifier = Modifier
                    .fillMaxWidth(),
                lifetimeCommissionSavedInfo = subsInfo.lifetimeCommissionSavedInfo,
            )
        }

        // Active (or) Expired Plan
        val currentSubscription = subsInfo.currentSubscription
        if (currentSubscription != null) {
            val theme = currentSubscription.theme()
            CompositionLocalProvider(
                RapidoLocalColors provides theme
            ) {

                SubscriptionSection(
                    sectionTitle = "Current Plan",
                ) {
                    PurchasedSubscriptionCard(
                        modifier = Modifier.padding(top = 8.dp),
                        subscription = currentSubscription
                    )
                }

            }
        }

        // Future Plan
        val upcomingSubs = subsInfo.upcomingSubscriptions
        if (upcomingSubs.isNotEmpty()) {
            SubscriptionSection(
                sectionTitle = "Upcoming Plan",
            ) {
                upcomingSubs.forEach { upcomingSub ->
                    val theme = upcomingSub.theme()
                    CompositionLocalProvider(
                        RapidoLocalColors provides theme
                    ) {
                        PurchasedSubscriptionCard(
                            modifier = Modifier.padding(top = 8.dp),
                            subscription = upcomingSub
                        )
                    }
                }
            }


        }
    }
}


fun LazyListScope.subscriptionsAvailableSection(
    itemModifier: Modifier,
    availableSubscriptions: List<AvailableSubscription>,
    selectedSubscriptionID: String?,
) {
    item {
        RdsTextView(
            modifier = itemModifier,
            //TODO: Add Localisation
            text = stringResource(R.string.select_your_next_plan),
            type = RdsTextType.Custom(
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            )
        )
    }
    itemsIndexed(
        availableSubscriptions,
        key = { _, item ->
            item.subscriptionId
        }
    ) { _, subscription ->
        CompositionLocalProvider(
            RapidoLocalColors provides RapidoDefaultOrderColors.copy(
                onSurfaceDimVariant = RdsColors.gray200,
            )
        ) {
            AvailableSubscriptionCard(
                modifier = itemModifier,
                subscription = subscription,
                isSelected = selectedSubscriptionID == subscription.subscriptionId
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
    modifier: Modifier = Modifier,
    lifetimeCommissionSavedInfo: LifetimeCommissionSavedInfo,
) {
    Row(modifier = modifier) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            RdsTextView(
                text = buildAnnotatedString {
                    withStyle(RdsTextType.DisplayMedium.typography.toSpanStyle()) {
                        append(lifetimeCommissionSavedInfo.commissionSavedLabel)
                    }
                    append("  ")
                    withStyle(SpanStyle(color = RdsColors.gray900)) {
                        //TODO: Add Localisations
                        append(stringResource(R.string.commission_saved))
                    }

                },
                style = RdsTextType.BodyMedium.typography.copy(
                    lineHeight = 16.sp,
                )
            )
            RdsHtmlText(
                text = lifetimeCommissionSavedInfo.commissionLifetimeLabel,
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


class SubsContentScrollConnection(
    private val maxContentPadding: Float,
) : NestedScrollConnection {
    var subsContentPadding: Float by mutableFloatStateOf(maxContentPadding)
        private set

    override fun onPreScroll(
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        val delta = available.y
        if (delta > 0) {
            return Offset.Zero
        }
        val previousPadding = subsContentPadding
        subsContentPadding = (previousPadding + delta).coerceIn(0f, maxContentPadding)
        return Offset(0f, subsContentPadding - previousPadding)
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        val delta = available.y
        if (delta < 0) {
            return Offset.Zero
        }
        val previousPadding = subsContentPadding
        subsContentPadding = (previousPadding + delta).coerceIn(0f, maxContentPadding)
        return Offset(0f, subsContentPadding - previousPadding)
    }
}


@Preview(heightDp = 1250)
@Composable
fun SubscriptionScreenV2Preview(@PreviewParameter(SubsScreenPreviewParameterProvider::class) subsInfo: SubscriptionInfoV2) {
    RapidoTheme {
        SubscriptionScreenV2(subsInfo = subsInfo)
    }
}