package com.task.hub.presentation.ui.screen.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.task.hub.presentation.ui.theme.priegoFont
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AddScreen(
    destinationsNavigator: DestinationsNavigator,
    viewModel: AddTaskViewModel = viewModel()
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var isSheetOpen by remember {
        mutableStateOf(true)
    }

    val scope = rememberCoroutineScope()

    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                isSheetOpen = false
                destinationsNavigator.popBackStack()
            },
            containerColor = MaterialTheme.colors.background,
            modifier = Modifier.padding(top = 7.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                TaskActionBar(
                    onCancel = {
                        scope.launch {
                            sheetState.hide()
                            viewModel.resetTaskState()
                        }
                        destinationsNavigator.popBackStack()
                    },
                    onDone = { viewModel.onEvent(TaskFormEvent.Submit) }
                )

                CreateTaskContent(
                    onHideSheet = {
                        if (it) {
                            scope.launch {
                                sheetState.hide()
                                viewModel.resetTaskState()
                            }
                            destinationsNavigator.popBackStack()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun TaskActionBar(
    onCancel: () -> Unit,
    onDone: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp)
    ) {
        Text(
            text = "Cancel",
            fontSize = 16.sp,
            color = MaterialTheme.colors.primary,
            fontFamily = priegoFont,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.clickable { onCancel() }
        )
        Text(
            text = "New Task",
            fontSize = 16.sp,
            color = MaterialTheme.colors.onBackground,
            fontFamily = priegoFont,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(end = 10.dp)
        )
        Text(
            text = "Done",
            fontSize = 16.sp,
            color = MaterialTheme.colors.primary,
            fontFamily = priegoFont,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.clickable { onDone() }
        )
    }
}
