package com.example.personaai.features.chat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personaai.features.chat.domain.model.ChatMessage
import com.example.personaai.features.chat.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.combine

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ChatRepository
) : ViewModel() {

    private val _input = MutableStateFlow("")
    val input = _input.asStateFlow()

    val messages = repository
        .getMessages()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val uiState: StateFlow<ChatUiState> =
        combine(messages, _input) { list, input ->

            ChatUiState(
                messages = list,
                input = input,
                isLoading = false
            )

        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ChatUiState()
        )

    fun onInputChanged(text: String) {
        _input.value = text
    }

    fun sendMessage() {

        val text = _input.value.trim()

        if (text.isBlank()) return

        _input.value = ""

        viewModelScope.launch {

            repository.insertMessage(
                ChatMessage(
                    text = text,
                    isUser = true
                )
            )

            delay(800)

            repository.insertMessage(
                ChatMessage(
                    text = "I'm still under development.",
                    isUser = false
                )
            )
        }
    }

    fun clearChat() {
        viewModelScope.launch {
            repository.clearChat()
        }
    }
}