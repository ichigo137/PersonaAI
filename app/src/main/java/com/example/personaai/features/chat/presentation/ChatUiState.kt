package com.example.personaai.features.chat.presentation

import com.example.personaai.features.chat.domain.model.ChatMessage

data class ChatUiState(
    val messages: List<ChatMessage> = emptyList(),
    val input: String = "",
    val isLoading: Boolean = false
)