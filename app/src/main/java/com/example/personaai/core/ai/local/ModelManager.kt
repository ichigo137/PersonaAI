package com.example.personaai.core.ai.local

import android.content.Context
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ModelManager @Inject constructor(
    private val context: Context
) {

    companion object {
        private const val MODEL_NAME = "gemma-3n.task"
    }

    private val modelDirectory: File
        get() = File(context.filesDir, "models")

    val modelFile: File
        get() = File(modelDirectory, MODEL_NAME)

    fun isModelDownloaded(): Boolean {
        return modelFile.exists()
    }

    fun ensureModelDirectory() {
        if (!modelDirectory.exists()) {
            modelDirectory.mkdirs()
        }
    }
}