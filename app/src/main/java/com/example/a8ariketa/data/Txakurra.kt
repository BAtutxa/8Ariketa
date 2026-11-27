package com.example.a8ariketa.data

import android.content.Context
import org.json.JSONArray

// TXAKURRAREN DATUAK GORDETZEKO KLASEA
data class Txakurra(
    val irudia: String,
    val izena: String,
    val adina: Int
)

// ASSETS KARPETATIK JSON FITXATEGIA IRAKURTZEKO FUNTZIOA
fun irakurriTxakurrak(context: Context): List<Txakurra> {
    // TXAKURREN ZERRENDA HUTS BAT SORTU
    val txakurrak = mutableListOf<Txakurra>()

    try {
        // JSON FITXATEGIA IREKI ETA TESTU BEZALA IRAKURRI
        val jsonString = context.assets.open("txakurrak.json")
            .bufferedReader()
            .use { it.readText() }

        // TESTUA JSON ARRAY BIHURTU
        val jsonArray = JSONArray(jsonString)

        // ELEMENTU BAKOITZA ZEHARKATU ETA ZERRENDARA GEHITU
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            txakurrak.add(
                Txakurra(
                    irudia = obj.getString("irudia"),
                    izena = obj.getString("izena"),
                    adina = obj.getInt("adina")
                )
            )
        }
    } catch (e: Exception) {
        // ERROREA GERTATUZ GERO, KONTSOLAN ERAKUTSI
        e.printStackTrace()
    }

    // TXAKURREN ZERRENDA ITZULI
    return txakurrak
}