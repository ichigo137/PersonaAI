package com.example.personaai.features.chat.data

import com.example.personaai.core.database.entity.ConversationEntity
import com.example.personaai.core.database.entity.MessageEntity
import com.example.personaai.features.chat.domain.model.ChatMessage
import com.example.personaai.features.chat.domain.model.Conversation
import com.example.personaai.features.chat.domain.model.Sender

fun ConversationEntity.toDomain() = Conversation(
    id = id,
    title = title,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun MessageEntity.toDomain() = ChatMessage(
    id = id,
    conversationId = conversationId,
    sender = Sender.valueOf(role.uppercase()),
    content = content,
    timestamp = timestamp
)
