package me.kartdroid.androidkitchen2

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationAttributes
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.kartdroid.androidkitchen2.dragablecompose.DragableComposeActivity
import me.kartdroid.androidkitchen2.drawover.FloatingWindowService
import me.kartdroid.androidkitchen2.drawover.FloatingWindowViewModel
import me.kartdroid.androidkitchen2.html.HtmlActivity
import me.kartdroid.androidkitchen2.orders.MMOOrderActivity
import me.kartdroid.androidkitchen2.playground.AssetImage
import me.kartdroid.androidkitchen2.subscription.activity.SubscriptionActivity
import me.kartdroid.androidkitchen2.ui.theme.AndroidKitchen2Theme
import me.kartdroid.androidkitchen2.utils.logDebug
import java.util.concurrent.TimeUnit


const val NOTIFICATION_CHANNEL_ID_HIGH_PRIORITY = "com.kartdroid.kitchen.priority";

class MainActivity : ComponentActivity() {

    init {

        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(
                source: LifecycleOwner,
                event: Lifecycle.Event
            ) {
                val nonConfigInstance = nonConfigurationCustomInstance
                logDebug("retrieved nonConfigInstance: $nonConfigInstance")
                lifecycle.removeObserver(this)
            }
        })
    }
    private val nonConfigurationCustomInstance: NonConfigurationCustomInstance by lazy {
        val lastInstance = lastCustomNonConfigurationInstance
        if (lastInstance == null) {
            logDebug("Creating NonConfigurationCustomInstance")
            NonConfigurationCustomInstance()
        } else {
            logDebug("Reusing NonConfigurationCustomInstance")
            lastInstance as NonConfigurationCustomInstance
        }
    }

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

    override fun onStop() {
        super.onStop()
        logDebug("Main ::onStop")
    }


    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): Any {
        logDebug("Main ::onRetainCustomNonConfigurationInstance: $nonConfigurationCustomInstance")
        return nonConfigurationCustomInstance
    }

    override fun onDestroy() {
        super.onDestroy()
        logDebug("Main ::onDestroy")
    }

    @Composable
    fun Content(
        viewModel: FloatingWindowViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    ) {
        val randomCustomObject: RandomCustomObject = remember {
            var cachedRandomObject =
                nonConfigurationCustomInstance.get("liveOrderSupportVM") as? RandomCustomObject
            if (cachedRandomObject == null) {
                cachedRandomObject = RandomCustomObject("Hello")
                nonConfigurationCustomInstance.put("liveOrderSupportVM", cachedRandomObject)
                logDebug("Creating RandomCustomObject: $cachedRandomObject")
            } else {
                logDebug("Reusing RandomCustomObject: $cachedRandomObject")
            }
            cachedRandomObject
        }
        val viewModelStoreOwner = LocalViewModelStoreOwner.current
        logDebug("viewModelStoreOwner: $viewModelStoreOwner")
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
                        Text(text = randomCustomObject.message)
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

                        Button(onClick = ::scheduleNotification) {
                            Text(text = "Schedule Notificaiton")
                        }

                        Row(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = androidx.compose.ui.graphics.Color.Black,
                                    shape = object : Shape {
                                        override fun createOutline(
                                            size: Size,
                                            layoutDirection: LayoutDirection,
                                            density: Density
                                        ): Outline {
                                            val rect = size.toRect()
                                            return Outline.Rounded(
                                                RoundRect(
                                                    left = rect.left,
                                                    top = rect.top,
                                                    right = rect.right,
                                                    bottom = rect.bottom,
                                                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(
                                                        50f
                                                    )
                                                )
                                            )
                                        }

                                    }
                                )
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Button(onClick = ::playRingtone) {
                                Text(text = "Play Ringtone")
                            }
                            Button(onClick = ::stopRingtone) {
                                Text(text = "Stop Ringtone")
                            }
                        }

                        AssetImage(
                            modifier = Modifier
                                .border(width = 1.dp, color = Color.Black)
                                .width(240.dp)
                                .height(360.dp),
                            assetBasePath = "file:///android_asset/",
                            assetName = "asset://sample_bill.svg"
                        )
                    }
                }
            }
        }
        val activity = LocalContext.current as? Activity
        DisposableEffect(Unit) {
            onDispose {
                if (activity?.isFinishing == true) {
                    logDebug("Removing liveOrderSupportVM")
                    nonConfigurationCustomInstance.remove("liveOrderSupportVM")
                }
            }
        }
    }


    private fun scheduleNotification(): Unit {
        logDebug("Scheduling Notification")
        lifecycleScope.launch {
            logDebug("Notification Schedule Entry")
            delay(3000)
            logDebug("Notification Schedule Trigerred")
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            checkAndCreateNotificationChannel(notificationManager)
            val ringtone = requireNotNull(
                RingtoneManager.getRingtone(
                    applicationContext,
                    Uri.parse(
                        "android.resource://" + packageName + "/" + R.raw.auto_accept_order_sound_loop
                    )
                )
            )

            val notification =
                NotificationCompat.Builder(
                    applicationContext,
                    NOTIFICATION_CHANNEL_ID_HIGH_PRIORITY
                )
                    .apply {
                        setSmallIcon(R.drawable.ic_auto_icon)
                        setLargeIcon(
                            BitmapFactory.decodeResource(
                                resources,
                                R.mipmap.ic_launcher_round
                            )
                        )
                        setContentTitle("Attention !! Attention !!")
                        setProgress(1, 0, true)
                        setColor(0xfff8da77.toInt())
                        setAutoCancel(true)
                        setPriority(NotificationCompat.PRIORITY_MAX)
                        setContentText("This is a High Priority Notifiaction Test.\n Please check this Notification")
                        setTicker("Ticker Text")
                        setStyle(
                            NotificationCompat.BigTextStyle()
                            //.setBigContentTitle("Attention2!! Attention2 !!")
                            /*.setSummaryText("This is definitely a High Priority Notifiaction Test.\n" +
                                " Please check this Notification")*/
                        )
                        setSound(Uri.parse("android.resource://" + packageName + "/" + R.raw.auto_accept_order_sound_loop))
                    }
                    .build()
            notificationManager.notify(0xADEF80, notification)
            logDebug("Notification Notified")
        }
    }

    private var ringtone: Ringtone? = null
    private fun playRingtone() {
        playVibration(TimeUnit.SECONDS.toMillis(5))
        if (ringtone == null) {
            val ringtone = RingtoneManager.getRingtone(
                applicationContext,
                Uri.parse(
                    "android.resource://" + packageName + "/" + R.raw.taxina
                )
            )
            ringtone?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    it.isLooping = true
                    it.volume = 1f
                }
            }
            this.ringtone = ringtone
        }
        if (ringtone?.isPlaying?.not() == true) {
            ringtone?.play()
        }
    }

    private fun playVibration(durationInMillis: Long) {
        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        if (vibrator != null && vibrator.hasVibrator() && durationInMillis > 0) {
            val timings: LongArray = longArrayOf(50, 50, 50, 50, 50, 100, 350, 250)
            //val amplitudes: IntArray = intArrayOf(77, 79, 84, 99, 143, 255, 0, 255)
            val amplitudes: IntArray = intArrayOf(77, 2, 84, 99, 143, 255, 0, 255)
            //val amplitudes: IntArray = intArrayOf(255, 255, 255, 255, 255, 255, 255, 255)
            val repeatIndex = -1 // Do not repeat.
            //val repeatIndex = timings.lastIndex - 1 // Do not repeat.
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                vibrator.vibrate(durationInMillis)
                return
            }
            val effect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                VibrationEffect.createWaveform(timings, amplitudes, repeatIndex)
            } else {
                VibrationEffect.createOneShot(durationInMillis, 2)
            }
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    vibrator.vibrate(
                        effect,
                        VibrationAttributes.Builder()
                            //.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            //.setUsage(VibrationAttributes.USAGE_RINGTONE)
                            .build()
                    )
                }else {
                    vibrator.vibrate(
                        effect,
                        AudioAttributes.Builder()
                            //.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            //.setUsage(VibrationAttributes.USAGE_RINGTONE)
                            .build()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun stopRingtone() {
        stopVibration()
        ringtone?.let {
            if (it.isPlaying) {
                it.stop()
            }
        }
    }

    private fun stopVibration() {
        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.cancel()
        }
    }

    private fun checkAndCreateNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val highPriorityChannel =
                NotificationChannel(
                    NOTIFICATION_CHANNEL_ID_HIGH_PRIORITY,
                    "High Importance Notification",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    setSound(
                        Uri.parse("android.resource://" + packageName + "/" + R.raw.auto_accept_order_sound_loop),
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                            .build()
                    )
                }
            if (notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID_HIGH_PRIORITY) == null) {
                notificationManager.createNotificationChannel(highPriorityChannel)
            } else {
                logDebug("Notification Channel already created")
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
            Uri.parse("package:$packageName")
        )
        startActivity(intent)
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Content()
    }
}

class RandomCustomObject(
    val message: String
)
class NonConfigurationCustomInstance {
    private val mMap = HashMap<String, Any>()
    fun get(key: String): Any? {
        return mMap[key]
    }

    fun put(key: String, value: Any) {
        mMap[key] = value
    }

    fun remove(key: String) {
        mMap.remove(key)
    }
}



