package com.example.personaai.core.network.interceptor

import com.example.personaai.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.HF_API_KEY}")
            .addHeader("Content-Type", "application/json")
            .build()

        return chain.proceed(request)
    }
}