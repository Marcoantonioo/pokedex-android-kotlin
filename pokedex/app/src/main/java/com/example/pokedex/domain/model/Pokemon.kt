package com.example.pokedex.domain.model

import com.example.pokedex.domain.model.baseResponse.BaseDetalhesPokemon

data class Pokemon(
    var name: String? = null,
    var url: String? = null,
    var detalhes: BaseDetalhesPokemon? = null
) {

    fun getNameResolved() = this.name?.capitalize()

    // O id do Pokemon é resolvido splitando a URL
    // o penultimo caractere é o ID
    val id: Long?
        get() {
            val urlSplitted = url?.split('/')
            val idResolved = urlSplitted?.get(urlSplitted.size - 2)
            return idResolved?.toLong()
        }
}