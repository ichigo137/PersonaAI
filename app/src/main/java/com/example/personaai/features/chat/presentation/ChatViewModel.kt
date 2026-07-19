package com.example.personaai.features.chat.presentation

import androidx.lifecycle.ViewModel
import com.example.personaai.features.chat.domain.model.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
class ChatViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())

    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    fun onInputChanged(text: String) {
        _uiState.value = _uiState.value.copy(
            input = text
        )
    }

    private var nextId = 1L

    fun sendMessage() {

        val message = uiState.value.input.trim()

        if (message.isBlank()) return

        val userMessage = ChatMessage(
            id = nextId++,
            text = message,
            isUser = true,
            timestamp = System.currentTimeMillis()
        )

        _uiState.value = _uiState.value.copy(
            messages = _uiState.value.messages + userMessage,
            input = ""
        )

        fakeReply()
    }

    private fun fakeReply() {

        viewModelScope.launch {

            delay(1000)

            val ai = ChatMessage(
                id = nextId++,
                text = "I'm still under development.",
                isUser = false,
                timestamp = System.currentTimeMillis()
            )

            _uiState.value = _uiState.value.copy(
                messages = _uiState.value.messages + ai
            )
        }
    }
}