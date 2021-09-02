package com.example.pokeapiegsys.domain.model

class BaseDetalhesPokemon(
    var id: Long,
    var name: String,
    var height: Int?,
    var weight: Int?,
    var sprites: Sprites
)

class Sprites(
    val back_default: String,
    val front_default: String
)