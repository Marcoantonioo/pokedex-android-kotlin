package com.example.pokeapiegsys.data.dataStore

import com.example.pokeapiegsys.domain.model.BaseListResponsePokemon
import com.example.pokeapiegsys.domain.model.Pokemon

interface PokemonDataStore {
    suspend fun loadAll(limit: Int, offset: Int): BaseListResponsePokemon?
    suspend fun getRandomPokemon(maxId: Int?): Pokemon
}