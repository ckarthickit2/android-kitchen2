package com.rapido.rapidodesignsystem.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rapido.presentation.model.IFaqContext
import com.rapido.rapidodesignsystem.components.icon.RdsIconConfig
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import me.kartdroid.androidkitchen2.R

@Composable
fun HelpToolbarButton(
    modifier: Modifier = Modifier,
    iFaqContext: IFaqContext,
    onHelpClicked: (iFaqContext: IFaqContext) -> Unit,
    text: String = ""
) {
    RdsSecondaryOutlinedButton(
        modifier = modifier,
        text = text.ifBlank { stringResource(id = R.string.help) },
        leadingIconConfig = RdsIconConfig(
            painter = painterResource(R.drawable.ic_help_support_menu_icon),
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        ),
        type = RdsTextType.TitleMedium,
        contentPadding = PaddingValues(
            top = 5.dp,
            end = 8.dp,
            bottom = 5.dp
        ),
        onClick = { onHelpClicked(iFaqContext) }
    )
}

@Preview
@Composable
private fun HelpToolBarButton() {
   Surface {
       HelpToolbarButton(
           modifier = Modifier.padding(4.dp),
           iFaqContext = IFaqContext.FaqContext("some_faq_id", "Help"),
           onHelpClicked = {},
           text = "Help"
       )
   }
}
