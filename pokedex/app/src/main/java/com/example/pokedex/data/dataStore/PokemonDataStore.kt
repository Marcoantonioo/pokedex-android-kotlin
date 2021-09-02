package com.example.pokedex.data.dataStore

import com.example.pokedex.domain.model.BaseListResponsePokemon
import com.example.pokedex.domain.model.Pokemon

interface PokemonDataStore {
    suspend fun loadAll(limit: Int, offset: Int): BaseListResponsePokemon?
    suspend fun getRandomPokemon(maxId: Int?): Pokemon
}