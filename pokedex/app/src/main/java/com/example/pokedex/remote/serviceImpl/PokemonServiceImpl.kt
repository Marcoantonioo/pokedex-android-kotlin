package com.example.pokedex.remote.serviceImpl

import android.util.Log
import com.example.pokedex.domain.model.baseResponse.BaseListResponsePokemon
import com.example.pokedex.remote.service.PokemonRetrofitService
import com.example.pokedex.data.dataSource.PokemonRemoteService
import com.example.pokedex.domain.model.baseResponse.BaseDetalhesPokemon

class PokemonServiceImpl(private val service: PokemonRetrofitService) : PokemonRemoteService {
    override suspend fun loadAll(limit: Int, offset: Int): BaseListResponsePokemon? {
        return try {
            val call = service.loadAllPokemons(limit, offset)

            val resultCall = call.execute()

            val res = resultCall.body()

            populaInformacoesExtrasPokemon(res)

            return res

        } catch (ex: UnsupportedOperationException) {
            Log.d(TAG_RETROFIT, ex.message!!)
            null
        }
    }

    suspend fun populaInformacoesExtrasPokemon(res: BaseListResponsePokemon?) {
        // Percorremos a lista de pokemons retornados da response
        // e para cada um buscamos pelo id do mesmo para obter as outras
        // info.
        res?.results?.forEach {
            val detalhes = loadById(it.id!!)
            it.detalhes = detalhes
        }
    }

    override suspend fun loadById(id: Long): BaseDetalhesPokemon? {
        val spritesCall = service.loadById(id).execute()
        return spritesCall.body()
    }

    companion object {
        const val TAG_RETROFIT = "TAG_RETROFIT"
    }
}