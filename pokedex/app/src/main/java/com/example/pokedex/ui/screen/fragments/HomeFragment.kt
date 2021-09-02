package com.example.pokedex.ui.screen.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapiegsys.R
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.ui.adapter.PokemonAdapter
import com.example.pokedex.ui.dialog.DialogPokemon
import com.example.pokedex.ui.viewModel.HomeViewModel
import com.example.pokedex.ui.viewModel.HomeViewModel.Companion.LIMIT
import com.example.pokedex.ui.viewModel.HomeViewModel.Companion.OFFSET
import com.example.pokedex.core.utils.setVisibilidadeVisibleView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), PokemonAdapter.OnItemClickListener {
    private lateinit var mViewHolder: ViewHolder
    private lateinit var adapter: PokemonAdapter
    private val mViewModel: HomeViewModel by viewModel()
    private var mainList = mutableListOf<Pokemon>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Melhorar a exbição dos pokemons
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        mViewHolder = ViewHolder(rootView)

        adapter = PokemonAdapter(requireContext())
        adapter.itemClickListener = this

        mViewHolder.recylerView.addOnScrollListener(onScrollHitBottomLoadMore)

        mViewHolder.autoCompleteTextView.addTextChangedListener(textWatcherAutoComplete)
        mViewHolder.autoCompleteTextView.onItemClickListener = handleOnCompleteClick()

        configureBtnRandomPokemon()
        configureLayoutManager()
        setObservers()
        return rootView
    }

    private fun setObservers() {
        // Observer do progressBar
        handleObserver(mViewModel.liveDataProgress) { showProgress(it) }

        // Observer do randomPokemon
        handleObserver(mViewModel.liveDataRandomPokemon) {
            val dialog = DialogPokemon(requireContext())
            dialog.pokemon = it
            dialog.show(childFragmentManager, "Tag")
            mViewModel.liveDataProgress.postValue(false)
        }

        // Observer da lista de pokemons
        handleObserver(mViewModel.liveDataList) { listRemote ->
            // para cada lista retornada da viewModel adicionamos
            // a lista principal.
            listRemote.forEach {
                mainList.add(it)
            }
            updateAdapterList(mainList)

            mViewHolder.autoCompleteTextView.setAdapter(configureAdapterNomes())
        }
    }

    private fun showProgress(isLoading: Boolean) {
        setVisibilidadeVisibleView(mViewHolder.progressBar, isLoading)
        setVisibilidadeVisibleView(mViewHolder.btnRandom, !isLoading)
    }

    private fun configureAdapterNomes(): ArrayAdapter<String> {
        return ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            mViewModel.getAllPokemonNames(mainList)
        )
    }

    private val textWatcherAutoComplete = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (s.isEmpty()) {
                updateAdapterList(mainList)
                return
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {}
    }

    private fun handleOnCompleteClick(): AdapterView.OnItemClickListener {
        return AdapterView.OnItemClickListener { parent, _, position, _ ->
            val newList = mutableListOf<Pokemon>()
            val item = parent.getItemAtPosition(position).toString()
            val pokemon = mainList.firstOrNull { it.name.equals(item, ignoreCase = true) }
            if (pokemon != null) {
                newList.add(pokemon)
                updateAdapterList(newList)
            }
        }
    }

    private val onScrollHitBottomLoadMore = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                mViewModel.offset += OFFSET
                mViewModel.limit = LIMIT
                mViewModel.loadMore()
            }
        }
    }

    private fun configureBtnRandomPokemon() {
        mViewHolder.btnRandom.setOnClickListener {
            mViewModel.getRandomPokemon()
        }
    }

    private fun updateAdapterList(list: List<Pokemon>) {
        adapter.list = list
        adapter.notifyDataSetChanged()
        mViewModel.liveDataProgress.postValue(false)
    }


    override fun onItemClick(pokemon: Pokemon) {
        Toast.makeText(context, pokemon.name, Toast.LENGTH_LONG).show()
    }

    private fun configureLayoutManager() {
        val mLayoutManager = LinearLayoutManager(requireContext())
        mViewHolder.recylerView.layoutManager = mLayoutManager
        mViewHolder.recylerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        if (mainList.isEmpty()) {
            mViewModel.loadMore()
        }
    }

    open class ViewHolder(rootView: View) {
        val progressBar: ProgressBar = rootView.findViewById(R.id.progressBar)
        val btnRandom: ImageView = rootView.findViewById(R.id.btnRandom)
        val recylerView: RecyclerView = rootView.findViewById(R.id.recylerView)
        val autoCompleteTextView: AutoCompleteTextView = rootView.findViewById(R.id.autoComplete)
    }

    private fun <T> handleObserver(liveData: MutableLiveData<T>, action: (T) -> Unit) {
        liveData.observe(viewLifecycleOwner, {
            action.invoke(it)
        })
    }
}
