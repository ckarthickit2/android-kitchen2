package me.kartdroid.androidkitchen2.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.kartdroid.androidkitchen2.ui.theme.RapidoTheme
import me.kartdroid.androidkitchen2.ui.theme.RdsColors
import me.kartdroid.androidkitchen2.utils.addTestTag

@Composable
fun OrderDistance(
    title: String,
    distance: String,
    distanceTestTag: String = "",
) {
    OrderDistanceTitle(
        title = title
    )
    Spacer(modifier = Modifier.width(4.dp))
    OrderDistance(
        distance = distance,
        distanceTestTag = distanceTestTag
    )
}

@Composable
private fun OrderDistanceTitle(title: String) {
    Text(
        style = MaterialTheme.typography.bodyMedium.copy(
            lineHeight = 20.sp,
            color = RapidoTheme.colors.onSurfaceDimVariant
        ),
        text = title,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun OrderDistance(
    distance: String,
    distanceTestTag: String = ""
) {
    Text(
        modifier = Modifier.addTestTag(distanceTestTag),
        style = MaterialTheme.typography.bodyLarge.copy(
            lineHeight = 20.sp,
            fontWeight = FontWeight.Bold,
            color = RapidoTheme.colors.onSurface
        ),
        text = distance,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun PreviewOrderDistanceHorizental() {
    Box(Modifier.background(color = RdsColors.white)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OrderDistance(title = "Pickup", distance = "1.2km", distanceTestTag = "")
        }
    }
}

@Preview
@Composable
private fun PreviewOrderDistanceVertical() {
    Box(Modifier.background(color = RdsColors.white)) {
        Column {
            OrderDistance(title = "Pickup", distance = "1.2km", distanceTestTag = "")
        }
    }
}
