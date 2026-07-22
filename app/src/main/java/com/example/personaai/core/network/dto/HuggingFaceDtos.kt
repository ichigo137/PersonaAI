package com.example.personaai.core.network.dto

import com.google.gson.annotations.SerializedName

data class HuggingFaceRequest(
    val model: String,
    val messages: List<HuggingFaceMessage>,
    val temperature: Double = 0.7,
    @SerializedName("max_tokens")
    val maxTokens: Int = 512
)

data class HuggingFaceMessage(
    val role: String,
    val content: String
)

data class HuggingFaceResponse(
    val choices: List<HuggingFaceChoice> = emptyList()
)

data class HuggingFaceChoice(
    val message: HuggingFaceAssistantMessage? = null,
    @SerializedName("finish_reason")
    val finishReason: String? = null
)

data class HuggingFaceAssistantMessage(
    val role: String? = null,
    val content: String? = null
)
