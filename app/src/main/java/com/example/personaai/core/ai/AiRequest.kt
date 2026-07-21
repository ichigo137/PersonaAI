package com.example.personaai.core.ai

data class AiRequest(
    val messages: List<AiMessage>
)

data class AiMessage(
    val role: Role,
    val content: String
)

enum class Role {
    SYSTEM,
    USER,
    ASSISTANT
}