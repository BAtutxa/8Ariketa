package com.example.a8ariketa

import android.content.Context
import org.json.JSONArray

fun txakurrakKargatu(context: Context): List<Txakurra> {
    // 1. Leer el archivo como texto
    val testua = context.assets.open("txakurrak.json")
        .bufferedReader()
        .use { it.readText() }

    // 2. Convertir el texto en un array JSON
    val array = JSONArray(testua)

    // 3. Crear un Txakurra por cada elemento
    return List(array.length()) { i ->
        val objektua = array.getJSONObject(i)
        Txakurra(
            irudia = context.resources.getIdentifier(
                objektua.getString("irudia"), "drawable", context.packageName
            ),
            izena = objektua.getString("izena"),
            adina = objektua.getInt("adina")
        )
    }
}