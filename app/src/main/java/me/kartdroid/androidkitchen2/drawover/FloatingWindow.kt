package me.kartdroid.androidkitchen2.drawover

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import me.kartdroid.androidkitchen2.utils.logDebug

class FloatingWindow constructor(
    private val context: Context,
) {

    private val windowManager by lazy {
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }
    private val root by lazy {
        ComposeView(context).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed((context as ComponentActivity).lifecycle))
            setContent {
                ComposeContent(
                    onClose = {
                        hide()
                    }
                )
            }
        }
    }
    private var isShowing = false

    fun show() {
        addRootView()
    }

    private fun hide() {
        removeRootView()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun addRootView() {
        val params: WindowManager.LayoutParams =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
                )
            } else {
                WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
                )
            }

        params.gravity =
            Gravity.TOP

        params.x = 0
        params.y = 0


        root.setViewTreeLifecycleOwner((context as ComponentActivity))
        root.setViewTreeSavedStateRegistryOwner((context as ComponentActivity))
        root.setOnTouchListener(object : View.OnTouchListener {
            var initialX = 0
            var initialY = 0
            var initialTouchX = 0f
            var initialTouchY = 0f

            override fun onTouch(p0: View?, motionEvent: MotionEvent?): Boolean {
                logDebug("FloatingWindow:: onTouch")
                when (motionEvent?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        initialX = params.x
                        initialY = params.y
                        initialTouchX = motionEvent.rawX
                        initialTouchY = motionEvent.rawY

                        return true
                    }

                    MotionEvent.ACTION_UP -> {

                        return true
                    }

                    MotionEvent.ACTION_MOVE -> {
                        // Calculate the X and Y coordinates of the view.
                        params.x = initialX + (motionEvent.rawX - initialTouchX).toInt()
                        params.y = initialY + (motionEvent.rawY - initialTouchY).toInt()

                        // Update the layout with new X & Y coordinate

                        // Update the layout with new X & Y coordinate
                        windowManager.updateViewLayout(root, params)
                        return true
                    }
                }
                return false
            }
        })
        windowManager.addView(root, params)
        isShowing = true
    }

    private fun removeRootView() {
        if (isShowing) {
            try {
                windowManager.removeView(root)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

}


