package com.example.a8ariketa.data

import kotlinx.serialization.Serializable

@Serializable
data class Txakurra(
    val irudia: String = "",
    val izena: String = "",
    val adina: Int = 0,
    val deskribapena: String = ""
){

}
