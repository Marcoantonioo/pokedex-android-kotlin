package com.example.pokedex.data.dataSource

import com.example.pokedex.domain.model.baseResponse.BaseDetalhesPokemon
import com.example.pokedex.domain.model.baseResponse.BaseListResponsePokemon

interface PokemonRemoteService {
    suspend fun loadAll(limit: Int, offset: Int): BaseListResponsePokemon?
    suspend fun loadById(id: Long): BaseDetalhesPokemon?
}
