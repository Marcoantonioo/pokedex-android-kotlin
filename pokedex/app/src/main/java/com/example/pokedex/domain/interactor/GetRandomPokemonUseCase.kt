package com.example.pokedex.domain.interactor

import com.example.pokedex.domain.repository.PokemonRepository

class GetRandomPokemonUseCase(private val pokemonRepository: PokemonRepository) {
    suspend fun execute(maxId: Int?) = pokemonRepository.getRandomPokemon(maxId)
}
