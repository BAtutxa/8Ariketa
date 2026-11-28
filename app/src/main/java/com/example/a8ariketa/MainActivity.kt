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
import androidx.compose.ui.draw.clip
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
    //le pasamos el contexto actual de la aplicacion Android. para que pueda acceder
    //a los recursos internos de la app, como la carpeta assets donde esta el JSON
    val context = LocalContext.current
    //Usamos remember para guardar la lista ya convertida y que no se pierda
    //metemos el resultado en la variable "txakurrak"
    //llamamos a la funcion "datuakKargatu" para leer el archivo "txakurrak.json"
    val txakurrak = remember { Datuak().datuakKargatu(context, "txakurrak.json") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        //deja una separacion fija de 12.dp entre cada tarjeta
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        //recorreo cada uno de los elemntos de la lista "txakurrak"
        //y en cada iteracion guarda el objeto actual en la variable temporal "txakurra"
        items(txakurrak) { txakurra ->
            //coge el nombre del perro y lo pone ne minusculas
            when (txakurra.izena.lowercase()){
                //compara ese nombre con los casos posibles
                //cuando coincide lo guarda dentro de "txakurra.irudia"
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
            //Cuando el perro ya tiene su foto asignada, llama al composbale "TxakurraItem"
            //pasandole el objeto completo para que dibuje la Card
                TxakurraItem(txakurra)
        }
    }
}

//recibe el objeto "txakurra" con todos los datos del perro actual
@Composable
fun TxakurraItem(txakurra: Txakurra, modifier: Modifier = Modifier) {
    //crea el contenedor visual tipo tarjeta
    Card(modifier = modifier) {
        //organiza los elementos interiores en horizontal
        Row(
            modifier = Modifier
                //hace que la fila ocupe todo el ancho
                .fillMaxSize()
                //deja un margen interior de 12.dp por los cuatro lados
                .padding(12.dp),
            //alinea verticalmente en el centro la foto repecto al bloque de texto
            verticalAlignment = Alignment.CenterVertically
        ) {
            //dibuja la foto reodnda pasandole unicamente el ID numerico de la imagen
            TxakurraIcon(txakurra.irudia)
            //crear un hueco vacio horizontal para separar la foto del texto
            Spacer(modifier = Modifier.width(12.dp))
            //Dibujar la columna con los tres textos
            TxakurraInformazioa(txakurra.izena, txakurra.adina, txakurra.deskribapena)
        }
    }
}

//recibe un solo parametro (el numero entero que identifica la imagen dentro de "res/drawable")
@Composable
fun TxakurraIcon(irudia: Int) {
    Image(
        //busca el ID en la carpeta drawable y lo convierte en un recurso visual listo
        painter = painterResource(irudia),
        contentDescription = null,
        //para escalar la imagen manteniendo su proporción para llenar todo el espacio y recorta lo que sobre
        contentScale = ContentScale.Crop,
        //fija el tamaño de la imagen a un recuadro 60x60
        modifier = Modifier
            .size(60.dp)
            //recorta las esquinas de ese cuadrado eplicando la forma small definida en el archivo Shape.kt
            .clip(MaterialTheme.shapes.small)
    )
}


//recibe los tres datos de texto que necesita pintar en pantalla
@Composable
fun TxakurraInformazioa(izena: String, adina: Int, deskribapena: String) {
    //apila los tres elementos text en vertical y deja una separacion de 4.dp
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            //mostrar el nombre del perro
            text = izena,
            //usar el estilo de titulo mediano definido en Type.kt
            style = MaterialTheme.typography.titleMedium,
            //ponerlo en negrita
            fontWeight = FontWeight.Bold,
            //aplicar el color principal de la paleta definido en Color.kt y Theme.kt
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            //juntamos el numero de la variable adina con la palabra "urte"
            text = "$adina urte",
            //aplicar un tamañode letra mediano
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            //mostrar la descripcion del perro
            text = deskribapena,
            //usar el estilo estandar de texto de lectura del tema
            style = MaterialTheme.typography.bodyMedium
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