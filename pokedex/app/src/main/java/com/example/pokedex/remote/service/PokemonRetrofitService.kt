package com.example.pokedex.remote.service

import com.example.pokedex.domain.model.baseResponse.BaseListResponsePokemon
import com.example.pokedex.domain.model.baseResponse.BaseDetalhesPokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonRetrofitService {
    @GET("pokemon")
    fun loadAllPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Call<BaseListResponsePokemon>

    @GET("pokemon/{id}/")
    fun loadById(@Path("id") id: Long): Call<BaseDetalhesPokemon>
}