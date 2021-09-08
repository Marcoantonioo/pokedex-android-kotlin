package com.example.pokedex.domain.model.genericResponseApi

import com.example.pokedex.core.utils.Utils

class GenericResponseApiClassImpl(
    override var name: String?,
    override var url: String?
) : GenericResponseApiClass {
    val id: Long?
        get() {
            return Utils.resolveIdSplited(url)
        }
}