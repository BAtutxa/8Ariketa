package com.example.a8ariketa

import android.content.Context
import org.json.JSONArray

fun kargatuTxakurrak(context: Context): List<Txakurra> {
    val jsonString = context.assets.open("txakurrak.json")
        .bufferedReader()
        .use { it.readText() }

    val jsonArray = JSONArray(jsonString)
    val zerrenda = mutableListOf<Txakurra>()

    for (i in 0 until jsonArray.length()) {
        val obj = jsonArray.getJSONObject(i)
        zerrenda.add(
            Txakurra(
                irudia = obj.getString("irudia"),
                izena = obj.getString("izena"),
                adina = obj.getInt("adina"),
                deskribapena = obj.getString("deskribapena")
            )
        )
    }

    return zerrenda
}