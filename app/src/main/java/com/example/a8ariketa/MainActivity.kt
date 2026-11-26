package com.example.a8ariketa

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a8ariketa.models.Txakurra
import com.example.a8ariketa.ui.theme._8AriketaTheme
import kotlinx.serialization.json.Json

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

@Composable
fun TxakurrakAPP(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    val txakurrak = remember {
        irakurriTxakurrak(context)
    }

    LazyColumn(modifier = modifier) {
        items(txakurrak) { txakurra ->
            TxakurraItem(txakurra)
        }
    }
}

@Composable
fun TxakurraItem(txakurra: Txakurra) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TxakurraIcon(txakurra = txakurra)

        Spacer(modifier = Modifier.width(16.dp))

        TxakurraInformazioa(txakurra = txakurra)
    }
}

@Composable
fun TxakurraIcon(txakurra: Txakurra) {
    val irudiId = when (txakurra.irudia) {
        "beltza" -> R.drawable.beltza
        "lur" -> R.drawable.lur
        "txiki" -> R.drawable.txiki
        "haizea" -> R.drawable.haizea
        "lagun" -> R.drawable.lagun
        "sua" -> R.drawable.sua
        "izar" -> R.drawable.izar
        "mendia" -> R.drawable.mendia
        "argia" -> R.drawable.argia
        else -> R.drawable.ic_launcher_foreground
    }

    Image(
        painter = painterResource(id = irudiId),
        contentDescription = txakurra.izena,
        modifier = Modifier.size(100.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TxakurraInformazioa(txakurra: Txakurra) {
    Column {
        Text(
            text = txakurra.izena
        )

        Text(
            text = "${txakurra.adina} urte"
        )
    }
}

fun irakurriTxakurrak(context: Context): List<Txakurra> {
    val json = context.assets
        .open("txakurrak.json")
        .bufferedReader()
        .use { it.readText() }

    return Json.decodeFromString<List<Txakurra>>(json)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _8AriketaTheme {
        TxakurrakAPP()
    }
}