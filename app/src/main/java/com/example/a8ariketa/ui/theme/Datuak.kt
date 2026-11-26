package com.example.a8ariketa.ui.theme

import android.content.Context
import android.util.Log
import kotlinx.serialization.json.Json

class Datuak {
    fun datuakLortu (context: Context): List<Txakurra>{
        return try {
            val jsonString = context.assets.open("txakurrak.json")
                .bufferedReader()
                .use { it.readText() }

            Json { ignoreUnknownKeys = true }
                .decodeFromString<List<Txakurra>>(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }

    }
}