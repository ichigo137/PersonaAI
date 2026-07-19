package com.example.personaai.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.personaai.features.chat.data.local.ChatDao
import com.example.personaai.features.chat.data.local.ChatEntity

@Database(
    entities = [ChatEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun chatDao(): ChatDao
}