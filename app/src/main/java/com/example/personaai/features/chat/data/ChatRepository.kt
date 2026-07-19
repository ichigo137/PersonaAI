package com.example.personaai.features.chat.data

import com.example.personaai.features.chat.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    val messages: Flow<List<ChatMessage>>

    suspend fun send(message: ChatMessage)

    suspend fun clear()
}