package com.rapido.rapidodesignsystem.components.toolbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.components.button.RdsSecondaryOutlinedButton
import com.rapido.rapidodesignsystem.components.icon.RdsIcon
import com.rapido.rapidodesignsystem.components.icon.RdsIconConfig
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.components.toolbar.ToolBarTestTag.LEADING_ICON
import com.rapido.rapidodesignsystem.components.toolbar.ToolBarTestTag.SUBTITLE
import com.rapido.rapidodesignsystem.components.toolbar.ToolBarTestTag.TITLE
import com.rapido.rapidodesignsystem.components.toolbar.ToolBarTestTag.TOP_APPBAR_PARENT
import com.rapido.rapidodesignsystem.theme.RapidoTheme
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.R
import me.kartdroid.androidkitchen2.ui.test.addTestTag
import me.kartdroid.androidkitchen2.ui.test.mapTestTagAsResourceID

@Composable
fun TopAppBarWithSubtitle(
    modifier: Modifier = Modifier,
    @DrawableRes actionIcon: Int,
    toolbarText: String,
    subtitleText: String = "",
    trailingContent: @Composable (() -> Unit)? = null,
    actionOnClick: () -> Unit,
    elevation: Dp = 0.dp,
    divider: @Composable (() -> Unit)? = null,
    leadingIconTestTag: String = LEADING_ICON,
    titleTestTag: String = TITLE,
    subTitleTestTag: String = SUBTITLE,
    showBackButton: Boolean = true
) {
    Surface(
        modifier = modifier
            .mapTestTagAsResourceID(),
        color = MaterialTheme.colors.background,
        elevation = elevation
    ) {
        Column(modifier = Modifier.heightIn(min = 56.dp)) {
            Row(
                modifier = Modifier
                    .addTestTag(TOP_APPBAR_PARENT)
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.weight(1f, false)) {
                    if (showBackButton) {
                        IconButton(
                            onClick = actionOnClick,
                            modifier = Modifier
                                .addTestTag(leadingIconTestTag)
                                .align(Alignment.CenterVertically)
                                .clickable(onClick = actionOnClick)
                        ) {
                            RdsIcon(config = RdsIconConfig(painter = painterResource(actionIcon)))
                        }
                    }
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp)
                    ) {
                        RdsTextView(
                            type = RdsTextType.HeadlineSmall,
                            text = toolbarText,
                            modifier = Modifier.addTestTag(titleTestTag),
                            maxLines = 1
                        )
                        if (subtitleText.isNotBlank()) {
                            RdsTextView(
                                type = RdsTextType.BodySmall,
                                text = subtitleText,
                                modifier = Modifier.addTestTag(subTitleTestTag),
                                maxLines = 1,
                                color = RdsColors.dark3
                            )
                        }
                    }
                }

                if (trailingContent != null) {
                    trailingContent()
                }
            }
            if (divider != null)
                divider()
        }
    }
}

@Composable
fun TopAppBarCenteredTitle(
    modifier: Modifier = Modifier,
    @DrawableRes actionIcon: Int,
    toolbarText: @Composable BoxScope.() -> Unit,
    actionOnClick: () -> Unit,
    elevation: Dp = 0.dp,
    divider: @Composable (() -> Unit)? = null
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.background,
        elevation = elevation
    ) {
        Column(modifier = Modifier.heightIn(min = 56.dp)) {
            Box(contentAlignment = Alignment.Center) {
                IconButton(
                    onClick = actionOnClick,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable(onClick = actionOnClick)
                        .align(Alignment.CenterStart)
                ) {
                    RdsIcon(config = RdsIconConfig(painter = painterResource(actionIcon)))
                }
                toolbarText()
            }
            if (divider != null)
                divider()
        }
    }
}

@Preview
@Composable
fun PreviewTopAppBarWithSubtitle() {
    RapidoTheme {
        TopAppBarWithSubtitle(
            modifier = Modifier.fillMaxWidth(),
            showBackButton = false,
            actionIcon = R.drawable.ic_keyboard_back,
            toolbarText = stringResource(R.string.auto_order_details),
            subtitleText = "",
            actionOnClick = {
            },
            trailingContent = {
                RdsSecondaryOutlinedButton(
                    modifier = Modifier,
                    text = "help",
                    leadingIconConfig = RdsIconConfig(
                        painter = painterResource(R.drawable.ic_help_support_menu_icon),
                        modifier = Modifier.padding(end = 10.dp)
                    ),
                    type = RdsTextType.TitleMedium,
                    contentPadding = PaddingValues(
                        start = 10.dp,
                        top = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp
                    )
                ) {
                }
            }
        )
    }
}
