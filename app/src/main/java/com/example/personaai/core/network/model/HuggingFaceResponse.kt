package com.example.personaai.core.network.model

data class HuggingFaceResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: AssistantMessage
)

data class AssistantMessage(
    val role: String,
    val content: String
)