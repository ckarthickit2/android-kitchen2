package com.rapido.rapidodesignsystem.components.button

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.components.icon.RdsIconConfig

sealed class RdsButtonConfig(private val buttonState: RdsButtonState) {

    enum class ButtonSize(val buttonHeight: Dp) {
        SMALL(36.dp),
        MEDIUM(48.dp),
        LARGE(56.dp)
    }

    data class Primary(
        val state: RdsButtonState = RdsButtonState.ENABLED,
        val leadingIconConfig: RdsIconConfig? = null,
        val trailingIconConfig: RdsIconConfig? = null,
        val buttonSize: ButtonSize = ButtonSize.MEDIUM,
        val primaryVariant: Variant = Variant.DEFAULT,
        val text: String,
        val onClick: () -> Unit
    ) : RdsButtonConfig(buttonState = state) {

        enum class Variant {
            DEFAULT,
            POSITIVE,
            NEGATIVE,
        }
    }

    data class Secondary(
        val state: RdsButtonState = RdsButtonState.ENABLED,
        val leadingIconConfig: RdsIconConfig? = null,
        val trailingIconConfig: RdsIconConfig? = null,
        val buttonSize: ButtonSize = ButtonSize.MEDIUM,
        val secondaryVariant: Variant = Variant.DEFAULT,
        val text: String,
        val onClick: () -> Unit
    ) : RdsButtonConfig(buttonState = state) {

        enum class Variant {
            DEFAULT,
            POSITIVE,
            NEGATIVE,
        }
    }

    data class Tertiary(
        val state: RdsButtonState = RdsButtonState.ENABLED,
        val leadingIconConfig: RdsIconConfig? = null,
        val trailingIconConfig: RdsIconConfig? = null,
        val buttonSize: ButtonSize = ButtonSize.MEDIUM,
        val text: String,
        val onClick: () -> Unit
    ) : RdsButtonConfig(buttonState = state)

    data class Pill(
        val state: RdsButtonState = RdsButtonState.ENABLED,
        val leadingIconConfig: RdsIconConfig? = null,
        val trailingIconConfig: RdsIconConfig? = null,
        val pillVariant: Variant = Variant.DEFAULT,
        val text: String,
        val onClick: () -> Unit
    ) : RdsButtonConfig(buttonState = state) {

        enum class Variant {
            DEFAULT,
            POSITIVE,
        }
    }

    data class IconButton(
        val state: RdsButtonState = RdsButtonState.ENABLED,
        val iconConfig: RdsIconConfig,
        val variant: Variant = Variant.NO_BORDER,
        val onClick: () -> Unit
    ) : RdsButtonConfig(buttonState = state) {

        enum class Variant {
            BORDER, // add a border around the icon
            NO_BORDER // no border around the icon
        }
    }

    data class LinkButton(
        val state: RdsButtonState = RdsButtonState.ENABLED,
        val leadingIconConfig: RdsIconConfig? = null,
        val trailingIconConfig: RdsIconConfig? = null,
        val text: String,
        val onClick: () -> Unit
    ) : RdsButtonConfig(buttonState = state)
}

enum class RdsButtonState {
    ENABLED,
    DISABLED,
    LOADING,
}
