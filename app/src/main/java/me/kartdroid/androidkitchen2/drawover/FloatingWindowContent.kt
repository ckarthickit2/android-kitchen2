package me.kartdroid.androidkitchen2.drawover

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.kartdroid.androidkitchen2.ui.theme.AndroidKitchen2Theme

@Composable
fun ComposeContent(
    onClose: () -> Unit,
    current: Float,
    total: Float,
) {
    AndroidKitchen2Theme {
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .wrapContentWidth()
        ) {
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                IconButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = onClose
                ) {
                    Icon(imageVector = Icons.Filled.Close, tint = Color.Black, contentDescription = "Close")
                }
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface, CardDefaults.shape)
                        .border(BorderStroke(1.dp, Color.Black)),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Text(text = "Floating Window", style = MaterialTheme.typography.displaySmall)
                        Text(text = "This is a sample description", style = MaterialTheme.typography.bodyMedium)
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
                                    .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                            ) {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Cancel")
                                }
                            }
                           Row(
                               modifier = Modifier
                                   .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
                               ,
                               horizontalArrangement = Arrangement.SpaceBetween,
                               verticalAlignment = Alignment.CenterVertically
                           ) {
                               Button(onClick = { /*TODO*/ }) {
                                   Text(text = "Accept")
                               }
                               ProgressWithText(
                                   modifier = Modifier.padding(end = 8.dp),
                                   current = current,
                                   total = total,
                                   text = current.toInt().toString()
                               )
                           }
                        }
                    }
                }
                /*Card(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Floating Window", style = MaterialTheme.typography.displaySmall)
                    Text(text = "This is a sample description", style = MaterialTheme.typography.bodyMedium)
                }*/
            }
        }
    }
}

@Composable
fun ProgressWithText(
    current: Float,
    total: Float,
    text: String,
    modifier: Modifier= Modifier,
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Color.White),
    ) {
        val progress = current / total
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            progress = progress,
            strokeWidth = 4.dp,
            color = if (progress <= 0.3f) Color.Red else Color.Green
        )
        Text(modifier = Modifier.align(Alignment.Center), text = text)
    }
}

@Preview
@Composable
fun ComposeContentPreview() {
    ComposeContent(onClose = {}, 3f,10f)
}

@Preview
@Composable
fun ProgressWithTextPreview() {
    val progress = 3f
    ProgressWithText(current = progress, total = 10f, text = progress.toInt().toString())
}