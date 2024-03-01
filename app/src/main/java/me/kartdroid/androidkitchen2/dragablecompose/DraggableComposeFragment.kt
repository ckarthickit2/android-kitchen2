package me.kartdroid.androidkitchen2.dragablecompose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import me.kartdroid.androidkitchen2.ui.theme.RdsColors

/**
 * @author [Karthick Chinnathambi](https://github.com/karthick-rapido)
 * @since 24/11/23
 */
class DraggableComposeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ComposeContent()
            }
        }
    }

    @Composable
    fun ComposeContent() {
        BoxWithConstraints(
                modifier = Modifier
                        .padding(8.dp)
                        .background(RdsColors.transparent),
        ) {
            val density = LocalDensity.current
            val parentWidth = constraints.maxWidth
            val parentHeight = constraints.maxHeight
//            val configuration = LocalConfiguration.current
//            val parentWidth = configuration.screenWidthDp
//            val parentHeight = configuration.screenHeightDp
            var offsetX by remember { mutableStateOf(0f) }
            var offsetY by remember { mutableStateOf(0f) }
            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            Column(
                    modifier = Modifier
                            .offset(
                                    x = (offsetX / density.density).dp,
                                    y = (offsetY / density.density).dp
                            )
                            .background(color = Color(alpha = 225, red = 250, green = 251, blue = 254))
                            .pointerInput(key1 = null) {
                                val boxSize = this.size
                                detectDragGestures { change, dragAmount ->
                                    change.consume()
                                    offsetX = (offsetX + dragAmount.x).coerceIn(
                                            0f,
                                            parentWidth - boxSize.width.toFloat()
                                    )
                                    //offsetX += dragAmount.x
                                    offsetY = (offsetY + dragAmount.y).coerceIn(
                                            0f,
                                            parentHeight - boxSize.height.toFloat()
                                    )
                                    //offsetY += dragAmount.y
                                }
                            },
                    horizontalAlignment = Alignment.End
            ) {
                Row(
                        modifier = Modifier
                                .wrapContentHeight()
                                .wrapContentWidth()
                                .padding(6.dp)
                                .shadow(
                                        elevation = 12.dp,
                                        shape = RoundedCornerShape(size = 100.dp)
                                )
                                .background(
                                        color = RdsColors.white,
                                        shape = RoundedCornerShape(size = 100.dp)
                                )
                                .padding(start = 12.dp, top = 8.dp, end = 12.dp, bottom = 8.dp)
                                .clickable {
                                    Toast
                                            .makeText(context, "Clicked", Toast.LENGTH_LONG)
                                            .show()
                                },
                        verticalAlignment = Alignment.CenterVertically
                ) {
                    NextOrderWidget()
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                            modifier = Modifier
                                    .padding(1.dp)
                                    .width(32.dp)
                                    .height(32.dp),
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "View Order",
                            contentScale = ContentScale.None)
                }
            }
        }
    }

    @Composable
    fun NextOrderWidget() {
        Row(
                verticalAlignment = Alignment.CenterVertically
        ) {
            Image(imageVector = Icons.Filled.AccountBox, contentDescription = "Expand")
            Text(
                    text = "Next Order",
                    maxLines = 1,
                    style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = RdsColors.dark1,
                            letterSpacing = 0.2.sp,
                    ),
                    modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically)
            )
        }
    }

    @Preview
    @Composable
    fun DraggableComposePreview() {
        ComposeContent()
    }
}