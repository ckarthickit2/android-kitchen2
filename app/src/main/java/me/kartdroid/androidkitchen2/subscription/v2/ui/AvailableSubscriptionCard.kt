package me.kartdroid.androidkitchen2.subscription.v2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalAbsoluteElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.rapidodesignsystem.components.icon.RdsIcon
import com.rapido.rapidodesignsystem.components.icon.RdsIconConfig
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoDefaultOrderColors
import com.rapido.rapidodesignsystem.theme.RapidoLocalColors
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.subscription.models.AvailableSubscription
import me.kartdroid.androidkitchen2.subscription.v2.ui.preview.AvailableSubsPreviewProvider

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 26/04/24
 */

@Composable
fun AvailableSubscriptionCard(
    modifier: Modifier = Modifier,
    subscription: AvailableSubscription,
) {
    val absoluteElevation = LocalAbsoluteElevation.current + 4.dp
    val shape = RoundedCornerShape(8.dp)
    Box(
        modifier = modifier
            .shadow(elevation = absoluteElevation, shape = shape, clip = false)
            .background(color = MaterialTheme.colors.surface, shape = shape)
            .clip(shape)
            .fillMaxWidth(),
    ) {
        RdsTextView(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 8.dp, topEnd = 8.dp))
                .background(color = Color(0xFF3804A5))
                .padding(horizontal = 12.dp, vertical = 5.dp)
                .align(Alignment.TopEnd),
            text = stringResource(id = R.string.recommended),
            type = RdsTextType.Custom(
                TextStyle(
                    fontSize = 10.sp,
                    lineHeight = 16.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontWeight = FontWeight(500),
                    color = RdsColors.white,
                    textAlign = TextAlign.Center
                )
            ))
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            SelectionAndLabelRow(subscription = subscription)
            Spacer(modifier = Modifier.height(8.dp))
            SubscriptionInfoRow(subscription = subscription)
        }
    }
}

@Composable
fun SelectionAndLabelRow(subscription: AvailableSubscription) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RdsIcon(
            config = RdsIconConfig(
                painter = painterResource(id = R.drawable.ic_unselected_plan),
                tintColor = Color(0xFF3804A5), //Color.Unspecified
                modifier = Modifier.size(18.dp)
            ))
        /*RdsImage(
                painter = rememberVectorPainter(image = Icons.Filled.CheckCircle),
                modifier = Modifier.size(18.dp),
                colorFilter = ColorFilter.tint(Color(0xFF3804A5)),
                contentScale = ContentScale.Fit
                )*/
        Spacer(modifier = Modifier.width(16.dp))
        RdsTextView(
            text = subscription.title,
            type = RdsTextType.Custom(
                TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontWeight = FontWeight(700),
                )

            ),
        )
    }
}


@Preview
@Composable
fun AvailableSubscriptionCardPreview(@PreviewParameter(AvailableSubsPreviewProvider::class) subscription: AvailableSubscription) {
    Box(
        modifier = Modifier
            .background(RdsColors.gray_50)
            .padding(24.dp)
            .height(100.dp)
    ) {
        CompositionLocalProvider(
            RapidoLocalColors provides RapidoDefaultOrderColors.copy(
                onSurfaceDimVariant = RdsColors.gray100,
            )
        ) {
            AvailableSubscriptionCard(subscription = subscription)
        }
    }

}