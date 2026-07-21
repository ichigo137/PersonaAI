package com.example.personaai.di

import android.content.Context
import androidx.room.Room
import com.example.personaai.core.database.AppDatabase
import com.example.personaai.features.chat.data.ChatRepositoryImpl
import com.example.personaai.features.chat.data.local.ChatDao
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
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "persona_database"
        ).build()
    }

    @Provides
    fun provideChatDao(
        database: AppDatabase
    ): ChatDao {
        return database.chatDao()
    }

    @Provides
    @Singleton
    fun provideChatRepository(
        dao: ChatDao
    ): ChatRepository {
        return ChatRepositoryImpl(dao)
    }
}