package com.example.a8ariketa

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a8ariketa.models.Datuak
import com.example.a8ariketa.models.Txakurra
import com.example.a8ariketa.ui.theme._8AriketaTheme

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
    val txakurrak = remember { Datuak().datuakKargatu(context, "txakurrak.json") }
    Log.d("Txakurra zerrenda", txakurrak.toString())

    LazyColumn() {
        items(txakurrak) {txakurra -> TxakurraItem(txakurra)}
    }
}

@Composable
fun TxakurraItem (txakurra: Txakurra, modifier: Modifier = Modifier){
    Card {
        Row {
            TxakurraIcon(txakurra.irudia)
            TxakurraInformazioa(txakurra.izena, txakurra.adina)
        }
    }
}

@Composable
fun TxakurraIcon(irudia: String) {

    val context = LocalContext.current
    val irudiaId = context.resources.getIdentifier(
        irudia.substringBeforeLast("."),
        "drawable",
        context.packageName
    )

    Image(
        painter = painterResource(id = irudiaId),
        contentDescription = null,
        modifier = Modifier.size(60.dp)
    )
}

@Composable
fun TxakurraInformazioa(izena: String, adina: Int) {
    Column {
        Text(text = izena)
        Text(text = "$adina urte")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _8AriketaTheme {
        TxakurrakAPP()
    }
}