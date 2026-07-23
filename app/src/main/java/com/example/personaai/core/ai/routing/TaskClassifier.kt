package com.example.personaai.core.ai.routing
import javax.inject.Inject
class TaskClassifier @Inject constructor(){

    fun classify(prompt: String): TaskType {

        val text = prompt.trim().lowercase()

        // Empty prompt
        if (text.isBlank()) {
            return TaskType.UNKNOWN
        }

        // Weather
        if (
            text.contains("weather") ||
            text.contains("temperature") ||
            text.contains("forecast") ||
            text.contains("rain")
        ) {
            return TaskType.WEATHER
        }

        // Translation
        if (
            text.contains("translate") ||
            text.contains("translation")
        ) {
            return TaskType.TRANSLATION
        }

        // Memory
        if (
            text.contains("remember") ||
            text.contains("forget") ||
            text.contains("memory")
        ) {
            return TaskType.MEMORY
        }

        // Summary
        if (
            text.contains("summarize") ||
            text.contains("summary")
        ) {
            return TaskType.SUMMARY
        }

        // Coding
        if (
            text.contains("code") ||
            text.contains("kotlin") ||
            text.contains("java") ||
            text.contains("python") ||
            text.contains("bug") ||
            text.contains("compile") ||
            text.contains("android")
        ) {
            return TaskType.CODING
        }

        // Calculator
        if (
            text.matches(Regex("[0-9+\\-*/(). ]+")) ||
            text.startsWith("calculate")
        ) {
            return TaskType.CALCULATOR
        }

        // Search
        if (
            text.contains("search") ||
            text.contains("find")
        ) {
            return TaskType.SEARCH
        }

        // Simple greetings
        if (
            text == "hi" ||
            text == "hello" ||
            text == "hey"
        ) {
            return TaskType.CHAT
        }

        return TaskType.GENERAL
    }
}