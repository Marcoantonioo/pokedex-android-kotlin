package com.example.pokedex.core

import com.example.pokedex.remote.service.PokemonRetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    private const val baseUrl = "https://pokeapi.co/api/v2/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun pokemonService(): PokemonRetrofitService =
        retrofit.create(PokemonRetrofitService::class.java)
}