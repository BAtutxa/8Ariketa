package com.example.a8ariketa.models

import kotlinx.serialization.Serializable

@Serializable
data class Txakurrak (
    val Image :Int = 0,
    val izena: String,
    val deskribapena: String
)