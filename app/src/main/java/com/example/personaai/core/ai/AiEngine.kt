package com.example.personaai.core.ai

interface AiEngine {

    suspend fun generateResponse(
        prompt: String,
        conversation: List<String> = emptyList()
    ): String
}