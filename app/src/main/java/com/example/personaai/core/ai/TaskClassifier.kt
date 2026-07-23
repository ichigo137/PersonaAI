package com.example.personaai.core.ai

import javax.inject.Inject
import javax.inject.Singleton

enum class TaskType {
    LOCAL,
    CLOUD
}

@Singleton
class TaskClassifier @Inject constructor() {

    fun classify(prompt: String): TaskType {

        val text = prompt.lowercase()

        val cloudKeywords = listOf(
            "today",
            "latest",
            "news",
            "weather",
            "stock",
            "price",
            "current",
            "live",
            "search",
            "internet",
            "web",
            "google",
            "who won",
            "score"
        )

        return if (cloudKeywords.any { text.contains(it) }) {
            TaskType.CLOUD
        } else {
            TaskType.LOCAL
        }
    }
}