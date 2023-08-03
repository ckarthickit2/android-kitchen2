package me.kartdroid.androidkitchen2.drawover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.kartdroid.androidkitchen2.ui.theme.AndroidKitchen2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeContent(
    onClose: () -> Unit
) {
    AndroidKitchen2Theme{
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                IconButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = onClose
                ) {
                    Icon(imageVector = Icons.Filled.Close, tint = MaterialTheme.colorScheme.onSurface, contentDescription = "Close")
                }
                Surface(
                    onClick = {},
                    modifier = Modifier,
                    enabled = false,
                    shape = CardDefaults.shape,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = null,
                    tonalElevation = 0.0.dp,
                    shadowElevation = 0.0.dp,
                ) {
                    Column {
                        Text(text = "Floating Window", style = MaterialTheme.typography.displaySmall)
                        Text(text = "This is a sample description", style = MaterialTheme.typography.bodyMedium)
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

@Preview
@Composable
fun ComposeContentPreview() {
    ComposeContent(onClose = {})
}