package me.kartdroid.androidkitchen2.subscription.v1.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.R

/**
 * Created by Abhishek Raj on 22/06/23.
 */

@Composable
fun NoSubscription(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.wrapContentHeight()) {
        val imageHeight = 256
        Image(
            modifier = Modifier
                .height(imageHeight.dp)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_subscription_placeholder_banner),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        Row(
            modifier = Modifier
                .padding(top = (0.75f * imageHeight).dp)
                .padding()
                .wrapContentSize()
                .wrapContentWidth()
        ) {
            Spacer(modifier = Modifier.width(24.dp))
            Surface(
                modifier = Modifier
                    .weight(1f),
                border = BorderStroke(2.dp, RdsColors.purpleLight5),
                shape = RoundedCornerShape(8.dp),
            ) {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
                    RdsTextView(
                        modifier = Modifier, text = "No active plans for now",
                        style = RdsTextType.TitleMedium.typography.copy(
                            color = RdsColors.dark1,
                            fontSize = 20.sp,
                            lineHeight = 27.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        maxLines = 2
                    )

                    RdsTextView(
                        modifier = Modifier, text = "Check again later",
                        style = RdsTextType.TitleMedium.typography.copy(
                            color = RdsColors.dark1,
                            fontSize = 16.sp,
                            lineHeight = 22.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        maxLines = 2
                    )
                }
            }
            Spacer(modifier = Modifier.width(24.dp))
        }
    }
}

@Preview(device = Devices.PIXEL_2_XL, showSystemUi = true)
@Composable
fun PreviewNoSubscription(
    modifier: Modifier = Modifier
) {
    RapidoTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            NoSubscription()
        }
    }
}
