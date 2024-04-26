package com.rapido.rapidodesignsystem.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * @author [Jayesh Suthar](linkedin.com/in/jayeshsde)
 * @since 25/07/23.
 */
@Composable
fun DefaultBottomSheetContainer(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(
        start = 28.dp,
        top = 28.dp,
        end = 28.dp,
        bottom = 20.dp
    ),
    onClose: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .fillMaxWidth()
            .then(modifier)
    ) {
        IconButton(onClick = onClose, modifier = Modifier.align(Alignment.End)) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = "close icon",
                modifier = Modifier.defaultMinSize(minWidth = 32.dp, minHeight = 32.dp),
                tint = Color.Unspecified
            )
        }
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            Box(
                modifier = Modifier.padding(contentPadding)
            ) {
                content()
            }
        }
    }
}
