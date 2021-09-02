package com.example.pokeapiegsys.domain.interactor

import com.example.pokeapiegsys.domain.repository.PokemonRepository

class GetRandomPokemonUseCase(private val pokemonRepository: PokemonRepository) {
    suspend fun execute(maxId: Int?) = pokemonRepository.getRandomPokemon(maxId)
}
