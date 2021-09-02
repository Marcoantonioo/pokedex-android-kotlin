package com.example.pokedex.data.dataRepository

import com.example.pokedex.data.dataStore.PokemonFactory
import com.example.pokedex.domain.model.BaseListResponsePokemon
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.repository.PokemonRepository

class PokemonDataRepository(private val factory: PokemonFactory) : PokemonRepository {
    override suspend fun loadAll(limit: Int, offset: Int): BaseListResponsePokemon? {
        return factory.getRemoteDataStore().loadAll(limit, offset)
    }

    override suspend fun getRandomPokemon(maxId: Int?): Pokemon {
        return factory.getRemoteDataStore().getRandomPokemon(maxId)
    }
}
