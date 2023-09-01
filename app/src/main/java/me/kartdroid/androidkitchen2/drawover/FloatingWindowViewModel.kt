package me.kartdroid.androidkitchen2.drawover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.kartdroid.androidkitchen2.utils.logDebug

data class UIState(
    val current: Float,
    val total: Float,
    val isFinished: Boolean = false,
)

class FloatingWindowViewModel : ViewModel() {

    private val totalValue = 20f
    val progressCountdownState = MutableStateFlow(UIState(totalValue, totalValue))
    private var currentProgressJob: Job? = null

    class Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FloatingWindowViewModel() as T
        }

    }

    fun showTimer() {
        currentProgressJob = viewModelScope.launch {
            while (true) {
                val currentProgress = progressCountdownState.value
                logDebug("currentProgress = $currentProgress")
                delay(1000)
                if (currentProgress.current > 0f) {
                    progressCountdownState.value = currentProgress.copy(current = currentProgress.current - 1)
                } else {
                    progressCountdownState.value = currentProgress.copy(isFinished = true)
                    break
                }
            }
        }
    }

    fun reset() {
        currentProgressJob?.cancel()
        progressCountdownState.value = UIState(totalValue, totalValue)
    }
}