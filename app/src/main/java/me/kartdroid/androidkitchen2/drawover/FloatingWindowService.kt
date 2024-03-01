package me.kartdroid.androidkitchen2.drawover

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import me.kartdroid.androidkitchen2.utils.viewModels


class FloatingWindowService : LifecycleService(), SavedStateRegistryOwner, ViewModelStoreOwner {

    private val savedStateRegistryController by lazy {  SavedStateRegistryController.create(this) }
    private val viewModelStoreInternal by lazy { ViewModelStore() }
    override val savedStateRegistry: SavedStateRegistry by lazy {
        savedStateRegistryController.savedStateRegistry
    }
    private var floatingWindow: FloatingWindow? = null

    private val floatingWindowViewModel: FloatingWindowViewModel by viewModels {
        FloatingWindowViewModel.Factory()
    }

    override fun onCreate() {
        savedStateRegistryController.performAttach()
        savedStateRegistryController.performRestore(null)
        // Restore the Saved State first so that it is available to
        // OnContextAvailableListener instances
        super.onCreate()
        startForegroundService()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        if(intent?.action == "SHOW_VIEW") {
            showFloatingWindow()
        }else if(intent?.action == "HIDE_VIEW") {
            floatingWindow?.hide()
        }
        return START_NOT_STICKY
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
            floatingWindow = FloatingWindow(this, floatingWindowViewModel)
            floatingWindow?.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
    }

    override val viewModelStore: ViewModelStore
        get() = viewModelStoreInternal

}