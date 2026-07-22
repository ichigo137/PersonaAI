package com.example.personaai.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.personaai.core.database.dao.ConversationDao
import com.example.personaai.core.database.dao.MessageDao
import com.example.personaai.core.database.entity.ConversationEntity
import com.example.personaai.core.database.entity.MessageEntity

@Database(
    entities = [ConversationEntity::class, MessageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun conversationDao(): ConversationDao

    abstract fun messageDao(): MessageDao
}
