package com.example.pokedex.domain.model.baseResponse

import com.example.pokedex.domain.model.Pokemon

class BaseListResponsePokemon(
    val count: Int,
    val previous: String,
    val next: String,
    val results: List<Pokemon>
)