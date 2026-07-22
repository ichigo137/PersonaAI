package com.example.personaai.features.chat.domain.model

/** UI-facing role for a chat message. */
enum class Sender { USER, ASSISTANT }

/** Domain conversation header. */
data class Conversation(
    val id: String,
    val title: String,
    val createdAt: Long,
    val updatedAt: Long
)

/** Domain chat message. */
data class ChatMessage(
    val id: String,
    val conversationId: String,
    val sender: Sender,
    val content: String,
    val timestamp: Long
)
