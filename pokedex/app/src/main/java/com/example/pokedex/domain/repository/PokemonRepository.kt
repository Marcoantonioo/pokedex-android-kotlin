package com.example.pokedex.domain.repository

import com.example.pokedex.domain.model.baseResponse.BaseListResponsePokemon
import com.example.pokedex.domain.model.Pokemon

interface PokemonRepository {
    suspend fun loadAll(limit: Int, offset: Int): BaseListResponsePokemon?
    suspend fun getRandomPokemon(maxId: Int?): Pokemon
}
