package com.example.pokedex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapiegsys.R
import com.example.pokedex.domain.model.Pokemon

class PokemonAdapter(
    private val context: Context
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>(), View.OnClickListener {
    var itemClickListener: OnItemClickListener? = null

    var list: List<Pokemon> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.name.text = item.getNameResolved()
        holder.id.text = item.id?.toString()

        holder.itemView.setOnClickListener(this)

        holder.itemView.tag = position

        Glide
            .with(context)
            .load(item.detalhes?.sprites?.back_default)
            .into(holder.img);
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.textViewPokemonId)
        val name: TextView = itemView.findViewById(R.id.textViewName)
        var img: ImageView = itemView.findViewById(R.id.iv_pokemon)
    }

    override fun onClick(v: View?) {
        itemClickListener ?: return
        val position = getItemSelected(v)
        itemClickListener?.onItemClick(position)
    }

    private fun getItemSelected(itemView: View?): Pokemon {
        val position = itemView?.tag as Int
        return list[position]
    }

    interface OnItemClickListener {
        fun onItemClick(pokemon: Pokemon)
    }
}