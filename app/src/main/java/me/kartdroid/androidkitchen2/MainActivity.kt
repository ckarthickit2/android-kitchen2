package me.kartdroid.androidkitchen2

import android.content.ContentProvider
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import me.kartdroid.androidkitchen2.drawover.FloatingWindow
import me.kartdroid.androidkitchen2.ui.theme.AndroidKitchen2Theme
import me.kartdroid.androidkitchen2.utils.logDebug

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logDebug("Main ::onCreate")
        setContent {
            Content()
        }
    }


    override fun onResume() {
        super.onResume()
        logDebug("Main ::onResume")
    }

    @Composable
    fun Content() {
        AndroidKitchen2Theme {
            Scaffold(
                topBar = {
                    TopAppBar(title = { Text(text = "Android Kitchen") })
                }
            ) {
                Surface(
                    modifier = Modifier.padding(it)
                ) {
                    Column {
                        Button(
                            onClick = {
                                if (canDrawOverOtherApps()) {
                                    FloatingWindow(this@MainActivity).show()
                                }
                            }
                        ) {
                            Text("Display Over Other Apps - UI")
                        }
                    }
                }
            }
        }
    }

    private fun canDrawOverOtherApps(): Boolean {
        val canDrawOverApps = Settings.canDrawOverlays(applicationContext)
        return canDrawOverApps.also {
            if (!it) {
                openDrawOverAppsMobileSetting()
            }
        }
    }

    private fun openDrawOverAppsMobileSetting() {
        val packageName = applicationContext.packageName
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:$packageName"))
        startActivity(intent)
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Content()
    }
}



