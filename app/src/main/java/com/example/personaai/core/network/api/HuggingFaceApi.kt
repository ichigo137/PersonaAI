package com.example.personaai.core.network.api

import com.example.personaai.core.network.model.HuggingFaceRequest
import com.example.personaai.core.network.model.HuggingFaceResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface HuggingFaceApi {

    @POST("v1/chat/completions")
    suspend fun chatCompletion(
        @Body
        request: HuggingFaceRequest
    ): Response<HuggingFaceResponse>

}