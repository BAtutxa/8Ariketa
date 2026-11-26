package com.example.a8ariketa.data

import android.content.Context
import androidx.annotation.DrawableRes
import com.example.a8ariketa.R
import org.json.JSONArray

data class Txakurra(
    @DrawableRes val irudia: Int,
    val izena: String,
    val adina: Int,
    val deskribapena: String
)

private fun drawableForName(name: String): Int = when (name) {
    "beltza" -> R.drawable.beltza
    "lur" -> R.drawable.lur
    "txiki" -> R.drawable.txiki
    "haizea" -> R.drawable.haizea
    "lagun" -> R.drawable.lagun
    "sua" -> R.drawable.sua
    "izar" -> R.drawable.izar
    "mendia" -> R.drawable.mendia
    "argia" -> R.drawable.argia
    else -> R.drawable.beltza
}

private fun fallbackTxakurrak(): List<Txakurra> = listOf(
    Txakurra(R.drawable.beltza, "Beltza", 2, "Oso txakur jolastia da eta parkean korrika egitea maite du."),
    Txakurra(R.drawable.lur, "Lur", 16, "Txakur lasaia eta jakintsua, eguzkitan lo egitea gustuko duena."),
    Txakurra(R.drawable.txiki, "Txiki", 2, "Energia handikoa, beti baloiaren atzetik ibiltzen da."),
    Txakurra(R.drawable.haizea, "Haizea", 8, "Oso leiala eta mendi-ibilaldi luzeak egiteko lagun bikaina."),
    Txakurra(R.drawable.lagun, "Lagun", 8, "Maitekorra eta besteen konpainian egotea izugarri gustatzen zaio."),
    Txakurra(R.drawable.sua, "Sua", 14, "Oso txakur zintzoa, etxean goxo-goxo egotea maite du."),
    Txakurra(R.drawable.izar, "Izar", 2, "Jakin-min handikoa eta beste txakurrekin jolasteko gogoz beti."),
    Txakurra(R.drawable.mendia, "Mendia", 7, "Izaera orekatua du eta igeri egitea asko gustatzen zaio."),
    Txakurra(R.drawable.argia, "Argia", 4, "Azkarra, alaia eta edozein abenturatarako prest dagoena.")
)

fun irakurriTxakurrak(context: Context): List<Txakurra> {
    return try {
        val jsonText = context.assets.open("txakurrak.json").bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonText)
        List(jsonArray.length()) { index ->
            val obj = jsonArray.getJSONObject(index)
            Txakurra(
                irudia = drawableForName(obj.getString("irudia")),
                izena = obj.getString("izena"),
                adina = obj.getInt("adina"),
                deskribapena = obj.optString("deskribapena", "")
            )
        }
    } catch (_: Exception) {
        fallbackTxakurrak()
    }
}
