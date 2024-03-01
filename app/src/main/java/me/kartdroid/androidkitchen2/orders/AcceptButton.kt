package me.kartdroid.androidkitchen2.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.ui.theme.AndroidKitchen2Theme
import me.kartdroid.androidkitchen2.ui.theme.RapidoTheme
import me.kartdroid.androidkitchen2.ui.theme.RdsColors

@Composable
fun AcceptButton(
    modifier: Modifier,
    timerText: String?,
    progress: Float?,
    maxProgress: Int,
    isAcceptButtonEnable: Boolean,
    isRunningOutOfTime: Boolean,
    orderId: String,
    onAcceptOrder: (String) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(
                        color = if (isAcceptButtonEnable) RapidoTheme.colors.primary else RdsColors.gray100,
                )
                .clickable(
                        enabled = (isAcceptButtonEnable),
                        onClick = {
                            onAcceptOrder(orderId)
                        }
                )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
        ) {
            Text(
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = RapidoTheme.colors.onSurface,
                    textAlign = TextAlign.Center
                ),
                text = stringResource(R.string.accept_cta),
                modifier = Modifier.weight(1f, false)
            )
            if (timerText != null && progress != null) {
                Box(
                    modifier = Modifier
                            .padding(start = 16.dp)
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(RdsColors.white),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize(),
                            color = if (isRunningOutOfTime) RdsColors.red else RdsColors.green6,
                            strokeWidth = 2.dp,
                            progress = if (maxProgress <= 0) 0.0f else progress / maxProgress,
                    )
                    Text(
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = RdsColors.dark1,
                            lineHeight = 14.sp
                        ),
                        text = timerText
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AcceptButtonPreview() {
    AndroidKitchen2Theme {
        Column(
            modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xfff5f5f5))
                    .padding(16.dp)
        ) {
            AcceptButton(
                modifier = Modifier, "3", 0.6f, 1,
                isAcceptButtonEnable = false,
                isRunningOutOfTime = false,
                orderId = "1",
                onAcceptOrder = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            AcceptButton(
                modifier = Modifier, "3", 0.6f, 1,
                isAcceptButtonEnable = false,
                isRunningOutOfTime = true,
                orderId = "2",
                onAcceptOrder = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            AcceptButton(
                modifier = Modifier, "3", 0.6f, 1,
                isAcceptButtonEnable = true,
                isRunningOutOfTime = false,
                orderId = "3",
                onAcceptOrder = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            AcceptButton(
                modifier = Modifier, "3", 0.6f, 1,
                isAcceptButtonEnable = true,
                isRunningOutOfTime = true,
                orderId = "4",
                onAcceptOrder = {}
            )
        }
    }
}
