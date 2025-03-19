package me.kartdroid.androidkitchen2.playground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.kartdroid.androidkitchen2.ui.theme.AndroidKitchen2Theme


@Preview
@Composable
fun ImagePreview(modifier: Modifier = Modifier) {
    val assetBasePath = "file:///android_asset/"
    val iconPath = "asset://sample_bill.svg"
    AndroidKitchen2Theme {

        Surface {
            AssetImage(
                modifier = Modifier
                    .background(Color.Cyan)
                    .width(240.dp)
                    .height(360.dp),
                assetBasePath = assetBasePath,
                assetName = iconPath
            )
        }
    }
}

@Composable
fun AssetImage(
    assetBasePath: String,
    assetName: String,
    modifier: Modifier = Modifier,
) {
    val resolvedIconUri = remember(assetName) {
        val assetPrefix = "asset://"
        if (assetName.startsWith(assetPrefix)) {
            val iconName = assetName.removePrefix(assetPrefix)
            assetBasePath + iconName
        } else {
            assetName
        }
    }
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current).apply {
            data(resolvedIconUri)
            /* if (fallbackDrawableRes != null) {
                 error(fallbackDrawableRes)
             }*/
        }.build(),
        contentDescription = "Sample"
    )
}