package com.example.pokeapiegsys.core.service

import com.example.pokeapiegsys.domain.model.BaseDetalhesPokemon
import com.example.pokeapiegsys.domain.model.BaseListResponsePokemon

interface PokemonService {
    suspend fun loadAll(limit: Int, offset: Int): BaseListResponsePokemon?
    suspend fun loadById(id: Long): BaseDetalhesPokemon?
}
