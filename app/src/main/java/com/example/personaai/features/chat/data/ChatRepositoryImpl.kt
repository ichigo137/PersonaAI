package com.example.personaai.features.chat.data

import com.example.personaai.features.chat.data.local.ChatDao
import com.example.personaai.features.chat.domain.model.ChatMessage
import com.example.personaai.features.chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChatRepositoryImpl(
    private val dao: ChatDao
) : ChatRepository {

    override fun getMessages(): Flow<List<ChatMessage>> =
        dao.getMessages().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun insertMessage(message: ChatMessage) {
        dao.insertMessage(message.toEntity())
    }

    override suspend fun clearChat() {
        dao.clearChat()
    }
}