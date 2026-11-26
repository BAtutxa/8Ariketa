package com.example.a8ariketa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a8ariketa.ui.theme.Datuak
import com.example.a8ariketa.ui.theme.Txakurra
import com.example.a8ariketa.ui.theme._8AriketaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val txakurrak = Datuak().datuakLortu(this)
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
    val datuak = remember { Datuak().datuakLortu(context) }
    LazyColumn(modifier = modifier) {
        items(datuak) { datua -> TxakurraItem(datua) }
    }
}

@Composable
fun TxakurraItem(datua: Txakurra, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Row(modifier = Modifier.padding(8.dp)) {
            TxakurraIcon(datua)
            TxakurraInformazioa(datua)
        }
    }
}

@Composable
fun TxakurraIcon(datua: Txakurra, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val id = context.resources.getIdentifier(datua.irudia, "drawable", context.packageName)

    Image(
        painter = painterResource(id),
        contentDescription = null,
        modifier = Modifier.size(64.dp)
    )
}

@Composable
fun TxakurraInformazioa(datua: Txakurra, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(start = 8.dp)) {
        Text(text = datua.izena)
        Text(text = "${datua.adina} urte")
        Text(text = datua.deskribapena)
    }
}

@Preview(showBackground = true)
@Composable
fun TxakurraPreview() {
    _8AriketaTheme {
        TxakurrakAPP()
    }
}