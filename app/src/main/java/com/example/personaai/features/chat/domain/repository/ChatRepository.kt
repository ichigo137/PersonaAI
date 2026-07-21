package com.example.personaai.features.chat.domain.repository

import com.example.personaai.features.chat.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun getMessages(): Flow<List<ChatMessage>>

    suspend fun insertMessage(message: ChatMessage)

    suspend fun clearChat()
}