package com.example.personaai.features.chat.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Query(
        "SELECT * FROM chat_messages ORDER BY timestamp ASC"
    )
    fun getMessages(): Flow<List<ChatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(
        message: ChatEntity
    )

    @Query("DELETE FROM chat_messages")
    suspend fun clearChat()

}