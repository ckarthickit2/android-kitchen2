package me.kartdroid.androidkitchen2.html

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.snapshotFlow
import me.kartdroid.androidkitchen2.utils.logDebug

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 09/11/23
 */
class HtmlActivity : ComponentActivity() {
    val someState = mutableIntStateOf(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logDebug("HmlActivity ::onCreate")
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContent {
            Content()
        }
        Handler(Looper.getMainLooper()).postDelayed({
            Log.i("KC_DEBUG", "posting 1")
            someState.intValue = 1;
        }, 2000)
        Handler(Looper.getMainLooper()).postDelayed({
            Log.i("KC_DEBUG", "posting 2")
            someState.intValue = 2;
        }, 4000)
        Handler(Looper.getMainLooper()).postDelayed({
            Log.i("KC_DEBUG", "posting 8")
            someState.intValue = 8;
        }, 6000)
    }

    @Composable
    fun Content() {
//        HtmlText()
        LaunchedEffect(Unit) {
            Log.i("KC_DEBUG", "re-executing")

            snapshotFlow { someState.intValue }.collect {
                if (someState.intValue < 10) {
                    Log.i("KC_DEBUG", "someState = ${someState.intValue}")
                }
            }
        }
        Text(text = "Re-Render Test")
    }
}