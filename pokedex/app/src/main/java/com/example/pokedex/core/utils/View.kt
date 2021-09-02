package com.example.pokedex.core.utils

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun setVisibilidadeVisible(tornarViewsVisible: Boolean?, vararg ets: View?) {
    for (et in ets) {
        et?.visibility = if (tornarViewsVisible == true) View.VISIBLE else View.GONE
    }
}

fun setVisibilidadeVisibleView(et: View?, value: Boolean?) = setVisibilidadeVisible(value, et)