package com.task.hub.presentation.ui.screen.add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddSubtaskViewModel : ViewModel() {
    // State for storing the subtask name
    private val _subtaskName = MutableStateFlow("")
    val subtaskName: StateFlow<String> = _subtaskName

    // Function to update the subtask name
    fun updateSubtaskName(name: String) {
        _subtaskName.value = name
    }

    // Function to add the subtask
    fun addSubtask() {
        // Add logic here to save the subtask or perform any necessary actions
    }
}
