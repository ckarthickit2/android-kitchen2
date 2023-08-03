package me.kartdroid.androidkitchen2.drawover

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner


class MyService : LifecycleService(), SavedStateRegistryOwner {

    private var floatingWindow: FloatingWindow? = null
    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showFloatingWindow()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        startForegroundService()
    }

    private fun startForegroundService() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "MyChannelId",
                "My Foreground Service",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }


// But here, you are sending notifications to "MyChannelId"

// But here, you are sending notifications to "MyChannelId"
        val notificationBuilder = NotificationCompat.Builder(
            this, "MyChannelId"
        )
        notificationBuilder.setSmallIcon(R.mipmap.sym_def_app_icon)
        notificationBuilder.setContentTitle("My Awesome App")
        notificationBuilder.setContentText("Doing some work...")

        startForeground(1337, notificationBuilder.build())
    }

    private fun showFloatingWindow() {
        if (Settings.canDrawOverlays(applicationContext)){
            floatingWindow = FloatingWindow(this)
            floatingWindow?.show()
        }
    }

    override val savedStateRegistry: SavedStateRegistry = SavedStateRegistryController.create(this).savedStateRegistry
}