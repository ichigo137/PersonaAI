package com.example.personaai.di

import com.example.personaai.core.ai.AiProvider
import com.example.personaai.core.ai.HuggingFaceProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AiModule {

    @Binds
    @Singleton
    abstract fun bindAiProvider(impl: HuggingFaceProvider): AiProvider
}
