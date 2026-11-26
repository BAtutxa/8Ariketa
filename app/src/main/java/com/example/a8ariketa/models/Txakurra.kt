package com.example.a8ariketa.models

import kotlinx.serialization.Serializable

@Serializable
data class Txakurra(
    val izena: String,
    val deskribapena: String,
    val adina: Int,
    val irudia: String
)
