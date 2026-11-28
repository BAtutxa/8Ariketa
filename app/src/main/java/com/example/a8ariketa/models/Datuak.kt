package com.example.a8ariketa.models

import android.content.Context
import android.util.Log
import kotlinx.serialization.json.Json

class Datuak {
    //Leemos el JSON y hacemos la comversion a una List<Txakurra>
    fun datuakKargatu(context: Context, fitx: String): List<Txakurra> {
        return try {
            val jsonString = context.assets.open(fitx)
                .bufferedReader()
                .use { it.readText() }

            Log.d("EL string: ", jsonString)

            Json { ignoreUnknownKeys = true }
                .decodeFromString<List<Txakurra>>(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}