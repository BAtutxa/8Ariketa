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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.a8ariketa.data.Txakurra
import com.example.a8ariketa.data.irakurriTxakurrak
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
    val txakurrak = remember(context) { irakurriTxakurrak(context) }

    LazyColumn(modifier = modifier) {
        items(txakurrak) { txakurra ->
            TxakurraItem(txakurra = txakurra)
        }
    }
}

@Composable
fun TxakurraItem(
    txakurra: Txakurra,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        TxakurraIcon(txakurra.irudia)
        TxakurraInformazioa(txakurra.izena, txakurra.adina)
    }
}

@Composable
fun TxakurraIcon(
    @DrawableRes txakurraIrudia: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small)),
        painter = painterResource(txakurraIrudia),
        contentDescription = null
    )
}

@Composable
fun TxakurraInformazioa(
    txakurraIzena: String,
    txakurraAdina: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = txakurraIzena,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.urteak, txakurraAdina)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TxakurraPreview() {
    _8AriketaTheme {
        TxakurrakAPP()
    }
}
