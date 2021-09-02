package com.example.pokeapiegsys.domain.interactor

import com.example.pokeapiegsys.domain.repository.PokemonRepository

class LoadAllPokemonUseCase(private val pokemonRepository: PokemonRepository) {
    suspend fun execute(limit: Int, offset: Int) = pokemonRepository.loadAll(limit, offset)
}
