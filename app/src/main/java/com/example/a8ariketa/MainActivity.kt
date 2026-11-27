package com.example.a8ariketa

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.font.FontWeight
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(txakurrak) {txakurra ->

            when(txakurra.izena.lowercase()){
                "argia" -> txakurra.irudia = R.drawable.argia
                "lur" -> txakurra.irudia = R.drawable.lur
                "sua" -> txakurra.irudia = R.drawable.sua
                "beltza" -> txakurra.irudia = R.drawable.beltza
                "haizea" -> txakurra.irudia = R.drawable.haizea
                "izar" -> txakurra.irudia = R.drawable.izar
                "lagun" -> txakurra.irudia = R.drawable.lagun
                "mendia" -> txakurra.irudia = R.drawable.mendia
                "txiki" -> txakurra.irudia = R.drawable.txiki
                else -> txakurra.irudia = R.drawable.argia
            }

            TxakurraItem(txakurra)
        }

    }
}

@Composable
fun TxakurraItem (txakurra: Txakurra, modifier: Modifier = Modifier){
    Card() {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TxakurraIcon(txakurra.irudia)
            Spacer(modifier = Modifier.width(12.dp))
            TxakurraInformazioa(txakurra.izena, txakurra.adina, txakurra.deskribapena)
        }
    }
}

@Composable
fun TxakurraIcon(irudia: Int) {

    Image(
        painter = painterResource(irudia),
        contentDescription = null,
        modifier = Modifier.size(60.dp)
    )
}

@Composable
fun TxakurraInformazioa(izena: String, adina: Int, deskribapena: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = izena,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "$adina urte",
            style = MaterialTheme.typography.labelMedium,
        )
        Text(
            text = deskribapena,
            style = MaterialTheme.typography.bodyMedium,
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _8AriketaTheme {
        TxakurrakAPP()
    }
}