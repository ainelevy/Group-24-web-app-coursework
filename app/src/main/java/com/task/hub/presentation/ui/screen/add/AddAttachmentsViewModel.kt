package com.task.hub.presentation.ui.screen.add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddAttachmentViewModel : ViewModel() {
    // State for keeping track of selected attachment
    private val _selectedAttachment = MutableStateFlow<String?>(null)
    val selectedAttachment: StateFlow<String?> = _selectedAttachment

    // Function to set the selected attachment
    fun setSelectedAttachment(attachmentUri: String?) {
        _selectedAttachment.value = attachmentUri
    }
}
