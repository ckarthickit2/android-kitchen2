package me.kartdroid.androidkitchen2.incentives

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.rapidodesignsystem.components.button.RdsPrimaryButton
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import kotlinx.collections.immutable.persistentListOf
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.presentation.UIImageResource
import me.kartdroid.androidkitchen2.serializers.SerializablePersistentList
import me.kartdroid.androidkitchen2.utils.UIImageResourceRender

data class RedeemCash(
    val id: String,
    val title: String,
    val body: String,
    val ctaInfo: CtaInfo,
    val isLocked: Boolean,
    val theme: String,
    val backgroundImage: String,
    val overlayImage: String,
    val confirmationUiMeta: ConfirmationUiMeta
)

data class CtaInfo(
    val leadingIcon: String,
    val label: String,
    val trailingIcon: String
)

data class ConfirmationUiMeta(
    val icons: SerializablePersistentList<String>,
    val title: String,
    val description: String,
    val ctaInfo: ConfirmationCtaInfo
)

data class ConfirmationCtaInfo(
    val label: String,
    val deeplink: String
)

@Composable
fun RedeemCashRewardComponent(
    info: RedeemCash,
    onClick: (info: RedeemCash) -> Unit,
    modifier: Modifier = Modifier,
) {
    val containerShape = RoundedCornerShape(12.dp)
    Box(modifier = modifier.clip(containerShape)) {

        UIImageResourceRender(
            resource = UIImageResource.DrawableResource(R.drawable.sample_bill), //UIImageResource.RemoteResource(info.backgroundImage),
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(start = 12.dp, top = 8.dp, end = 12.dp, bottom = 12.dp)
        ) {
            RdsTextView(
                text = info.title,
                style = RdsTextType.BodyLarge.typography.copy(fontWeight = FontWeight.SemiBold),
                color = me.kartdroid.androidkitchen2.ui.theme.RapidoTheme.colors.onSurface
            )
            RdsTextView(
                text = info.body,
                type = RdsTextType.Custom(
                    TextStyle(
                        fontSize = 40.sp,
                        lineHeight = 44.sp,
                        fontWeight = FontWeight.Bold,
                        color = me.kartdroid.androidkitchen2.ui.theme.RapidoTheme.colors.onSurface,
                    )
                )
            )
            RdsPrimaryButton(
                modifier = Modifier
                    .defaultMinSize(minHeight = 38.dp)
                    .fillMaxWidth()
                    .padding(bottom = 48.dp),
                //colors = ButtonDefaults.buttonColors(backgroundColor = RapidoTheme.colors.secondaryContainer),
                onClick = { onClick(info) }
            ) {
                UIImageResourceRender(
                    resource = UIImageResource.RemoteResource(info.ctaInfo.leadingIcon),
                    modifier = Modifier.size(16.dp)
                )
                RdsTextView(
                    text = info.ctaInfo.label,
                    style = RdsTextType.BodyLarge.typography.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = me.kartdroid.androidkitchen2.ui.theme.RapidoTheme.colors.onSecondaryContainer
                )
                UIImageResourceRender(
                    resource = UIImageResource.RemoteResource(info.ctaInfo.trailingIcon),
                    modifier = Modifier.size(16.dp),
                    colorFilter = ColorFilter.tint(Color.White),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
        if (info.isLocked) {
            Box(
                modifier = Modifier
                    .background(Color(0x80888888))
                    .matchParentSize()
            )
            UIImageResourceRender(
                resource = UIImageResource.RemoteResource(info.overlayImage),
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(42.dp)
            )
        }
    }
}


@Preview
@Composable
private fun RedeemComponentPreview() {
    RapidoTheme {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(8.dp),
        ) {
            RedeemCashRewardComponent(
                info = redeemCashMock,
                onClick = {},
                modifier = Modifier
                    .wrapContentSize()
            )
        }
    }
}

val redeemCashMock = RedeemCash(
    id = "1",
    title = "Get Cash",
    body = "₹200",
    ctaInfo = CtaInfo(
        leadingIcon = "assets://ic_gem.svg",
        label = "1 Gems",
        trailingIcon = ""
    ),
    isLocked = true,
    theme = "small_card_purple",
    backgroundImage = "",
    overlayImage = "",
    confirmationUiMeta = ConfirmationUiMeta(
        icons = persistentListOf(),
        title = "Get ₹20 in your wallet now",
        description = "You earned ₹20 with 3 Gems",
        ctaInfo = ConfirmationCtaInfo(
            label = "Collect Reward",
            deeplink = ""
        )
    )
)