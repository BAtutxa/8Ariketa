package com.example.a8ariketa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a8ariketa.ui.theme._8AriketaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TxakurrakAPP()
            }
        }
    }
}

@Composable
fun TxakurrakAPP() {
    val context = LocalContext.current
    val txakurrak = remember { txakurrakKargatu(context) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(txakurrak) { txakurra ->
            TxakurraItem(txakurra)
        }
    }
}

// Provisional
@Composable
fun TxakurraItem(txakurra: Txakurra) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        TxakurraIcon(txakurra.irudia)
        TxakurraInformazioa(txakurra.izena, txakurra.adina, Modifier.padding(20.dp))

    }
}

@Composable
fun TxakurraIcon(@DrawableRes irudia: Int) {
    Image(
        painter = painterResource(irudia),
        contentDescription = "Txakurraren argazkia",
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(48.dp)
    )
}

@Composable
fun TxakurraInformazioa(izena: String, adina: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
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