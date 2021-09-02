package com.example.pokedex.data.dataStore

class PokemonFactory(
    private val dataRemote: PokemonRemoteDataStore,
    private val dataCache: PokemonCacheDataStore
) {
    fun getRemoteDataStore(): PokemonRemoteDataStore {
        return dataRemote
    }

    fun getCacheDataStore(): PokemonCacheDataStore {
        return dataCache
    }
}