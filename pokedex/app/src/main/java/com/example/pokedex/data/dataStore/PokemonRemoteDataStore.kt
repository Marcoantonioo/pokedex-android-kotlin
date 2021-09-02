package com.example.pokeapiegsys.data.dataStore

import com.example.pokeapiegsys.core.service.PokemonService
import com.example.pokeapiegsys.domain.model.BaseListResponsePokemon
import com.example.pokeapiegsys.domain.model.Pokemon
import kotlin.random.Random

class PokemonRemoteDataStore(
    private val pokemonService: PokemonService
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
