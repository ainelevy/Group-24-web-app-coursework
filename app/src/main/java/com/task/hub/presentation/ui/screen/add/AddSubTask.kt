package com.task.hub.presentation.ui.screen.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AddSubtaskScreen(
    destinationsNavigator: DestinationsNavigator,
    viewModel: AddSubtaskViewModel = viewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
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
                modifier = Modifier.padding(top = 7.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Add Subtask",
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Text Field for Subtask Name
                    OutlinedTextField(
                        value = viewModel.subtaskName,
                        onValueChange = { viewModel.subtaskName = it },
                        label = { Text("Subtask Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    // Row for Cancel and Add Buttons
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Cancel Button
                        TextButton(
                            onClick = {
                                scope.launch {
                                    sheetState.hide()
                                }
                                destinationsNavigator.popBackStack()
                            }
                        ) {
                            Text("Cancel")
                        }

                        // Add Button
                        Button(
                            onClick = {
                                viewModel.addSubtask()
                                scope.launch {
                                    sheetState.hide()
                                }
                                destinationsNavigator.popBackStack()
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.secondary
                            )
                        ) {
                            Text("Add")
                        }
                    }
                }
            }
        }
    }
}
