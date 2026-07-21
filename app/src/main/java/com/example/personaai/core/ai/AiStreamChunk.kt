package com.example.personaai.core.ai

data class AiStreamChunk(
    val text: String,
    val isComplete: Boolean = false
)