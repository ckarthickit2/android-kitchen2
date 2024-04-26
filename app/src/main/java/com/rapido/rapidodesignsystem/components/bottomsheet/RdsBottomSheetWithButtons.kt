package com.rapido.rapidodesignsystem.components.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.components.button.RdsPrimaryButton
import com.rapido.rapidodesignsystem.components.button.RdsSecondaryTextButton
import com.rapido.rapidodesignsystem.components.icon.RdsIconConfig
import com.rapido.rapidodesignsystem.components.text.RdsTextType
import com.rapido.rapidodesignsystem.components.text.RdsTextView
import com.rapido.rapidodesignsystem.tokens.base.RdsColors
import me.kartdroid.androidkitchen2.R

@Composable
fun RdsBottomSheetWithButtons(config: BottomSheetConfig) {
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                modifier = Modifier
                    .padding(end = 16.dp, bottom = 16.dp)
                    .size(24.dp)
                    .clickable {
                        config.closeOnClick()
                    },
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp, 4.dp)
                            .background(RdsColors.light1)
                    )
                }
                Column(
                    horizontalAlignment = config.body.contentAlignment,
                ) {
                    Image(
                        modifier = Modifier.size(120.dp, 131.dp),
                        contentScale = ContentScale.Fit,
                        painter = painterResource(id = config.header.image),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    RdsTextView(
                        type = RdsTextType.HeadlineMedium,
                        text = stringResource(id = config.body.title)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    RdsTextView(
                        type = RdsTextType.TitleMedium,
                        text = stringResource(id = config.body.description),
                        color = RdsColors.dark2
                    )
                }
                Column(
                    modifier = Modifier.padding(top = 24.dp, start = 8.dp, end = 8.dp)
                ) {
                    RdsPrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(
                            id = config.body.btnText
                        ),
                        contentPadding = PaddingValues(
                            top = 12.dp,
                            bottom = 12.dp
                        ),
                        type = RdsTextType.HeadlineSmall,
                        onClick = config.body.onClick
                    )
                    config.footer?.let { footer ->
                        Spacer(modifier = Modifier.height(24.dp))
                        Divider(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth(),
                            color = RdsColors.gray6
                        )
                        RdsSecondaryTextButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(
                                id = footer.btnText
                            ),
                            leadingIconConfig = footer.leadingIconConfig,
                            trailingIconConfig = footer.trailingIconConfig,
                            type = RdsTextType.HeadlineSmall,
                            onClick = footer.onClick
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewRdsBottomSheetWithButtons() {
    RdsBottomSheetWithButtons(
        BottomSheetConfig(
            header = BottomSheetHeader(
                image = R.drawable.ic_default_rider
            ),
            body = BottomSheetBody(
                title = R.string.not_getting_orders_caps,
                description = R.string.now_you_can_do,
                btnText = R.string.check_now,
                contentAlignment = Alignment.Start,
                onClick = {
                }
            ),
            footer = BottomSheetFooter(
                btnText = R.string.contact_support,
                leadingIconConfig = RdsIconConfig(
                    imageVector = Icons.Outlined.Call,
                    modifier = Modifier.padding(end = 7.dp)
                ),
                trailingIconConfig = null,
                onClick = {
                }
            ),
            closeOnClick = {
            }
        )
    )
}
