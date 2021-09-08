package com.example.pokedex.core.utils

object Utils {
    fun resolveIdSplited(url: String?): Long? {
        val urlSplitted = url?.split('/')
        val idResolved = urlSplitted?.get(urlSplitted.size - 2)
        return idResolved?.toLong()
    }
}
