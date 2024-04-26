package com.rapido.rapidodesignsystem.utils.extension

import androidx.compose.ui.graphics.Color

private fun isHexColorCode(colorCode: String): Boolean {
    val regex = Regex("^#([A-Fa-f0-9]{6,8}|[A-Fa-f0-9]{3})$")
    return regex.matches(colorCode)
}

fun String.toColorSafely(): Color {
    return if (this.isNotBlank() && isHexColorCode(this)) {
        Color(android.graphics.Color.parseColor(this))
    } else {
        Color.Unspecified
    }
}

fun List<String>.toColorSafely(): List<Color> {
    return this.map {
        it.toColorSafely()
    }
}

fun Color.toHexString(): String {
    return try {
        String.format("#%02X%02X%02X%02X", (this.alpha * 255).toInt(), (this.red * 255).toInt(), (this.green * 255).toInt(), (this.blue * 255).toInt())
    } catch (ex: Exception) {
        "#000000"
    }
}
