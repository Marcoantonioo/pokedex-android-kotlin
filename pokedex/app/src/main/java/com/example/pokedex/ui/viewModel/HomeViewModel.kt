package com.example.pokedex.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.domain.interactor.GetRandomPokemonUseCase
import com.example.pokedex.domain.interactor.LoadAllPokemonUseCase
import com.example.pokedex.domain.model.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO Ajustar posteriormente para utilizar o flow
class HomeViewModel(
    private val getRandomPokemonUseCase: GetRandomPokemonUseCase,
    private val loadAllPokemonUseCase: LoadAllPokemonUseCase,
) : ViewModel() {
    // Máximo id a ser utilizado para buscar por um pokemon random.
    private var maxId: Int? = null

    // region liveData utilizados
    var liveDataRandomPokemon: MutableLiveData<Pokemon> = MutableLiveData()
    var liveDataList: MutableLiveData<List<Pokemon>> = MutableLiveData()
    var liveDataProgress: MutableLiveData<Boolean> = MutableLiveData()
    // endregion

    // region utilitários
    var limit = 0
    var offset = 0
    // endregion

    /**
     * Método que nos retorna um pokemon randomicamente,
     * atribuimos ao [liveDataRandomPokemon] o valor retornado
     * do usecase.
     */
    fun getRandomPokemon() {
        liveDataProgress.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val pokemon = getRandomPokemonUseCase.execute(maxId)
            withContext(Dispatchers.Main) {
                liveDataRandomPokemon.value = pokemon
            }
        }
    }

    /**
     * Método utilizado para carregarmos os pokemons, sempre
     * buscando de 20 em 20, atribuimos ao [liveDataList] o
     * valor retornado do usecase.
     */
    fun loadMore() {
        liveDataProgress.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val baseResult = loadAllPokemonUseCase.execute(limit, offset)
            // maximo id a se utilizar no getRandomPokemon.
            maxId = baseResult?.count
            withContext(Dispatchers.Main) {
                liveDataList.value = baseResult?.results
            }
        }
    }

    /**
     * Mapeia todos os pokemons da lista recebida por parâmetro
     * para seu nome, utlizado no autoComplete.
     */
    fun getAllPokemonNames(list: List<Pokemon>) = list.map { it.name }

    companion object {
        const val OFFSET = 20
        const val LIMIT = 20
    }
}
