package com.example.personaai.core.network.model

data class HuggingFaceRequest(
    val model: String,
    val messages: List<HuggingFaceMessage>
)

data class HuggingFaceMessage(
    val role: String,
    val content: String
)