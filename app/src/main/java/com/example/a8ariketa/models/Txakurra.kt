package com.example.a8ariketa.models


import kotlinx.serialization.Serializable

@Serializable
data class Txakurra(
    var irudia: Int = 0,
    var izena: String = "",
    var adina: Int = 0,
    var deskribapena: String = ""
)
