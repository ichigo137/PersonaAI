package com.example.personaai.core.ai

/**
 * Generic chat message the AI layer works with.
 *
 * @property role one of "system", "user", or "assistant".
 */
data class ChatMessage(
    val role: String,
    val content: String
)

/**
 * Abstraction over the AI backend. Swappable providers (HuggingFace,
 * OpenAI, on-device, etc.) all implement this.
 */
interface AiProvider {

    /**
     * Send a conversation to the AI and return the assistant's reply text.
     *
     * Implementations should never throw — on failure they return a
     * human-readable error string so the UI can display it.
     */
    suspend fun chat(messages: List<ChatMessage>): String
}
