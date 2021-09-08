package com.example.pokedex.data.dataStore

import com.example.pokedex.data.dataSource.PokemonRemoteService
import com.example.pokedex.domain.model.baseResponse.BaseListResponsePokemon
import com.example.pokedex.domain.model.Pokemon
import kotlin.random.Random

class PokemonRemoteDataStore(
    private val pokemonService: PokemonRemoteService
) : PokemonDataStore {
    override suspend fun loadAll(limit: Int, offset: Int): BaseListResponsePokemon? {
        return pokemonService.loadAll(limit, offset)
    }

    override suspend fun getRandomPokemon(maxId: Int?): Pokemon {
        val randomId = Random.nextInt(1, maxId!!)
        val detalhes = pokemonService.loadById(randomId.toLong())
        return Pokemon().apply {
            this.name = detalhes?.name
            this.detalhes = detalhes
        }
    }
}
