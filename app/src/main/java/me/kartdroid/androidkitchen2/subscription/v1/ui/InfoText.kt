package me.kartdroid.androidkitchen2.subscription.v1.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors


/**
 * Created by Abhishek Raj on 15/06/23.
 */

@Composable
fun InfoText(
    modifier: Modifier = Modifier,
    infoText: String
) {
    RdsTextView(
        text = infoText,
        style = RdsTextType.TitleLarge.typography.copy(
            fontSize = 20.sp,
            lineHeight = 27.sp,
            fontWeight = FontWeight.Bold,
            color = RdsColors.black,
        ),
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp)
            .wrapContentHeight(),
        maxLines = 3
    )
}

@Preview(device = Devices.PIXEL_2_XL, showSystemUi = true)
@Composable
fun PreviewInfoText() {
    RapidoTheme {
        InfoText(modifier = Modifier, infoText = "To get ₹0 Commission orders, Buy a plan")
    }
}
