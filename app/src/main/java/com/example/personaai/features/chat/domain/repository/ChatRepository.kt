package com.example.personaai.features.chat.domain.repository

import com.example.personaai.features.chat.domain.model.ChatMessage
import com.example.personaai.features.chat.domain.model.Conversation
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    /** All conversations, newest first. */
    fun observeConversations(): Flow<List<Conversation>>

    /** Messages in a conversation, oldest first. */
    fun observeMessages(conversationId: String): Flow<List<ChatMessage>>

    /** Create a new conversation, returning its id. */
    suspend fun createConversation(title: String): String

    /** Persist the user's message, call the AI, and persist the reply. */
    suspend fun sendMessage(conversationId: String, userText: String)

    suspend fun renameConversation(conversationId: String, newTitle: String)

    suspend fun deleteConversation(conversationId: String)
}
