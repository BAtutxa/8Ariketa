package com.example.a8ariketa.ui.theme

import kotlinx.serialization.Serializable

@Serializable
data class Txakurra(
    val irudia: String,
    val izena: String,
    val adina: Int,
    val deskribapena: String
)