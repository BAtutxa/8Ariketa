package com.example.a8ariketa

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a8ariketa.ui.theme._8AriketaTheme
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _8AriketaTheme {
                val txakurrak = remember { kargatuTxakurrak(this) }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TxakurrakAPP(
                        txakurrak = txakurrak,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TxakurraIcon(
    irudia: String,
    deskribapena: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val bitmap: Bitmap? = remember(irudia){
        runCatching {
            context.assets.open("$irudia.jpg").use { stream ->
                BitmapFactory.decodeStream(stream)
            }
        }.getOrNull()
    }

    bitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = deskribapena,
            contentScale = ContentScale.Crop,
            modifier = modifier.size(64.dp).clip(CircleShape)
        )
    }
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
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$adina urte",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = deskribapena,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun Txakurraltem(
    txakurra: Txakurra,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TxakurraIcon(
                irudia = txakurra.irudia,
                deskribapena = txakurra.izena
            )
            Spacer(modifier = Modifier.width(12.dp))
            TxakurraInformazioa(
                izena = txakurra.izena,
                adina = txakurra.adina,
                deskribapena = txakurra.deskribapena,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun TxakurrakAPP(
    txakurrak: List<Txakurra>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(txakurrak, key = { it.izena }) { txakurra ->
            Txakurraltem(txakurra = txakurra)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _8AriketaTheme {
        val context = LocalContext.current
        val txakurrak = remember { kargatuTxakurrak(context) }
        TxakurrakAPP(txakurrak = txakurrak)
    }
}