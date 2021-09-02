package com.example.pokeapiegsys.data.dataRepository

import com.example.pokeapiegsys.data.dataStore.PokemonFactory
import com.example.pokeapiegsys.domain.model.BaseListResponsePokemon
import com.example.pokeapiegsys.domain.model.Pokemon
import com.example.pokeapiegsys.domain.repository.PokemonRepository

class PokemonDataRepository(private val factory: PokemonFactory) : PokemonRepository {
    override suspend fun loadAll(limit: Int, offset: Int): BaseListResponsePokemon? {
        return factory.getRemoteDataStore().loadAll(limit, offset)
    }

    override suspend fun getRandomPokemon(maxId: Int?): Pokemon {
        return factory.getRemoteDataStore().getRandomPokemon(maxId)
    }
}
