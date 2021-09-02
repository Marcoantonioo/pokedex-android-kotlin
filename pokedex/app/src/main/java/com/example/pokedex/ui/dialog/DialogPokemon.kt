package com.example.pokedex.ui.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.pokeapiegsys.R
import com.example.pokedex.domain.model.Pokemon

class DialogPokemon(
    private val mContext: Context,
) : DialogFragment() {
    var pokemon: Pokemon? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Melhorar a exbição dos pokemons
        val view = View.inflate(activity, R.layout.pokemon_dialog, null)
        val img = view.findViewById<ImageView>(R.id.iv_pokemon)

        Glide
            .with(mContext)
            .load(pokemon?.detalhes?.sprites?.back_default)
            .placeholder(resources.getDrawable(R.mipmap.ic_pokeball_foreground))
            .into(img)

        view.findViewById<TextView>(R.id.textViewName).text = pokemon?.getNameResolved()
        view.findViewById<TextView>(R.id.textViewAltura).text = pokemon?.detalhes?.height.toString()
        view.findViewById<TextView>(R.id.textViewPeso).text = pokemon?.detalhes?.weight.toString()
        return view
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}
