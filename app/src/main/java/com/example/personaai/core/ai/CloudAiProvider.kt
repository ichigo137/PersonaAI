package com.example.personaai.core.ai

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CloudAiProvider @Inject constructor(
    private val huggingFaceEngine: HuggingFaceEngine
) : AiEngine {

    override suspend fun generateResponse(
        prompt: String,
        conversation: List<String>
    ): String {

        // Temporary until we connect HuggingFaceEngine
        return "🔵 CLOUD ROUTER WORKING\n\n$prompt"
    }
}