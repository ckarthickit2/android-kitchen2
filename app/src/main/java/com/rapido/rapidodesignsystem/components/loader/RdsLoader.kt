package com.rapido.rapidodesignsystem.components.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors

@Composable
fun RdsLoader(
    modifier: Modifier,
    loaderText: String,
    backgroundColor: Color = RdsColors.black,
    loaderTextColor: Color = RdsColors.white,
    loaderIndicatorColor: Color = RdsColors.yellow400
) {
    Column(
        modifier = modifier
            .height(130.dp)
            .padding(12.dp, 12.dp)
            .fillMaxWidth()
            .background(color = backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = loaderIndicatorColor
        )
        if (loaderText.isNullOrEmpty().not()) {
            RdsTextView(
                type = RdsTextType.BodyMedium,
                text = loaderText,
                color = loaderTextColor,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun RdsLoaderDialog(
    title: String,
    subTitle: String
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = RdsColors.semiTransparent
        ) {
            Column(
                modifier = Modifier
                    .background(color = RdsColors.white)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .width(64.dp)
                        .height(64.dp)
                        .align(Alignment.CenterHorizontally),
                    color = RdsColors.yellow400,
                    strokeWidth = 6.dp,
                )
                RdsTextView(
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .wrapContentWidth()
                        .align(Alignment.CenterHorizontally),
                    text = title,
                    style = RdsTextType.BodyLarge.typography.copy(
                        fontWeight = FontWeight.Bold,
                        color = RdsColors.black,
                        fontSize = 18.sp
                    )
                )
                RdsTextView(
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.CenterHorizontally),
                    text = subTitle,
                    style = RdsTextType.BodyMedium.typography.copy(
                        fontWeight = FontWeight.Normal,
                        color = RdsColors.black,
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewRdsLoader() {
    RapidoTheme {
        RdsLoader(modifier = Modifier, loaderText = "Loading")
    }
}

@Preview
@Composable
fun RDSLoaderDialogPreview() {
    RapidoTheme {
        RdsLoaderDialog(title = "Title", subTitle = "SubTitle Lorem Ipsum")
    }
}
