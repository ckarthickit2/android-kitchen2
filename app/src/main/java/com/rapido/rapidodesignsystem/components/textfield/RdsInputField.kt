package com.rapido.rider.features.acquisition.presentation.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.components.text.TextSize
import com.rapido.rapidodesignsystem.tokens.base.NotoSans
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.utils.addTestTag

const val INPUT_FIELD_TEST_TAG = "input_field"
const val INPUT_FIELD_HINT_TEST_TAG = "input_field_hint"
const val INPUT_FIELD_SUPPORT_TEST_TAG = "input_field_support_hint"
const val INPUT_FIELD_ERROR_TEST_TAG = "input_field_error_hint"

@Composable
fun RdsInputField(
    modifier: Modifier,
    label: String = "",
    labelTextStyle: FontWeight = FontWeight.Medium,
    labelTextSize: TextUnit = 19.sp,
    labelTextColor: Color = RdsColors.dark2,
    labelTextFontFamily: FontFamily = FontFamily.Default,
    inputText: String = "",
    hint: String = "",
    supportingText: String = "",
    supportingTextFontWeight: FontWeight = FontWeight.Normal,
    supportingTextSize: TextUnit = 12.sp,
    errorText: String = "",
    isReadOnly: Boolean = false,
    maxLength: Int = Int.MAX_VALUE,
    isWhiteSpaceAllowed: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Done,
        keyboardType = KeyboardType.Text
    ),
    isValidInput: (input: String) -> Boolean,
    onTextChange: (input: String) -> Unit,
) {
    var isValid by remember { mutableStateOf(true) }

    Column {
        if (label.isNotEmpty()) {
            InputLabelText(
                label = label,
                labelTextStyle,
                labelTextSize,
                labelTextColor,
                labelTextFontFamily
            )
        }
        Surface(
            color = RdsColors.white,
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(
                1.2.dp,
                if (isValid || inputText.isBlank()) RdsColors.gray400 else RdsColors.redBase
            ),
            modifier = modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.addTestTag(INPUT_FIELD_TEST_TAG),
                readOnly = isReadOnly,
                maxLines = 1,
                keyboardOptions = keyboardOptions,
                textStyle = TextStyle(color = RdsColors.black, fontSize = 18.sp),
                value = inputText,
                onValueChange = {
                    if (it.length <= maxLength) {
                        val input = if (!isWhiteSpaceAllowed) it.filter { str ->
                            !str.isWhitespace()
                        } else it
                        isValid = isValidInput(input)
                        onTextChange(input)
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = RdsColors.black,
                    disabledTextColor = RdsColors.transparent,
                    backgroundColor = RdsColors.white,
                    focusedIndicatorColor = RdsColors.transparent,
                    unfocusedIndicatorColor = RdsColors.transparent,
                    disabledIndicatorColor = RdsColors.transparent
                ),
                placeholder = {
                    if (inputText.isBlank()) {
                        InputHintText(hint = hint)
                    }
                }
            )
        }
        if (supportingText.isNotEmpty() && (inputText.isBlank() || isValid) && isReadOnly.not()) {
            InputSupportingText(
                supportingText = supportingText,
                supportingTextFontWeight,
                supportingTextSize
            )
        }
        if (errorText.isNotEmpty() && inputText.isNotBlank() && isValid.not()) {
            InputErrorField(errorText)
        }
    }
}

@Composable
fun InputLabelText(
    label: String,
    labelTextStyle: FontWeight = FontWeight.Medium,
    labelTextSize: TextUnit = 19.sp,
    labelTextColor: Color = RdsColors.dark2,
    labelTextFontFamily: FontFamily = FontFamily.Default,
) {
    RdsTextView(
        modifier = Modifier.padding(0.dp, 12.dp),
        type = RdsTextType.Custom(
            TextStyle(
                color = labelTextColor,
                fontWeight = labelTextStyle,
                fontSize = labelTextSize,
                fontFamily = labelTextFontFamily
            ),
            TextSize.Medium
        ),
        text = label
    )
}

@Composable
private fun InputHintText(hint: String) {
    RdsTextView(
        modifier = Modifier
            .addTestTag(INPUT_FIELD_HINT_TEST_TAG),
        type = RdsTextType.Custom(
            TextStyle(
                color = RdsColors.gray500,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            TextSize.Medium
        ),
        text = hint
    )
}

@Composable
private fun InputSupportingText(
    supportingText: String,
    fontWeight: FontWeight,
    fontSize: TextUnit
) {
    RdsTextView(
        modifier = Modifier
            .addTestTag(INPUT_FIELD_SUPPORT_TEST_TAG)
            .padding(0.dp, 12.dp, 0.dp, 0.dp),
        type = RdsTextType.Custom(
            TextStyle(
                color = RdsColors.dark3,
                fontWeight = fontWeight,
                fontSize = fontSize
            ),
            TextSize.Medium
        ),
        text = supportingText
    )
}

@Composable
private fun InputErrorField(errorText: String) {
    RdsTextView(
        modifier = Modifier
            .addTestTag(INPUT_FIELD_ERROR_TEST_TAG)
            .padding(0.dp, 12.dp, 0.dp, 0.dp),
        type = RdsTextType.Custom(
            TextStyle(
                color = RdsColors.redBase,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            ),
            TextSize.Medium
        ),
        text = errorText
    )
}

@Preview
@Composable
private fun PreviewInputFieldWithMediumLabel() {
    Box(modifier = Modifier.background(color = RdsColors.white)) {
        RdsInputField(
            modifier = Modifier.background(color = RdsColors.white),
            inputText = "CHEPP2213D",
            label = "PAN Number",
            hint = "Enter Pan number",
            supportingText = "Ex: CHEPP2213D",
            errorText = "Enter valid PAN number",
            isValidInput = {
                true
            },
            onTextChange = {
            }
        )
    }
}

@Preview
@Composable
private fun PreviewInputFieldWithBoldLabel() {
    Box(modifier = Modifier.background(color = RdsColors.white)) {
        RdsInputField(
            modifier = Modifier.background(color = RdsColors.white),
            inputText = "CHEPP2213D",
            label = "PAN Number",
            labelTextStyle = FontWeight.Bold,
            labelTextSize = 22.sp,
            labelTextColor = RdsColors.black,
            labelTextFontFamily = NotoSans,
            hint = "Enter Pan number",
            supportingText = "Example: KA 12 EZ 4231",
            errorText = "Enter valid PAN number",
            isValidInput = {
                true
            },
            onTextChange = {
            }
        )
    }
}
