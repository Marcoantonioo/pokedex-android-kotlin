package com.example.pokedex.domain.model.baseResponse

import com.example.pokedex.domain.model.genericResponseApi.GenericResponseApiClassImpl

class BaseDetalhesPokemon(
    var id: Long,
    var name: String,
    var height: Int?,
    var weight: Int?,
    var sprites: Sprites,
    var abilities: List<Abilities>,
    var types: List<Types>
)

class Sprites(
    val back_default: String,
    val front_default: String
)

class Types(
    val type: GenericResponseApiClassImpl
)

class Abilities(
    var ability: GenericResponseApiClassImpl
)

