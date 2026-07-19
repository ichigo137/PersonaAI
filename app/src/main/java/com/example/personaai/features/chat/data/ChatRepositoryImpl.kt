package com.example.personaai.features.chat.data

import com.example.personaai.features.chat.data.local.ChatDao
import com.example.personaai.features.chat.data.local.ChatEntity
import com.example.personaai.features.chat.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChatRepositoryImpl(
    private val chatDao: ChatDao
) : ChatRepository {

    override val messages: Flow<List<ChatMessage>> =
        chatDao.getMessages().map { entities ->
            entities.map { it.toDomain() }
        }

    override suspend fun send(message: ChatMessage) {
        chatDao.insert(message.toEntity())
    }

    override suspend fun clear() {
        chatDao.clear()
    }
}