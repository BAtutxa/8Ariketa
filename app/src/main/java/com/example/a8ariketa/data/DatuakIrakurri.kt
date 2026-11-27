package com.example.a8ariketa.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatuakIrakurri(){
    fun datuakKargatu(context: Context): List<Txakurra> {
        return try {
            val jsonString = context.assets.open("txakurrak.json")
                .bufferedReader()
                .use{it.readText()}
            val listType = object : TypeToken<List<Txakurra>>() {}.type
            Gson().fromJson(jsonString, listType)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}