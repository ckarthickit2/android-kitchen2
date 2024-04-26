package com.rapido.rapidodesignsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce

/**
 * Check this link for more details
 * https://al-e-shevelev.medium.com/how-to-prevent-multiple-clicks-in-android-jetpack-compose-8e62224c9c5e
 */

interface MultipleEventsCutter {
    fun processEvent(event: () -> Unit)
}

@OptIn(FlowPreview::class)
@Composable
fun <T> multipleEventsCutter(
    content: @Composable (MultipleEventsCutter) -> T
): T {
    val debounceState = remember {
        MutableSharedFlow<() -> Unit>(
            replay = 0,
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    }

    val result = content(
        object : MultipleEventsCutter {
            override fun processEvent(event: () -> Unit) {
                debounceState.tryEmit(event)
            }
        }
    )

    LaunchedEffect(true) {
        debounceState
            .debounce(300L)
            .collect { onClick ->
                onClick.invoke()
            }
    }

    return result
}
