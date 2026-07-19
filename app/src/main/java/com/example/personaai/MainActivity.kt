package com.example.personaai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import com.example.personaai.core.database.AppDatabase
import com.example.personaai.core.database.DatabaseProvider
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        DatabaseProvider.database =
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "persona.db"
            ).build()

        setContent {
            PersonaAIApp()
        }
    }
}
