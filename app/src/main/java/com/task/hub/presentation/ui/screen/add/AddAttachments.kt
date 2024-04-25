package com.task.hub.presentation.ui.screen.add

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
fun AddAttachmentScreen(
    destinationsNavigator: DestinationsNavigator,
    viewModel: AddAttachmentViewModel = viewModel()
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
                        text = "Add Attachment",
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // UI elements for adding attachments can be added here
                    // For example, you can have buttons to select from device storage, camera, etc.
                    Button(
                        onClick = {
                            // Handle attachment selection process
                            scope.launch {
                                sheetState.hide()
                            }
                            destinationsNavigator.popBackStack()
                        }
                    ) {
                        Text("Select from Device")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            // Handle attachment selection process
                            scope.launch {
                                sheetState.hide()
                            }
                            destinationsNavigator.popBackStack()
                        }
                    ) {
                        Text("Take Photo")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            // Handle attachment selection process
                            scope.launch {
                                sheetState.hide()
                            }
                            destinationsNavigator.popBackStack()
                        }
                    ) {
                        Text("Record Video")
                    }
                }
            }
        }
    }
}
