package me.kartdroid.androidkitchen2.ui.error

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.components.button.RdsPrimaryButton
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.tokens.base.RdsColors

@Composable
fun GenericErrorScreen(
    modifier: Modifier = Modifier,
    imageComposable: @Composable () -> Unit,
    @StringRes title: Int,
    @StringRes description: Int,
    @StringRes retryCTALabel: Int,
    showRetryCta: Boolean = true,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        imageComposable()
        Spacer(modifier = Modifier.height(40.dp))
        RdsTextView(
            text = stringResource(id = title),
            type = RdsTextType.HeadlineMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        RdsTextView(
            modifier = Modifier.padding(horizontal = 50.dp),
            text = stringResource(id = description),
            type = RdsTextType.BodyMedium,
            textAlign = TextAlign.Center,
            color = RdsColors.neutrals8
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (showRetryCta) {
            RdsPrimaryButton(
                modifier = Modifier,
                text = stringResource(id = retryCTALabel),
                onClick = onRetry
            )
        }
    }
}
