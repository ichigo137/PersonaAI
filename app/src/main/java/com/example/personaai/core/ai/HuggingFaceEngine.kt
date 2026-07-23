package com.example.personaai.core.ai

import com.example.personaai.core.network.api.HuggingFaceApi
import com.example.personaai.core.network.dto.HuggingFaceMessage
import com.example.personaai.core.network.dto.HuggingFaceRequest
import javax.inject.Inject

private const val TAG = "HuggingFaceProvider"

/**
 * Talks to the HuggingFace Inference Router (OpenAI-compatible
 * `v1/chat/completions` endpoint) using [MODEL_ID].
 */
class HuggingFaceProvider @Inject constructor(
    private val api: HuggingFaceApi
) : AiProvider {

    override suspend fun chat(messages: List<ChatMessage>): String {
        // Prepend a lightweight system prompt to give Gemma a persona.
        val payload = buildList {
            add(ChatMessage(SYSTEM_ROLE, SYSTEM_PROMPT))
            addAll(messages)
        }.map { HuggingFaceMessage(role = it.role, content = it.content) }

        val request = HuggingFaceRequest(
            model = MODEL_ID,
            messages = payload
        )

        return try {
            val response = api.chatCompletion(request)
            if (response.isSuccessful) {
                response.body()?.choices?.firstOrNull()?.message?.content
                    ?.takeIf { it.isNotBlank() }
                    ?: "I don't have a response for that."
            } else {
                "Service error (${response.code()}). Try again in a moment."
            }
        } catch (e: Exception) {
            "Couldn't reach the AI. Check your connection and try again."
        }
    }

    private companion object {
        const val MODEL_ID = "google/gemma-3-1b-it"
        const val SYSTEM_ROLE = "system"
        const val SYSTEM_PROMPT =
            "You are PersonaAI, a friendly, concise personal AI companion. " +
                "Keep replies short and helpful."
    }
}
