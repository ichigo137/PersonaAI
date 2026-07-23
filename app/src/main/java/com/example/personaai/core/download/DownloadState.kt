package com.example.personaai.core.download

sealed interface DownloadState {

    data object NotStarted : DownloadState

    data class Downloading(
        val progress: Int
    ) : DownloadState

    data object Completed : DownloadState

    data class Failed(
        val message: String
    ) : DownloadState
}