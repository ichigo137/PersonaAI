package com.example.personaai.core.ai

import com.example.personaai.core.network.api.HuggingFaceApi
import com.example.personaai.core.network.dto.HuggingFaceMessage
import com.example.personaai.core.network.dto.HuggingFaceRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HuggingFaceEngine @Inject constructor(
    private val api: HuggingFaceApi
) : AiEngine {

    override suspend fun generateResponse(
        prompt: String,
        conversation: List<String>
    ): String {

        val payload = buildList {
            add(
                HuggingFaceMessage(
                    role = "system",
                    content = SYSTEM_PROMPT
                )
            )

            if (conversation.isEmpty()) {
                add(HuggingFaceMessage("user", prompt))
            } else {
                conversation.forEachIndexed { index, text ->
                    add(
                        HuggingFaceMessage(
                            role = if (index % 2 == 0) "user" else "assistant",
                            content = text
                        )
                    )
                }
            }
        }

        val request = HuggingFaceRequest(
            model = MODEL_ID,
            messages = payload
        )

        return try {
            val response = api.chatCompletion(request)

            if (response.isSuccessful) {
                response.body()
                    ?.choices
                    ?.firstOrNull()
                    ?.message
                    ?.content
                    ?.takeIf { it.isNotBlank() }
                    ?: "I don't have a response for that."
            } else {
                val error = response.errorBody()?.string() ?: "Unknown error"

                "HTTP ${response.code()}\n\n$error"            }
        } catch (e: Exception) {
            "Couldn't reach the AI. Check your connection and try again."
        }
    }

    private companion object {
        const val MODEL_ID = "openai/gpt-oss-120b"
        const val SYSTEM_PROMPT =
            "You are PersonaAI, a friendly, concise personal AI companion. Keep replies short and helpful."
    }
}