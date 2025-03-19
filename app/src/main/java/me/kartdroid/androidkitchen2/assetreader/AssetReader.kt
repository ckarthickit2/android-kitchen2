package me.kartdroid.androidkitchen2.assetreader

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import java.io.BufferedReader
import java.io.InputStreamReader


@Composable
fun AssetReader() {
    Surface {
        val context = LocalContext.current
        var value by remember {
            mutableStateOf("This Text comes from state")
        }
        LaunchedEffect(Unit) {
            val inputStream = context.assets.open("files/something.txt")
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val fileText  = bufferedReader.use { it.readText() }
            value = fileText
        }
        Column {
            Text("path = ${context.assets}")
            Text(value)
        }
    }
}


@Preview
@Composable
private fun AssetReaderPreview() {
    RapidoTheme {
        AssetReader()
    }
}