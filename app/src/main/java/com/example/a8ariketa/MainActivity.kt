package com.example.a8ariketa

import android.annotation.*
import android.content.*
import android.os.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.annotation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.tooling.preview.*
import com.example.a8ariketa.ui.theme._8AriketaTheme
import kotlin.collections.*
import com.google.gson.*
import com.google.gson.reflect.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*

data class Txakurra(
    val irudia: String,
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

fun kargatuTxakurrak(context: Context): List<Txakurra> {
    return try {
        val jsonString = context.assets.open("txakurrak.json")
            .bufferedReader()
            .use{it.readText()}
        val listType = object : TypeToken<List<Txakurra>>() {}.type
        Gson().fromJson(jsonString, listType)
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}

@Composable
fun TxakurrakAPP(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val txakurZerrenda = remember { kargatuTxakurrak(context) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(txakurZerrenda) { txakurra ->
            TxakurraItem(txakurra = txakurra)
        }
    }
}

@SuppressLint("DiscouragedApi")
@Composable
fun TxakurraItem(
    txakurra: Txakurra
){
    val context = LocalContext.current
    val imageResId = remember(txakurra.irudia) {
        val id = context.resources.getIdentifier(
            txakurra.irudia.substringBeforeLast("."),
            "drawable",
            context.packageName
        )
        if (id != 0) id else R.drawable.ic_launcher_foreground
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        TxakurraIcon(txakurraIcon = imageResId)
        Spacer(modifier=Modifier.width(16.dp))
        TxakurraInformazioa(
            izena = txakurra.izena,
            adina = txakurra.adina,
            deskribapena = txakurra.deskribapena
        )
    }
}

@Composable
fun TxakurraIcon(
   @DrawableRes txakurraIcon: Int
) {
    Image(
        painterResource(id = txakurraIcon),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(64.dp)
            .clip(MaterialTheme.shapes.small)
    )
}

@Composable
fun TxakurraInformazioa(
    izena: String,
    adina: Int,
    deskribapena: String,
    modifier: Modifier = Modifier
) {
    Column(modifier=modifier){
        Text(
            text = izena,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$adina urte",
            style = MaterialTheme.typography.bodyMedium
        )
        /*Text(
            text = deskribapena,
            style = MaterialTheme.typography.bodySmall
        )*/
    }
}

@Preview(showBackground = true)
@Composable
fun TxakurraPreview() {
    _8AriketaTheme {
        TxakurrakAPP()
    }
}