package com.example.a8ariketa

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a8ariketa.data.Txakurra
import com.example.a8ariketa.data.irakurriTxakurrak
import com.example.a8ariketa.ui.theme._8AriketaTheme

// APLIKAZIOAREN JARDUERA NAGUSIA
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // PANTAILA OSOA ERABILTZEKO AUKERA GAITU
        enableEdgeToEdge()
        // APLIKAZIOAREN INTERFAZEA KARGATU
        setContent {
            _8AriketaTheme {
                TxakurrakAPP()
            }
        }
    }
}

// TXAKURREN ZERRENDA ERAKUSTEN DUEN FUNTZIO NAGUSIA
@Composable
fun TxakurrakAPP() {
    val context = LocalContext.current
    // JSON FITXATEGITIK TXAKURREN ZERRENDA KARGATU ETA MEMORIAN GORDE
    val txakurrak = remember { irakurriTxakurrak(context) }

    // PANTAILAREN EGITURA NAGUSIA (SCAFFOLD)
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        // ZERRENDA BERTIKALA (LAZYCOLUMN)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // TXAKUR BAKOITZARENTZAT TXARTEL BAT SORTU
            items(txakurrak) { txakurra ->
                TxakurraItem(txakurra = txakurra)
            }
        }
    }
}

// TXAKUR BAKOITZAREN TXARTELA (CARD) MARRAZTEN DUEN FUNTZIOA
@Composable
fun TxakurraItem(
    txakurra: Txakurra,
    modifier: Modifier = Modifier
) {
    // ERTZ BIRIBILDUAK DITUEN TXARTELA
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        // IRUDIA ETA INFORMAZIOA HORIZONTALEAN KOKATZEKO ERRENKADA (ROW)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TXAKURRAREN ARGAZKIA ERAKUTSI
            TxakurraIcon(irudiaIzena = txakurra.irudia)
            // IRUDIAREN ETA TESTUAREN ARTEKO TARTEA
            Spacer(modifier = Modifier.width(16.dp))
            // TXAKURRAREN IZENA ETA ADINA ERAKUTSI
            TxakurraInformazioa(
                izena = txakurra.izena,
                adina = txakurra.adina
            )
        }
    }
}

// TXAKURRAREN ARGAZKIA BOROBILEAN ERAKUSTEN DUEN FUNTZIOA
@Composable
fun TxakurraIcon(
    irudiaIzena: String,
    modifier: Modifier = Modifier
) {
    // UNEKO TESTUINGURUA LORTU
    val context = LocalContext.current
    // DRAWABLE KARPETATIK IRUDIAREN ID-A LORTU IZENAREN BIDEZ
    val imageResId = remember(irudiaIzena) {
        context.resources.getIdentifier(irudiaIzena, "drawable", context.packageName)
    }

    // IRUDIA EXISTITZEN BADA, PANTAILAN MARRAZTU
    if (imageResId != 0) {
        // ARGAZKIA BOROBIL FORMAREKIN ETA TAMAINA FINKOAREKIN
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = irudiaIzena,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(64.dp)
                .clip(CircleShape)
        )
    }
}

// TXAKURRAREN IZENA ETA ADINA ERAKUSTEN DITUEN FUNTZIOA
@Composable
fun TxakurraInformazioa(
    izena: String,
    adina: Int,
    modifier: Modifier = Modifier
) {
    // DATUAK BERTIKALEAN KOKATZEKO ZUTABEA (COLUMN)
    Column(modifier = modifier) {
        // TXAKURRAREN IZENA LETRA LODIZ
        Text(
            text = izena,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        // TXAKURRAREN ADINA "URTE" HITZAREKIN
        Text(
            text = "$adina urte",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

// DESIGN PANELEAN AURREIKUSPENA IKUSTEKO FUNTZIOA
@Preview(showBackground = true)
@Composable
fun TxakurraPreview() {
    _8AriketaTheme {
        TxakurrakAPP()
    }
}
