package me.kartdroid.androidkitchen2.drawover

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.annotation.MainThread
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.AndroidUiDispatcher
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.platform.compositionContext
import androidx.compose.ui.platform.createLifecycleAwareWindowRecomposer
import androidx.core.view.doOnAttach
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.kartdroid.androidkitchen2.utils.logDebug

class FloatingWindow constructor(
    private val context: Context,
    private val coroutineScope: LifecycleCoroutineScope,
) {

    private val windowManager by lazy {
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }
    private val totalValue = 20f
    private val progressState = mutableStateOf(totalValue)
    private val root by lazy {
        ComposeView(context).apply {
            //setParentCompositionContext(null)
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed((context as LifecycleOwner)))
            setContent {
                ComposeContent(
                    onClose = {
                        hide()
                    },
                    current = progressState.value,
                    total = totalValue,
                )
            }
            ViewTreeLifecycleOwner.set(this, (context as LifecycleService))
            //root.setViewTreeLifecycleOwner((context as LifecycleService))
            setViewTreeSavedStateRegistryOwner((context as SavedStateRegistryOwner))
            //ViewTreeViewModelStoreOwner.set(this, (context as ViewModelStoreOwner))
            doOnAttach {
                logDebug("Attached to Window")
                //createComposition()
            }
        }
    }
    private var isShowing = false

    @MainThread
    fun show() {
        addRootView()
        coroutineScope.launch {
           while(true){
               val currentProgress = progressState.value
               logDebug("currentProgress = $currentProgress")
               delay(1000)
               if(currentProgress >0f) {
                   progressState.value = currentProgress-1
               }else {
                   hide()
               }
           }
        }
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
                    WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
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


