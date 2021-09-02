package com.example.pokedex.domain.model

class BaseListResponsePokemon(
    val count: Int,
    val previous: String,
    val next: String,
    val results: List<Pokemon>
)