package me.kartdroid.androidkitchen2.ui.error

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import me.kartdroid.androidkitchen2.R

sealed class ErrorType {
    object NETWORK : ErrorType()
    object SERVER : ErrorType()
    data class OTHER(val errorName: String) : ErrorType()
}

fun ErrorType.name(): String = when (this) {
    is ErrorType.NETWORK -> "Network"
    is ErrorType.SERVER -> "Server"
    is ErrorType.OTHER -> "OTHER($errorName)"
}

@Composable
fun ErrorScreen(
        modifier: Modifier = Modifier,
        type: ErrorType,
        imageComposable: @Composable () -> Unit = {},
        @StringRes title: Int? = null,
        @StringRes description: Int? = null,
        @StringRes retryCTALabel: Int? = null,
        showRetryCta: Boolean = true,
        onRetry: () -> Unit
) {
    GenericErrorScreen(
        modifier = Modifier.fillMaxSize().then(modifier),
        imageComposable = { ErrorLogo(type, imageComposable) },
        title = getErrorTitle(type, title),
        description = getErrorBody(type, description),
        retryCTALabel = getRetryLabel(type, retryCTALabel),
        showRetryCta = showRetryCta,
        onRetry = onRetry
    )
}

@Composable
fun ErrorLogo(type: ErrorType, logo: @Composable () -> Unit) {
    return when (type) {
        is ErrorType.NETWORK -> {
            Image(
                painter = painterResource(id = R.drawable.ic_no_internet_illustration),
                contentDescription = null
            )
        }
        is ErrorType.SERVER -> {
            Image(
                painter = painterResource(id = R.drawable.ic_something_went_wrong_new),
                contentDescription = null
            )
        }
        else -> {
            logo()
        }
    }
}

@StringRes
fun getErrorTitle(type: ErrorType, @StringRes title: Int?): Int {
    return when (type) {
        is ErrorType.NETWORK -> {
            R.string.no_internet_exclamation
        }
        is ErrorType.SERVER -> {
            R.string.something_went_wrong
        }
        else -> {
            title ?: R.string.something_went_wrong
        }
    }
}

@StringRes
fun getErrorBody(type: ErrorType, @StringRes description: Int?): Int {
    return description ?: when (type) {
        is ErrorType.NETWORK -> {
            R.string.check_your_connection
        }
        is ErrorType.SERVER -> {
            R.string.an_error_occurred_try_again_later
        }
        else -> {
            R.string.an_error_occurred_try_again_later
        }
    }
}

@StringRes
fun getRetryLabel(type: ErrorType, @StringRes retryLabel: Int?): Int {
    return when (type) {
        is ErrorType.NETWORK -> {
            R.string.try_again
        }
        is ErrorType.SERVER -> {
            R.string.try_again
        }
        else -> {
            retryLabel ?: R.string.try_again
        }
    }
}
