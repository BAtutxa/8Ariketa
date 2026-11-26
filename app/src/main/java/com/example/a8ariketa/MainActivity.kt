package com.example.a8ariketa

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
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
import androidx.compose.ui.unit.sp
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
    val txakurrakList = remember { Datuak(context, "txakurrak.json") }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(txakurrakList) { txakurra ->
            TxakurraItem(txakurra = txakurra)
        }
    }
}

@Composable
fun TxakurraItem(txakurra: Txakurra, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TxakurraIcon(irudia = txakurra.irudia)
            Spacer(modifier = Modifier.width(16.dp))
            TxakurraInformazioa(
                izena = txakurra.izena,
                adina = txakurra.adina,
                deskribapena = txakurra.deskribapena
            )
        }
    }
}

@Composable
fun TxakurraIcon(irudia: String, modifier: Modifier = Modifier) {
    val irudiaRes = when (irudia.lowercase()) {
        "beltza", "beltza.jpg" -> R.drawable.beltza
        "lur", "lur.jpg" -> R.drawable.lur
        "txiki", "txiki.jpg" -> R.drawable.txiki
        "haizea", "haizea.jpg" -> R.drawable.haizea
        "lagun", "lagun.jpg" -> R.drawable.lagun
        "sua", "sua.jpg" -> R.drawable.sua
        "izar", "izar.jpg" -> R.drawable.izar
        "mendia", "mendia.jpg" -> R.drawable.mendia
        "argia", "argia.jpg" -> R.drawable.argia
        else -> R.drawable.ic_launcher_foreground
    }

    Image(
        painter = painterResource(id = irudiaRes),
        contentDescription = irudia,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .padding(10.dp)
            .size(64.dp)
    )
}

@Composable
fun TxakurraInformazioa(
    izena: String,
    adina: Int,
    deskribapena: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = izena,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$adina urte",
            fontSize = 14.sp
        )
        Text(
            text = deskribapena,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TxakurraPreview() {
    _8AriketaTheme {
        TxakurraItem(
            txakurra = Txakurra(
                izena = "Beltza",
                adina = 2,
                irudia = "beltza",
                deskribapena = "Txakur alaia"
            )
        )
    }
}