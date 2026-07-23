package com.example.personaai.features.chat.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personaai.features.chat.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ChatUiState(
    val messages: List<com.example.personaai.features.chat.domain.model.ChatMessage> = emptyList(),
    val input: String = "",
    val isLoading: Boolean = false
)

@HiltViewModel
class ChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ChatRepository
) : ViewModel() {

    val conversationId: String = checkNotNull(savedStateHandle["conversationId"])

    private val _input = MutableStateFlow("")
    val input = _input.asStateFlow()

    private val _isSending = MutableStateFlow(false)

    val uiState: StateFlow<ChatUiState> = combine(
        repository.observeMessages(conversationId),
        _input,
        _isSending
    ) { messages, input, isSending ->
        ChatUiState(
            messages = messages,
            input = input,
            isLoading = isSending
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
        if (text.isBlank() || _isSending.value) return

        _input.value = ""
        _isSending.value = true

        viewModelScope.launch {
            try {
                repository.sendMessage(conversationId, text)
            } finally {
                _isSending.value = false
            }
        }
    }
}

/** ViewModel-scoped companion for creating new conversations before navigating. */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ChatRepository
) : ViewModel() {

    fun createConversationAndNavigate(onCreated: (String) -> Unit) {
        viewModelScope.launch {
            val id = repository.createConversation("New chat")
            onCreated(id)
        }
    }

    val conversations = repository.observeConversations()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteConversation(conversationId: String) {
        viewModelScope.launch { repository.deleteConversation(conversationId) }
    }
}
