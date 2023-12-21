package me.kartdroid.androidkitchen2.html

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import me.kartdroid.androidkitchen2.utils.logDebug

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 09/11/23
 */
class HtmlActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logDebug("HmlActivity ::onCreate")
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContent {
            Content()
        }
    }

    @Composable
    fun Content() {
        HtmlText()
    }
}