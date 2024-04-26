package com.rapido.rapidodesignsystem.components.toolbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.tokens.base.RdsColors

@Composable
fun TopAppBar(
    modifier: Modifier,
    toolbarTextType: RdsTextType = RdsTextType.HeadlineSmall,
    @DrawableRes actionIcon: Int,
    toolbarText: String,
    isShowDivider: Boolean = true,
    actionOnClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.background
    ) {
        Column {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            actionOnClick()
                        },
                    painter = painterResource(id = actionIcon),
                    contentDescription = null
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 10.dp)
                ) {
                    RdsTextView(
                        type = toolbarTextType,
                        text = toolbarText,
                        modifier = Modifier,
                        maxLines = 1,
                    )
                }
            }
            if (isShowDivider)Divider(thickness = 1.dp, color = RdsColors.gray400)
        }
    }
}
