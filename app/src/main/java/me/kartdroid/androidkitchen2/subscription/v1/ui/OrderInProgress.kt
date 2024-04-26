package me.kartdroid.androidkitchen2.subscription.v1.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.rapido.rapidodesignsystem.components.loader.RdsLoader
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.R

/**
 * Created by Abhishek Raj on 19/06/23.
 */

@Composable
fun OrderInProgress() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = RdsColors.transparent)
    ) {
        RdsLoader(
            modifier = Modifier.align(Alignment.Center),
            loaderText = stringResource(id = R.string.loading)
        )
    }
}

@Preview(device = Devices.PIXEL_2_XL, showSystemUi = true)
@Composable
fun PreviewOrderInProgress(
    modifier: Modifier = Modifier
) {
    RapidoTheme {
        OrderInProgress()
    }
}
