package com.example.personaai.features.chat.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Query("SELECT * FROM messages ORDER BY timestamp ASC")
    fun getMessages(): Flow<List<ChatEntity>>

    @Insert
    suspend fun insert(message: ChatEntity)

    @Query("DELETE FROM messages")
    suspend fun clear()
}