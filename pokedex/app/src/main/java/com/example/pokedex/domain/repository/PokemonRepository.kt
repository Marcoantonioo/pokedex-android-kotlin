package com.example.pokeapiegsys.domain.repository

import com.example.pokeapiegsys.domain.model.BaseListResponsePokemon
import com.example.pokeapiegsys.domain.model.Pokemon

interface PokemonRepository {
    suspend fun loadAll(limit: Int, offset: Int): BaseListResponsePokemon?
    suspend fun getRandomPokemon(maxId: Int?): Pokemon
}
