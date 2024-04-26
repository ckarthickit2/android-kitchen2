package com.rapido.rapidodesignsystem.components.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.theme.RapidoTheme

/**
 * Created by Abhishek Raj on 27/12/23.
 */

@Composable
fun LinearProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
    backgroundColor: Color = Color.Gray,
    progressColor: Color = Color.Black,
    progressCornerRadius: Dp = 0.dp,
    progressBarShape: LinearProgressBarShape = LinearProgressBarShape.FLAT_ENDS
) {
    val progressClipShape = when (progressBarShape) {
        LinearProgressBarShape.ROUNDED_LEFT_END -> RoundedCornerShape(progressCornerRadius, 0.dp, 0.dp, progressCornerRadius)
        LinearProgressBarShape.ROUNDED_PROGRESS_ONLY -> if (progress < 1) RoundedCornerShape(0.dp, progressCornerRadius, progressCornerRadius, 0.dp) else RoundedCornerShape(0.dp)
        LinearProgressBarShape.ROUNDED_RIGHT_END -> RoundedCornerShape(0.dp, progressCornerRadius, progressCornerRadius, 0.dp)
        LinearProgressBarShape.ROUNDED_BOTH_ENDS -> RoundedCornerShape(progressCornerRadius)
        LinearProgressBarShape.FLAT_ENDS -> RoundedCornerShape(0.dp)
    }
    val backgroundClipShape = when (progressBarShape) {
        LinearProgressBarShape.ROUNDED_LEFT_END -> RoundedCornerShape(progressCornerRadius, 0.dp, 0.dp, progressCornerRadius)
        LinearProgressBarShape.ROUNDED_PROGRESS_ONLY -> RoundedCornerShape(0.dp)
        LinearProgressBarShape.ROUNDED_RIGHT_END -> RoundedCornerShape(0.dp, progressCornerRadius, progressCornerRadius, 0.dp)
        LinearProgressBarShape.ROUNDED_BOTH_ENDS -> RoundedCornerShape(progressCornerRadius)
        LinearProgressBarShape.FLAT_ENDS -> RoundedCornerShape(0.dp)
    }
    Box(
        modifier = modifier
            .clip(backgroundClipShape)
            .background(backgroundColor)
            .height(8.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clip(progressClipShape)
                .background(progressColor)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
    }
}

enum class LinearProgressBarShape {
    ROUNDED_LEFT_END, ROUNDED_PROGRESS_ONLY, ROUNDED_RIGHT_END, ROUNDED_BOTH_ENDS, FLAT_ENDS
}

@Preview(showBackground = true)
@Composable
fun LinearProgressIndicatorPreview() {
    RapidoTheme {
        Column(modifier = Modifier.padding(8.dp)) {
            RdsTextView(text = "ROUNDED_LEFT_END", style = TextStyle.Default)
            LinearProgressBar(progress = 0.5f, progressCornerRadius = 8.dp, progressBarShape = LinearProgressBarShape.ROUNDED_LEFT_END)
            Spacer(modifier = Modifier.height(8.dp))
            RdsTextView(text = "ROUNDED_RIGHT_END", style = TextStyle.Default)
            LinearProgressBar(progress = 0.5f, progressCornerRadius = 8.dp, progressBarShape = LinearProgressBarShape.ROUNDED_RIGHT_END)
            Spacer(modifier = Modifier.height(8.dp))
            RdsTextView(text = "ROUNDED_PROGRESS_ONLY, 0.7 progress", style = TextStyle.Default)
            LinearProgressBar(progress = 0.7f, progressCornerRadius = 8.dp, progressBarShape = LinearProgressBarShape.ROUNDED_PROGRESS_ONLY)
            Spacer(modifier = Modifier.height(8.dp))
            RdsTextView(text = "ROUNDED_PROGRESS_ONLY, 0 progress", style = TextStyle.Default)
            LinearProgressBar(progress = 0f, progressCornerRadius = 8.dp, progressBarShape = LinearProgressBarShape.ROUNDED_PROGRESS_ONLY)
            Spacer(modifier = Modifier.height(8.dp))
            RdsTextView(text = "ROUNDED_PROGRESS_ONLY, 1 progress", style = TextStyle.Default)
            LinearProgressBar(progress = 1f, progressCornerRadius = 8.dp, progressBarShape = LinearProgressBarShape.ROUNDED_PROGRESS_ONLY)
            Spacer(modifier = Modifier.height(8.dp))
            RdsTextView(text = "ROUNDED_BOTH_ENDS", style = TextStyle.Default)
            LinearProgressBar(progress = 1f, progressCornerRadius = 8.dp, progressBarShape = LinearProgressBarShape.ROUNDED_BOTH_ENDS)
            Spacer(modifier = Modifier.height(8.dp))
            RdsTextView(text = "FLAT_ENDS", style = TextStyle.Default)
            LinearProgressBar(progress = 1f, progressBarShape = LinearProgressBarShape.FLAT_ENDS)
        }
    }
}
