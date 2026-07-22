package com.example.personaai.di

import android.content.Context
import androidx.room.Room
import com.example.personaai.core.database.AppDatabase
import com.example.personaai.core.database.dao.ConversationDao
import com.example.personaai.core.database.dao.MessageDao
import com.example.personaai.features.chat.data.ChatRepositoryImpl
import com.example.personaai.features.chat.domain.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "persona_database")
            .build()

    @Provides
    fun provideConversationDao(db: AppDatabase): ConversationDao =
        db.conversationDao()

    @Provides
    fun provideMessageDao(db: AppDatabase): MessageDao =
        db.messageDao()

    @Provides
    @Singleton
    fun provideChatRepository(
        conversationDao: ConversationDao,
        messageDao: MessageDao,
        aiProvider: com.example.personaai.core.ai.AiProvider
    ): ChatRepository =
        ChatRepositoryImpl(conversationDao, messageDao, aiProvider)
}
