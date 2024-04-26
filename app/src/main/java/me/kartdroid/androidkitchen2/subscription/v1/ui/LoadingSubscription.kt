package me.kartdroid.androidkitchen2.subscription.v1.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.rapido.rapidodesignsystem.components.icon.RdsIcon
import com.rapido.rapidodesignsystem.components.icon.RdsIconConfig
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.R

/**
 * Created by Abhishek Raj on 14/06/23.
 */

@Composable
fun LoadingSubscription(
    modifier: Modifier = Modifier,
    listShimmerItems: List<Int> = arrayListOf(0, 1, 2, 3, 4)
) {
    LazyColumn(
        modifier = modifier,
        content = {
            items(listShimmerItems) { item ->
                when (item) {
                    0 -> {
                        Surface(
                            modifier = Modifier,
                            border = BorderStroke(1.dp, RdsColors.neutrals2),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            RdsTextView(
                                text = "",
                                type = RdsTextType.HeadlineSmall,
                                modifier = placeHolderModifier()
                                    .fillMaxWidth()
                                    .height(154.dp)
                                    .background(
                                        Brush.horizontalGradient(
                                            listOf(
                                                RdsColors.shimmerGradientStartColor,
                                                RdsColors.shimmerGradientMiddleColor,
                                                RdsColors.shimmerGradientEndColor
                                            )
                                        )
                                    )
                            )
                        }
                    }
                    else -> {
                        Surface(
                            modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp),
                            border = BorderStroke(1.dp, RdsColors.neutrals2),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            RdsTextView(
                                text = "",
                                type = RdsTextType.HeadlineSmall,
                                modifier = placeHolderModifier()
                                    .fillMaxWidth()
                                    .height(98.dp)
                                    .background(
                                        Brush.horizontalGradient(
                                            listOf(
                                                RdsColors.shimmerGradientStartColor,
                                                RdsColors.shimmerGradientMiddleColor,
                                                RdsColors.shimmerGradientEndColor
                                            )
                                        )
                                    )
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun LoadingToolbar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    Surface(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(18.dp))
            RdsIcon(
                config = RdsIconConfig(
                    svg = R.drawable.ic_keyboard_back,
                    modifier = Modifier.padding(8.dp),
                    onClick = onBackPressed
                )
            )
            Spacer(modifier = Modifier.width(25.dp))
            Surface(
                modifier = Modifier,
                border = BorderStroke(1.dp, RdsColors.neutrals2),
                shape = RoundedCornerShape(8.dp)
            ) {
                RdsTextView(
                    text = "",
                    type = RdsTextType.HeadlineSmall,
                    modifier = placeHolderModifier()
                        .width(167.dp)
                        .height(24.dp)
                )
            }
        }
    }
}

private fun placeHolderModifier(isVisible: Boolean = true, defaultModifier: Modifier = Modifier): Modifier {
    return if (isVisible) {
        Modifier.placeholder(
            visible = true,
            color = RdsColors.shimmerGradientStartColor,
            highlight = PlaceholderHighlight
                .shimmer(highlightColor = RdsColors.shimmerGradientMiddleColor),
            shape = RoundedCornerShape(8.dp)
        )
    } else {
        defaultModifier
    }
}

@Preview
@Composable
fun PreviewLoadingToolbar(
    modifier: Modifier = Modifier
) {
    RapidoTheme {
        LoadingToolbar(onBackPressed = {})
    }
}

@Preview
@Composable
fun PreviewSubscriptionLoading() {
    LoadingSubscription(modifier = Modifier)
}
