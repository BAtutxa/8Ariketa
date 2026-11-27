
package com.example.a8ariketa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a8ariketa.ui.theme._8AriketaTheme
import org.json.JSONArray

// Txakurraren datuak gordetzeko klasea
data class Txakurra(
    val izena: String,
    val adina: Int,
    val deskribapena: String
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            _8AriketaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TxakurrakAPP(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// JSON fitxategia irakurtzeko funtzioa
fun TxakurrakKargatu(context: android.content.Context): List<Txakurra> {

    val json = context.assets.open("txakurrak.json")
        .bufferedReader()
        .use { it.readText() }

    val jsonArray = JSONArray(json)
    val txakurrak = mutableListOf<Txakurra>()

    for (i in 0 until jsonArray.length()) {

        val objektua = jsonArray.getJSONObject(i)

        txakurrak.add(
            Txakurra(
                izena = objektua.getString("izena"),
                adina = objektua.getInt("adina"),
                deskribapena = objektua.getString("deskribapena")
            )
        )
    }

    return txakurrak
}

// Txakurren zerrenda erakusten du
@Composable
fun TxakurrakAPP(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    val txakurrak = remember {
        TxakurrakKargatu(context)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(txakurrak) { txakurra ->
            TxakurraItem(txakurra)
        }
    }
}

// Txakur bakoitzaren txartela erakusten du
@Composable
fun TxakurraItem(txakurra: Txakurra) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            TxakurraIcon(txakurra)

            Spacer(modifier = Modifier.width(12.dp))

            TxakurraInformazioa(txakurra)
        }
    }
}

// Txakurraren argazkia erakusten du
@Composable
fun TxakurraIcon(txakurra: Txakurra) {

    val context = LocalContext.current

    val irudiId = remember(txakurra.izena) {
        context.resources.getIdentifier(
            txakurra.izena.lowercase(),
            "drawable",
            context.packageName
        )
    }

    if (irudiId != 0) {
        Image(
            painter = painterResource(id = irudiId),
            contentDescription = txakurra.izena,
            modifier = Modifier.size(55.dp)
        )
    }
}

// Txakurraren adina eta deskribapena erakusten ditu
@Composable
fun TxakurraInformazioa(txakurra: Txakurra) {

    Column {

        Text(
            text = txakurra.izena,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "${txakurra.adina} urte"
        )

        Text(
            text = txakurra.deskribapena
        )
    }
}

// Diseinuaren aurrebista erakusten du
@Preview(showBackground = true)
@Composable
fun TxakurraPreview() {

    _8AriketaTheme {
        TxakurraItem(
            Txakurra(
                izena = "Beltza",
                adina = 2,
                deskribapena = "Oso txakur jolastia da."
            )
        )
    }
}
