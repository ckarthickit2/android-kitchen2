package com.rapido.rapidodesignsystem.components.bottomsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.rapido.rapidodesignsystem.tokens.base.RdsColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RdsBottomSheetScaffold(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    sheetContent: @Composable () -> Unit,
    sheetContentAlignment: Alignment = Alignment.TopStart,
    bottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    scaffoldBackgroundColor: Color = RdsColors.white,
    onSheetShow: () -> Unit = {},
    onSheetHide: () -> Unit = {},
    topBar: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {},
    sheetGesturesEnabled: Boolean = true,
    content: @Composable (PaddingValues) -> Unit,

) {
    LaunchedEffect(bottomSheetState) {
        snapshotFlow { bottomSheetState.isVisible }.collect { isVisible ->
            if (isVisible) {
                onSheetShow()
            } else {
                onSheetHide()
            }
        }
    }
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = bottomSheetState,
        sheetContent = {
            Box(modifier.defaultMinSize(minHeight = 1.dp), contentAlignment = sheetContentAlignment) {
                sheetContent()
            }
        },
        sheetElevation = 0.dp,
        sheetShape = RectangleShape,
        sheetBackgroundColor = RdsColors.transparent,
        sheetGesturesEnabled = sheetGesturesEnabled
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            },
            scaffoldState = scaffoldState,
            topBar = topBar,
            content = content,
            bottomBar = footer,
            backgroundColor = scaffoldBackgroundColor
        )
    }
}
