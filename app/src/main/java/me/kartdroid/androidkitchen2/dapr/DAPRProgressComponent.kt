package me.kartdroid.androidkitchen2.dapr

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 26/10/23
 */

@Composable
fun DaprCategoryBarComponent(
        modifier: Modifier = Modifier,
        completedRidesCount: Int,
        maxDroppedRides: Int,
        categorySegments: List<ProgressSegment>,
        barHeight: Dp = 12.dp,
        color: Color,
        backgroundColor: Color,
        highlightedSegmentDefinerTextColor: Color,
        nonHighlightedSegmentDefinerTextColor: Color
) {

    Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {

        //1-8, 9-16, 17-20
        categorySegments.forEachIndexed { index, progressSegment ->
            val scaleMin = progressSegment.ridesThreshold
            val scaleMax = if (categorySegments.lastIndex==index) maxDroppedRides else categorySegments[index + 1].ridesThreshold - 1
            val actualProgress = (completedRidesCount - scaleMin) + 1
            val progress = (actualProgress * 1f / (scaleMax - scaleMin + 1))
            val isActiveSegment = if (actualProgress==0) {
                index==0
            } else {
                completedRidesCount in scaleMin..scaleMax
            }
            val segmentShape = when (index) {
                0 -> RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50)
                categorySegments.lastIndex -> RoundedCornerShape(
                        topEndPercent = 50,
                        bottomEndPercent = 50
                )

                else -> RectangleShape
            }

            Column(modifier = Modifier.weight(1f)) {
                LinearProgressIndicator(
                        color = color,
                        trackColor = backgroundColor,
                        progress = progress,
                        modifier = Modifier
                                .clip(segmentShape)
                                .height(barHeight)
                )

                Text(
                        text = progressSegment.segmentRangeLabel,
                        style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 20.sp,
                                letterSpacing = 0.25.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (actualProgress > 0f || index==0) {
                                    highlightedSegmentDefinerTextColor
                                } else {
                                    nonHighlightedSegmentDefinerTextColor
                                },
                        ),
                        modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                        text = progressSegment.segmentCategoryLabel,
                        style = TextStyle(
                                fontSize = 10.sp,
                                lineHeight = 14.sp,
                                letterSpacing = 0.5.sp,
                                fontWeight = if (isActiveSegment) {
                                    FontWeight.Bold
                                } else {
                                    FontWeight.Medium
                                },
                                color = if (isActiveSegment) {
                                    color
                                } else {
                                    nonHighlightedSegmentDefinerTextColor
                                }
                        )
                )
            }
        }
    }
}

@Preview
@Composable
fun DAPRCategoryBarComponentPreview() {
    Column(
            modifier = Modifier
                    .background(Color.LightGray)
                    .padding(16.dp),
    ) {
        DaprCategoryBarComponent(
                modifier = Modifier.fillMaxWidth(),
                categorySegments = listOf(
                        ProgressSegment(1, "0-8 Rides", "Bad"),
                        ProgressSegment(9, "9-16 Rides", "Average"),
                        ProgressSegment(17, "17-20 Rides", "Best")
                ),
                completedRidesCount = 20,
                maxDroppedRides = 20,
                barHeight = 16.dp,
                backgroundColor = Color.White,
                color = Color(0xFFAA2D1F),
                highlightedSegmentDefinerTextColor = Color(0xff000000),
                nonHighlightedSegmentDefinerTextColor = Color(0xFF7D899B)
        )
    }
}

data class ProgressSegment(
        val ridesThreshold: Int,
        val segmentRangeLabel: String,
        val segmentCategoryLabel: String
)