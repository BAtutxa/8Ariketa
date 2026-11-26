package com.example.a8ariketa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a8ariketa.ui.theme._8AriketaTheme
import com.example.a8ariketa.ui.theme.Txakurra
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _8AriketaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TxakurrakAPP(
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Composable
fun TxakurrakAPP(txakurrak: List<Txakurra>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(txakurrak) { txakurra ->
            TxakurraItem(txakurra)
        }
    }
}

@Composable
fun TxakurraItem(txakurra: Txakurra, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth().padding(8.dp)) {
        Row(modifier = Modifier.padding(8.dp)) {
            TxakurraIcon(irudia = /* id de la foto */)
            TxakurraInformazioa(txakurra)
        }
    }
}

@Composable
fun TxakurraIcon(@DrawableRes irudia: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(irudia),
        contentDescription = null,
        modifier = modifier.size(64.dp)
    )
}

@Composable
fun TxakurraInformazioa(txakurra: Txakurra, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(start = 8.dp)) {
        Text(text = txakurra.izena)
        Text(text = "${txakurra.adina} urte")
        Text(text = txakurra.deskribapena)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _8AriketaTheme {
        TxakurrakAPP()
    }
}