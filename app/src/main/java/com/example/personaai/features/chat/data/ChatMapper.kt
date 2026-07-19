package com.example.personaai.features.chat.data

import com.example.personaai.features.chat.data.local.ChatEntity
import com.example.personaai.features.chat.domain.model.ChatMessage

fun ChatEntity.toDomain() = ChatMessage(
    id = id,
    text = text,
    isUser = isUser,
    timestamp = timestamp
)

fun ChatMessage.toEntity() = ChatEntity(
    id = id,
    text = text,
    isUser = isUser,
    timestamp = timestamp
)