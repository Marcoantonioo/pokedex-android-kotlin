package com.example.pokedex.domain.interactor

import com.example.pokedex.domain.repository.PokemonRepository

class LoadAllPokemonUseCase(private val pokemonRepository: PokemonRepository) {
    suspend fun execute(limit: Int, offset: Int) = pokemonRepository.loadAll(limit, offset)
}
