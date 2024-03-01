package me.kartdroid.androidkitchen2.utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.kartdroid.androidkitchen2.presentation.UIImageResource

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 13/04/23.
 */

@Composable
fun UIImageResourceRender(resource: UIImageResource, modifier: Modifier) {
    when (resource) {
        is UIImageResource.DrawableResource -> {
            Image(
                painter =
                painterResource(
                    id = resource.resourceID

                ),
                contentDescription = null,
                modifier = modifier
            )
        }
        is UIImageResource.RemoteResource -> {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(resource.uri)
                    .placeholder(resource.placeholderDrawable).build(),
                contentDescription = null,
                modifier = modifier
            )
        }
    }
}
