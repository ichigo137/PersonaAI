package com.example.personaai.features.chat.data

import com.example.personaai.core.ai.AiProvider
import com.example.personaai.core.ai.ChatMessage as AiChatMessage
import com.example.personaai.core.database.dao.ConversationDao
import com.example.personaai.core.database.dao.MessageDao
import com.example.personaai.core.database.entity.ConversationEntity
import com.example.personaai.core.database.entity.MessageEntity
import com.example.personaai.features.chat.domain.model.ChatMessage
import com.example.personaai.features.chat.domain.model.Conversation
import com.example.personaai.features.chat.domain.model.Sender
import com.example.personaai.features.chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val conversationDao: ConversationDao,
    private val messageDao: MessageDao,
    private val aiProvider: AiProvider
) : ChatRepository {

    override fun observeConversations(): Flow<List<Conversation>> =
        conversationDao.observeAll().map { list -> list.map { it.toDomain() } }

    override fun observeMessages(conversationId: String): Flow<List<ChatMessage>> =
        messageDao.observeByConversation(conversationId)
            .map { list -> list.map { it.toDomain() } }

    override suspend fun createConversation(title: String): String {
        val now = System.currentTimeMillis()
        val id = UUID.randomUUID().toString()
        conversationDao.insert(
            ConversationEntity(
                id = id,
                title = title.ifBlank { "New chat" },
                createdAt = now,
                updatedAt = now
            )
        )
        return id
    }

    override suspend fun sendMessage(conversationId: String, userText: String) {
        val now = System.currentTimeMillis()

        // 1. Persist the user's message.
        messageDao.insert(
            MessageEntity(
                id = UUID.randomUUID().toString(),
                conversationId = conversationId,
                role = Sender.USER.name.lowercase(),
                content = userText,
                timestamp = now
            )
        )

        // 2. Build context from full history for the AI.
        val history = messageDao.getByConversation(conversationId)
            .map { msg ->
                AiChatMessage(
                    role = if (msg.role.equals("user", ignoreCase = true)) "user" else "assistant",
                    content = msg.content
                )
            }

        // 3. Ask the AI. The provider never throws.
        val reply = aiProvider.chat(history)

        // 4. Persist the assistant reply.
        messageDao.insert(
            MessageEntity(
                id = UUID.randomUUID().toString(),
                conversationId = conversationId,
                role = Sender.ASSISTANT.name.lowercase(),
                content = reply,
                timestamp = System.currentTimeMillis()
            )
        )

        // 5. If this is the first exchange, title the conversation from the user's text.
        if (history.size == 1) {
            conversationDao.getById(conversationId)?.let { conv ->
                conversationDao.update(
                    conv.copy(
                        title = userText.take(40).trim().ifBlank { conv.title },
                        updatedAt = System.currentTimeMillis()
                    )
                )
            }
        } else {
            // Keep updatedAt fresh so the conversation bubbles to the top of history.
            conversationDao.getById(conversationId)?.let { conv ->
                conversationDao.update(conv.copy(updatedAt = System.currentTimeMillis()))
            }
        }
    }

    override suspend fun renameConversation(conversationId: String, newTitle: String) {
        conversationDao.getById(conversationId)?.let { conv ->
            conversationDao.update(conv.copy(title = newTitle))
        }
    }

    override suspend fun deleteConversation(conversationId: String) {
        conversationDao.delete(conversationId)
    }
}
