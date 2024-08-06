package me.kartdroid.androidkitchen2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.kartdroid.androidkitchen2.dragablecompose.DragableComposeActivity
import me.kartdroid.androidkitchen2.drawover.FloatingWindowService
import me.kartdroid.androidkitchen2.drawover.FloatingWindowViewModel
import me.kartdroid.androidkitchen2.html.HtmlActivity
import me.kartdroid.androidkitchen2.orders.MMOOrderActivity
import me.kartdroid.androidkitchen2.subscription.activity.SubscriptionActivity
import me.kartdroid.androidkitchen2.ui.theme.AndroidKitchen2Theme
import me.kartdroid.androidkitchen2.utils.logDebug

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
    fun Content(
        viewModel: FloatingWindowViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    ) {
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
                                    startServiceAndShowWindow()
                                    // FloatingWindow(this@MainActivity).show()
                                }
                            }
                        ) {
                            Text("Display Over Other Apps - UI")
                        }
                        Button(onClick = { finishActivityAndStartFloatingWindow() }) {
                            Text(text = "Finish Me & Start Service")
                        }
                        Button(onClick = { startOrderScreen() }) {
                            Text(text = "Order Screen")
                        }
                        Button(onClick = { startHtmlScreen() }) {
                            Text(text = "Html Text")
                        }
                        Button(onClick = { startDraggableComposeActivity() }) {
                            Text(text = "Draggable Compose")
                        }

                        Button(onClick = { startSubscriptionActivity() }) {
                            Text(text = "Subscription Screen")
                        }
                    }
                }
            }
        }
    }

    private fun startServiceAndShowWindow() {
        val service = Intent(this, FloatingWindowService::class.java).apply {
            action = "SHOW_VIEW"
        }
        startService(service)
    }

    private fun finishActivityAndStartFloatingWindow() {
        finish()
        val service = Intent(this, FloatingWindowService::class.java).apply {
            action = "SHOW_VIEW"
        }
        startService(service)
    }

    private fun startOrderScreen() {
        startActivity(Intent(this, MMOOrderActivity::class.java))
    }

    private fun startHtmlScreen() {
        startActivity(Intent(this, HtmlActivity::class.java))
    }

    private fun startDraggableComposeActivity() {
        startActivity(Intent(this, DragableComposeActivity::class.java))
    }

    private fun startSubscriptionActivity() {
        startActivity(Intent(this, SubscriptionActivity::class.java))
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



