package me.kartdroid.androidkitchen2.ui.theme

import androidx.compose.ui.graphics.Color
import me.kartdroid.androidkitchen2.ui.theme.RdsColors.contentPrimary
import me.kartdroid.androidkitchen2.ui.theme.RdsColors.dark1
import me.kartdroid.androidkitchen2.ui.theme.RdsColors.dark2
import me.kartdroid.androidkitchen2.ui.theme.RdsColors.dark3
import me.kartdroid.androidkitchen2.ui.theme.RdsColors.gray_400
import me.kartdroid.androidkitchen2.ui.theme.RdsColors.greenBase
import me.kartdroid.androidkitchen2.ui.theme.RdsColors.transparent
import me.kartdroid.androidkitchen2.ui.theme.RdsColors.white
import me.kartdroid.androidkitchen2.ui.theme.RdsColors.yellow400

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


object RdsColors {
    val yellow400 = Color(0xffffca20)
    val dark1 = Color(0xff0C0D0F)
    val dark2 = Color(0xff4B4B4B)
    val dark3 = Color(0xff727272)
    val greenBase = Color(0xff1D824F)
    val contentPrimary = Color(0xFF1A202A)
    val gray90 = Color(0xffECEEF2)
    val gray100 = Color(0xffeeeeee)
    val gray_400 = Color(0xFFAEB6C3)
    val gray900 = Color(0xff141414)
    val transparent = Color(0x00000000)
    val white = Color(0xffffffff)
    val neutrals3 = Color(0xffE0E0E0)
    val neutrals4 = Color(0xffBABFC8)
    val neutrals8 = Color(0xff454B57)
    val missedOrderOverlayColor = Color(0xBF0C0D0F)
    val lightRed = Color(0xFFFDEBED)
    val red = Color(0xFFEB5757)
    val redBase = Color(0xffD63643)
    val green6 = Color(0xff24A665)
    val blueDark3 = Color(0xff193b99)

    val pickupDropConnectorColor = Color(0xFFD9D9D9)
}

val KitchenDefaultOrderColors = KitchenThemeColors(
        primary = yellow400,
        primaryContainer = arrayListOf(Color.White, Color.White),
        onPrimaryContainer = dark1,
        onPrimaryContainerVariant = greenBase,
        secondaryContainer = contentPrimary,
        secondaryContainerOutline = gray_400,
        onSecondaryContainer = transparent,
        surface = white,
        secondarySurface = white,
        onSurface = dark1,
        onSurfaceVariant = dark3,
        onSurfaceDimVariant = dark2,
        primaryDividerStyle = DividerStyle.SOLID
)
